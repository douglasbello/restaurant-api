package br.com.douglasbello.restaurant.model.enums;

public enum EnumUserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    EnumUserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
