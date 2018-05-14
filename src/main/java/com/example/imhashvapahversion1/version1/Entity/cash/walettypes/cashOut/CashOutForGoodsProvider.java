package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierIndividual;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import sun.text.SupplementaryCharacterData;


import javax.persistence.*;
import javax.validation.Valid;

import java.util.Date;


@Entity
public class CashOutForGoodsProvider {
    @Id
    @GeneratedValue
    private Long id;
    private String supplierName;
    //Բանկային միջնորդավճար
    private String bankCommissions;
    //Պայմանագրի ամսաթիվ
    private Date contractDate;
    //Պայմանագրի համար
    private String contractNumber;
    //Նշումներ
    private String note;
    @Transient
    private Long supplierId;
    @Transient
    private String supplierType;

    @ManyToOne
    private CompanySupplier companySupplier;
    @ManyToOne
    private IndividualSupplier individualSupplier;
    @ManyToOne
    private PrivateEntrepreneurSupplier privateEntrepreneurSupplier;
    @ManyToOne
    private Organization organization;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletOut walletOut;

    public CashOutForGoodsProvider() {
    }

    public CashOutForGoodsProvider(String supplierName, String bankCommissions, Date contractDate, String contractNumber, String note, Long supplierId, String supplierType, CompanySupplier companySupplier, IndividualSupplier individualSupplier, PrivateEntrepreneurSupplier privateEntrepreneurSupplier, Organization organization, WalletOut walletOut) {
        this.supplierName = supplierName;
        this.bankCommissions = bankCommissions;
        this.contractDate = contractDate;
        this.contractNumber = contractNumber;
        this.note = note;
        this.supplierId = supplierId;
        this.supplierType = supplierType;
        this.companySupplier = companySupplier;
        this.individualSupplier = individualSupplier;
        this.privateEntrepreneurSupplier = privateEntrepreneurSupplier;
        this.organization = organization;
        this.walletOut = walletOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public CompanySupplier getCompanySupplier() {
        return companySupplier;
    }

    public void setCompanySupplier(CompanySupplier companySupplier) {
        this.companySupplier = companySupplier;
    }

    public IndividualSupplier getIndividualSupplier() {
        return individualSupplier;
    }

    public void setIndividualSupplier(IndividualSupplier individualSupplier) {
        this.individualSupplier = individualSupplier;
    }

    public PrivateEntrepreneurSupplier getPrivateEntrepreneurSupplier() {
        return privateEntrepreneurSupplier;
    }

    public void setPrivateEntrepreneurSupplier(PrivateEntrepreneurSupplier privateEntrepreneurSupplier) {
        this.privateEntrepreneurSupplier = privateEntrepreneurSupplier;
    }

    public String getBankCommissions() {
        return bankCommissions;
    }

    public void setBankCommissions(String bankCommissions) {
        this.bankCommissions = bankCommissions;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public WalletOut getWalletOut() {
        return walletOut;
    }

    public void setWalletOut(WalletOut walletOut) {
        this.walletOut = walletOut;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }
}
