/*
package com.javacoffee.JavaCoffee.paymentPackage.entity;


import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String type;
    private String cardName;
    private String cardNumber;
    private int expirationMonth;
    private int expirationYear;
    private int cvc;
    private String holderName;
    private boolean defaultPayment;



    //This code defines a @ManyToOne relationship between the UserPayment entity and the User entity.
    //The foreign key in the user_payment table that points to the user table should be named user_id.
    //This means that each UserPayment instance belongs to one User instance,
    //and each User instance can have multiple associated UserPayment instances.

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    //* UserPayment entity is the owning side of the relationship, and the UserBilling entity is the inverse side.
    //* The mappedBy attribute is used to specify the owning side of the relationship.
    //* the mappedBy attribute is used to indicate that the relationship between the UserPayment entity
    //* and the UserBilling entity is bidirectional, and that the
    //* userBilling field on the UserPayment side is the non-owning side of the relationship.
    //* When mappedBy is used, it means that the relationship is defined and mapped by the other side of the association,
    //* which is the UserBilling entity in this case.
    //* Therefore, any changes made to the relationship from the UserPayment entity will not be persisted in the database.
    //* Instead, the changes should be made from the UserBilling entity.
    //* Additionally, the CascadeType.ALL attribute specifies that any changes made to the UserPayment entity
    //* should be cascaded to the associated UserBilling entity.
    //*
    @OneToOne(cascade=CascadeType.ALL, mappedBy ="userPayment")
    private UserBilling userBilling;
}
*/