package github.com.gui0103.coffeeshop.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

import java.util.UUID;

@Document
public class Coffee {

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @DecimalMin("0.00")
    private Double price;

    @NotNull
    private Integer quantityInStock;

    //@CreationTimestamp
    //private LocalDateTime orderDate;

    public Coffee(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}