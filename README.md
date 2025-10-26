# Build một RESTful API
gồm các endpoint:

👉 View a list of all `actor`

👉 View detail of an `actor`

👉 Add new `actor`

👉 Delete an `actor`

👉 Update an `actor`

## 1. Khởi tạo template ban đầu cho dự án spring boot bằng [spring-initializr](https-colon-//start.spring.io/)
Vào đường link và chọn các option cho phù hợp với dự án:
- Project: Chọn công cụ Build dự án (Dự án demo hiện tại dùng maven)
- Language: Có java, kotlin và groovy (Dự án demo hiện tại dùng java)
- Spring Boot: Chọn phiên bản spring boot (Dự án demo hiện tại dùng 3.5.6)
- Project Metadata: liên quan đến việc đặt tên và mô tả dự án, phiên bản java (Dự án demo hiện tại dùng java 21)
- Dependencies: Các dependencies muốn dùng, sau này có thể thêm trong file pom.xml, Các dependencies dự án demo dùng lúc khởi tạo:
  - Lombok: hỗ trợ tự tạo các hàm cơ bản của class (gồm getter, setter, constructor,...)
  - MySQL Driver: driver cho mysql
  - Spring web: 
  - Spring Data JPA: Cung cấp các hàm được cài đặt sẵn cho các thao tác CRUD
  - Spring Boot Actuator: Health check endpoint cho server

Sau khi chọn xong các cấu hình thì nhấn generate để tải về và giải nén ra, ta đã có một folder project mẫu gồm các cấu hình đã chọn

## 2. Cấu hình ứng dụng trong application.properties
Ta có thể search google hoặc hỏi AI về cấu hình cơ bản thì sẽ gồm:
- Application name
- Các biến kết nối database
- Các cấu hình JPA: Ở các cấu hình này thì chú ý thuộc tính **ddl-auto**
  - Đặt validate nếu ta làm dự án với database có sẵn(chỉ kiểm tra entity có giống schema và báo lỗi nếu khác)
  - Đặt Update khi ta muốn các thuộc tính entity áp dụng lên database (nếu khác thì sẽ tự động thay đổi database)

## 3. Tạo các package theo cấu trúc phân tầng
Tạo các folder như:
- config
- controller
- dto
- entity
- mapper
- repository
- service

## 4. Tạo entity
Trước khi tạo một endpoint thì phải có một đối tượng để JPA giao tiếp với database, đó chính là entity
- Entity là một đối tượng ánh xạ của một bảng trong database, ta sẽ tạo các field theo schema của database
- Ở đầu file ta cần một annotation "@Entity" để đánh dấu đây là một thực thể để JPA ánh xạ database

## 5. Tạo Repository
Sau khi tạo entity thì ta cần tạo repository để có thể giao tiếp và thực hiện truy vấn database
- Ở đầu file cần một annotation "@Repository" để spring boot biết rằng đây là một bean repository giúp dễ inject vào các component khác
- Do dùng JPA nên ta phải "extends JpaRepository<Entity, IDType> với Entity là class entity vừa tạo, IDTtype là kiểu dữ liệu bạn dùng làm ID của entity
- Với các CRUD cơ bản thì Jpa đã tự tạo các hàm cho chúng ta sử dụng nên không cần code thêm

## 6. Tạo Service
Ở service chỉ cần inject dependency repository vừa tạo vào và gọi các hàm có sẵn hoặc thêm business logic nếu cần
- Tương tự repository, ta cần annotation "@Service" ở đầu file

## 7. Tạo Controller
- Tương tự 2 component trên, ta cần khai báo annotation "@RestController"
- Tiếp theo ta cần inject service vào để gọi các hàm của service
- Với mỗi endpoint ta cũng cần một annotation (@GetMapping, @PostMapping, ...)

---------------------------------------------------------------------------------------------------------------------------------------------------------------------
# Validate API và API Docs
## 1. Validate API
Để có nhiều trường để validate thì em đã tạo route film
### 1.1. Thêm dependency mapStruct
Để dơn giản trong quá trình validate thì ta sẽ dùng mapStruct để auto map các dto với entity
### 1.2. Tạo dto cho các endpoint
- Bước validate thường được thực hiện ở các dto nhận request, em validate 2 dto là Create và Update request
  - Với Create thì em dùng các annotation của jakarta.validation.constraints gồm (@NotBlank, @Size, @NotNull, @Positive, ...)
  - Còn Update thì tương tự Create nhưng loại bỏ các xác thực về NotBlank vầ Not Null
### 1.3. Cấu hình mapper
Mapper thì ta cần phải dùng annotation @Mapper(componentModel = "spring") ở trước khai báo class
- Để tạo các hàm mapper thì ta chỉ cần khai báo kiểu trả về và tham số truyền vào: ví dụ cần map từ FilmCreateRequest qua entity thì ta đặt kiểu trả về là entity còn tham số truyền vào là dto
- Nếu đối tượng đích có các thuộc tính chúng ta không muốn truyền vào thì ta dùng @Mapping(target = "tên thuộc tính", ignore = true)
- Nếu đối tượng đích và đối tượng nguồn có tên thuộc tính khác nhau nhưng vẫn muốn map thì ta dùng @Mapping(target = "thuộc tính đích", source, "thuộc tính nguồn")

## 2. API Docs
### 2.1. Thêm dependency spring doc 
Vào pom.xml và thêm dependency
```<groupId>org.springdoc</groupId>```
```<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>```

### 2.2. Cấu hình cho controller
Thêm annotation 
```@Tag(name = "Actor", description = "This is Actor tag")```
để chia tag và tạo mô tả cho route

### 2.3. Truy cập Swagger
ta vào endpoint /swagger-ui.html để truy cập swagger