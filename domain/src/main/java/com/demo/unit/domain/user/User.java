package com.demo.unit.domain.user;

import com.demo.unit.domain.company.Company;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private UserType type;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_domain" )
    private Company company;

    protected User() {
    }

    public User(String email, UserType type, String name, Company company) {
        this.email = email;
        this.type = type;
        this.name = name;
        this.company = company;
    }

    public void updateName(String name) {
        this.name = normalizeName(name);
    }

    private String normalizeName(String name) {
        String result = name == null ? "" : name.trim();
        if (result.length() > 50)
            return result.substring(0, 50);
        return result;
    }

    public void changeEmail(String newEmail) {
        if (this.email.equals(newEmail))
            return;
        UserType newType = company.isEmailCorporate(newEmail)
                ? UserType.EMPLOYEE
                : UserType.CUSTOMER;

        if (this.type != newType) {
            int delta = newType == UserType.EMPLOYEE ? 1 : -1;
            company.changeNumberOfEmployees(delta);
        }
        this.email = newEmail;
        this.type = newType;
    }

}
