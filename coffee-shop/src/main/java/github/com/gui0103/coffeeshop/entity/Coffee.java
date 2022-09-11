package github.com.gui0103.coffeeshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

import java.util.UUID;

@Document
public class Coffee {

    @Id
    private String id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @DecimalMin("0.00")
    private Double price;

    @NotNull
    private Integer quantityInStock;

    public Coffee(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}