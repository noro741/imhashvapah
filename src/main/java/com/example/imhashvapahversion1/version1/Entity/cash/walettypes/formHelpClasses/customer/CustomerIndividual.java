package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class CustomerIndividual implements GeneralMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Անվան դաշտը անպայման է լրացնել ")
    private String firstName;
    @NotEmpty(message = "Ազգանուն դաշտը անպայման է լրացնել ")
    private String lastName;
    @ManyToOne
    private Organization organization ;

    public CustomerIndividual() {
    }

    public CustomerIndividual(String firstName, String lastName, Organization organization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return firstName +" "+ lastName;
    }

    @Override
    public Long getInnerId() {
        return null;
    }

    @Override
    public Long getClientOrganizationId() {
        return null;
    }

    @Override
    public Long getIndividualId() {
        return getId();
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getHvhh() {
        return null;
    }

    @Override
    public String getHch() {
        return null;
    }

    @Override
    public String getType() {
        return "CustomerIndividual";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
