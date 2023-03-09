package com.javacoffee.JavaCoffee.securityPackage.entity;

//import com.javacoffee.JavaCoffee.shoppingCartPackage.entity.ShoppingCart;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Igor Adulyan
 */

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

    //constructor used for testing purposes
    public User(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


}
