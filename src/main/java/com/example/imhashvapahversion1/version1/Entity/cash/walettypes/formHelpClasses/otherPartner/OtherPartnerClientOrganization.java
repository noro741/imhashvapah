package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.service.ClientOrganizationService;
import com.example.imhashvapahversion1.version1.validate.Unique;
import com.example.imhashvapahversion1.version1.validate.test.EqualFields;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@EqualFields(id = "id", uniqueField = "clientOrganizationName",hvhh = "",message = "")
public class OtherPartnerClientOrganization implements GeneralMethods{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Հարկավոր է մուտքագրել կազմակերպության անվանումը:")
    private String clientOrganizationName;
    @ManyToOne
    private Organization organization;

    public OtherPartnerClientOrganization(String clientOrganizationName, Organization organization) {
        this.clientOrganizationName = clientOrganizationName;
        this.organization = organization;
    }

    public OtherPartnerClientOrganization() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return clientOrganizationName;
    }

    @Override
    public Long getInnerId() {
        return null;
    }

    @Override
    public Long getClientOrganizationId() {
        return id;
    }

    @Override
    public Long getIndividualId() {
        return null;
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
        return "OtherPartnerClientOrganization";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientOrganizationName() {
        return clientOrganizationName;
    }

    public void setClientOrganizationName(String clientOrganizationName) {
        this.clientOrganizationName = clientOrganizationName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
