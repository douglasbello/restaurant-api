package br.com.douglasbello.restaurant.model.dtos;

public class ExceptionResponseDTO {
    private Integer status;
    private String message;

    public ExceptionResponseDTO() {}

    public ExceptionResponseDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}