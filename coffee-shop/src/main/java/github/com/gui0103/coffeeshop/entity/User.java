package github.com.gui0103.coffeeshop.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
public class User {

    @PrimaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idUser;

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

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.money = 0.00;
    }

    public UUID getIdUser() {
        return idUser;
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

    // Needing more security
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
