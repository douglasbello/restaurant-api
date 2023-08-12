package br.com.douglasbello.restaurant.model.enums;

public enum EnumDrinkSize {
    ML600("600ml"),
    ML1500("1500ml"),
    ML2000("2000ml");

    private String ml;

    EnumDrinkSize(String ml) {
        this.ml = ml;
    }

    public String getMl() {
        return ml;
    }
}
