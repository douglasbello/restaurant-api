package br.com.douglasbello.restaurant.model.enums;

public enum EnumFoodSize {
    REGULAR("REGULAR"),
    MEDIUM("MEDIUM"),
    LARGE("LARGE");

    private String size;

    EnumFoodSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
