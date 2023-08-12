package br.com.douglasbello.restaurant.model.entities;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotdog")
public class Hotdog extends Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    public Hotdog() {}

    public Hotdog(String name, String description, BigDecimal price, EnumFoodSize size, UUID id) {
        super(name, description, price, size);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    protected void calculatePrice() {
        if (this.size == EnumFoodSize.MEDIUM) {
            this.price = price.add(new BigDecimal("2.99"));
        }

        if (this.size == EnumFoodSize.LARGE) {
            this.price = price.add(new BigDecimal("6.99"));
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hotdog other = (Hotdog) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
