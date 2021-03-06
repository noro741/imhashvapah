package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletOut;

import javax.persistence.*;
import javax.validation.Valid;
@Entity
public class CashOutForBankSpending implements GetWaletOut {
    @Id
    @GeneratedValue
    private Long id;


    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletOut walletOut;
    @ManyToOne
    private Organization organization;

    public CashOutForBankSpending() {
    }

    public CashOutForBankSpending(WalletOut walletOut, Organization organization) {
        this.walletOut = walletOut;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WalletOut getWalletOut() {
        return walletOut;
    }

    public void setWalletOut(WalletOut walletOut) {
        this.walletOut = walletOut;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }



    @Override
    public Long getCashOutId() {
        return id;
    }

    @Override
    public WalletOut getWalletOutImpl() {
        return walletOut;
    }

    @Override
    public GeneralMethods getSupplier() {
        return null;
    }
}
