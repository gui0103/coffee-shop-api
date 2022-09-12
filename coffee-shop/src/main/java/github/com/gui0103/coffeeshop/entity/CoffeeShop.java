package github.com.gui0103.coffeeshop.entity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("coffeeShops")
public class CoffeeShop {

    @Id
    private String id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 50)
    private String password;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @DecimalMin("0.00")
    private Double moneyReceived;

    private LocalDateTime creationDate;

    @NotBlank
    private String location;

    private List<Coffee> coffeeList;

    public CoffeeShop(String email, String password, String name, String location) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.moneyReceived = 0.00;
        this.creationDate = LocalDateTime.now();
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoneyReceived() {
        return moneyReceived;
    }

    public void setMoneyReceived(Double moneyReceived) {
        this.moneyReceived = moneyReceived;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Coffee> getCoffeeList() {
        return coffeeList;
    }

    public void setCoffeeList(List<Coffee> coffeeList) {
        this.coffeeList = coffeeList;
    }
}
