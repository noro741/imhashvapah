package com.example.imhashvapahversion1.version1.Entity.cash;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;

import javax.persistence.Entity;

import javax.validation.constraints.NotNull;

import java.util.Date;


@Entity
public class WalletIn {
    @Id
    @GeneratedValue
    private Long id;

    private String inType;

    @NotNull (message ="Հարկավոր է նշել մուտքի ամսաթիվը:")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inDate ;
    @NotEmpty(message ="Հարկավոր է նշել մուտքի գումարը:")
    private String inCash;
    private String note;
    @ManyToOne
    Organization organization;

    public WalletIn() {
    }

    public WalletIn(String inType, Date inDate, String inCash, String note, Organization organization) {
        this.inType = inType;
        this.inDate = inDate;
        this.inCash = inCash;
        this.note = note;
        this.organization = organization;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Date getInDate() {
        return inDate;
    }



    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getInCash() {
        return inCash;
    }

    public void setInCash(String inCash) {
        this.inCash = inCash;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType;
    }

}
