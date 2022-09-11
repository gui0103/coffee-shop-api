package github.com.gui0103.coffeeshop.entity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("users")
public class User {

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
    private Double money;

    private LocalDateTime creationDate;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.money = 0.00;
        this.creationDate = LocalDateTime.now();
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
