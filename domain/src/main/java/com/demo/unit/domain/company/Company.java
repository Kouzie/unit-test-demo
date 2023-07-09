package com.demo.unit.domain.company;

import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Company {
    @Id
    private String companyDomainName;
    private Integer numberOfEmployees;

    protected Company() {
    }

    public Company(String companyDomainName, Integer numberOfEmployees) {
        this.companyDomainName = companyDomainName;
        this.numberOfEmployees = numberOfEmployees;
    }

    public void changeNumberOfEmployees(int delta) {
        Assert.isTrue(numberOfEmployees + delta >= 0);
        numberOfEmployees += delta;
    }

    public boolean isEmailCorporate(String email) {
        String emailDomain = email.split("@")[1];
        return companyDomainName.equals(emailDomain);
    }
}
