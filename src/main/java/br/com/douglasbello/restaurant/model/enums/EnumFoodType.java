package br.com.douglasbello.restaurant.model.enums;

public enum EnumFoodType {
    BURGUER("BURGUER"),
    PIZZA("PIZZA"),
    HOTDOG("HOTDOG"),
    DESSERT("DESSERT");

    private String type;

    EnumFoodType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
