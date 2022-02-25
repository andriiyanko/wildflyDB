package com.example.andy.wildflydb.persistence.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


//    I'm using org.hibernate.validator because, WildFly 8 realize Java EE 7 and using Bean Validation 1.1
//    javax.validation is not available as it's new in Bean Validation 2.0. It's available in Java EE 8
    @NotBlank(message = "Department name is mandatory!")
    private String name;

    @NotBlank(message = "address is mandatory")
    private String address;

    @NotBlank(message = "code is mandatory")
    private String code;

    @JsonManagedReference
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;

    public Department(String name, String address, String code) {
        this.name = name;
        this.address = address;
        this.code = code;
    }
}
