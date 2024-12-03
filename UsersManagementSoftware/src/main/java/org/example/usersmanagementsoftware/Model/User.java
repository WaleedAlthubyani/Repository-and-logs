package org.example.usersmanagementsoftware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "length(name)>4 and length(username)>4 and (role='user' or role='admin') and age>0")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a name")
    @Size(min = 5, message = "Name must be longer than 4 letters")
    @Column(columnDefinition = "varchar(255) not null")
    private String name;

    @NotEmpty(message = "Please enter a username")
    @Size(min = 5,message = "Username must be longer than 4 letters")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String username;

    @NotEmpty(message = "Please enter a password")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @NotEmpty(message = "Please enter an email")
    @Email(message = "Please enter a valid email address")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String email;

    @NotEmpty(message = "Please enter a role")
    @Pattern(regexp = "^(?i)(user|admin)$",message = "Role can only be user or admin")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    @NotNull(message = "Please enter an age")
    @Positive(message = "Age must be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer age;

}
