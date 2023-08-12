package br.com.douglasbello.restaurant.model.enums;

public enum EnumDrinkType {
    COCA_COLA("COCA_COLA"),
    PEPSI("PEPSI"),
    GUARANA("GUARANÁ");

    private String sodaBrand;

    EnumDrinkType(String sodaBrand) {
        this.sodaBrand = sodaBrand;
    }

    public String getSodaBrand() {
        return sodaBrand;
    }
}
