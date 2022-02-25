package com.example.andy.wildflydb.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Field first name is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Field last name is mandatory")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Field email is mandatory")
    @Email(message = "Input email format is invalid. Must be xxxxxx@xxx.xxx")
    private String email;

    @JoinColumn(name = "department_id")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;


    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
