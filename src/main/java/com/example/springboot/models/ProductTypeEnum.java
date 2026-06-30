package com.example.springboot.models;

public enum ProductTypeEnum {
    ELECTRONICS("Electronics"),
    FOOD("Food"),
    BEVERAGES("Beverages"),
    CLOTHING("Clothing"),
    HOME_APPLIANCES("Home Appliances"),
    COSMETICS("Cosmetics"),
    BOOKS("Books"),
    TOYS("Toys"),
    AUTOMOTIVE("Automotive"),
    HEALTH_CARE("Health Care");

    private final String description;

    ProductTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
