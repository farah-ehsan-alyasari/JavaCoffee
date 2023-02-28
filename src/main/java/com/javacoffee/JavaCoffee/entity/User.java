package com.javacoffee.JavaCoffee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(nullable = false)
    private Long id;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    //@Column(nullable = false)
    private String email;

    private String phoneNumber;

    private String zip;

    //private boolean enabled = true;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="users_roles", joinColumns = @JoinColumn(name="user_id"),
                inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

    //Constructor for everything without Id
    public User(String userName, String firstName, String lastName,
                String email, String phoneNumber,  String zip, String password){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.zip = zip;
    }
}
