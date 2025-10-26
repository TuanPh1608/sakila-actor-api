package com.example.demo.entity;

public enum Rating {
    G,
    PG,
    R,
    NC_17("NC-17"),
    PG_13("PG-13");

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    Rating() {
        this.value = this.name();
    }

    public String getValue() {
        return value;
    }

}