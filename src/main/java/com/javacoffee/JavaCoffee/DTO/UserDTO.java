package com.javacoffee.JavaCoffee.DTO;

/**
 We use the UserDto to validate the user registration form. This DTO is annotated using Hibernate-Validation annotations which validate trivial fields on empty and our own custom @FieldMatch annotations which validates if the password is equal to the confirm password and the email address field is equal to the confirm email address field.
 **/

import com.javacoffee.JavaCoffee.validation.FieldMatch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch.List( { @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")})
public class UserDTO {

    @NotEmpty
    private String userName;

    @Pattern(regexp = "[A-Za-z]+$", message = "Only alphabetic allowed")
    private String firstName;

    @Pattern(regexp = "[A-Za-z]+$", message = "Only alphabetic allowed")
    private String lastName;

    @Column(unique = true)
    @Email
    private String email;

    private String phone;

    @Pattern(regexp = "[0-9]{5}$", message = "Zip code wrong format")
    private String zip;

    @NotEmpty(message = "Required")
    private String password;

    @NotEmpty(message = "Required")
    private String matchingPassword;

    public UserDTO(@NotEmpty String userName, @Pattern(regexp = "[A-Za-z]+$", message = "Only alphabetic allowed") String firstName, @Pattern(regexp = "[A-Za-z]+$", message = "Only alphabetic allowed") String lastName, @Email String email, String phone, @Pattern(regexp = "[0-9]{5}$", message = "Zip code wrong format") String zip, @NotEmpty(message = "Required") String password, @NotEmpty(message = "Required") String matchingPassword) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.zip = zip;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }
}

