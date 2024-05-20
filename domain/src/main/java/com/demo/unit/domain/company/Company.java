package com.demo.unit.domain.company;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.util.Assert;

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
        Assert.isTrue(numberOfEmployees + delta >= 0,
                "numberOfEmployeses is over then 0");
        numberOfEmployees += delta;
    }

    public boolean isEmailCorporate(String email) {
        String emailDomain = email.split("@")[1];
        return companyDomainName.equals(emailDomain);
    }
}
