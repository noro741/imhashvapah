package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes;

import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CashInFromBankAccount extends WalletIn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bankAccount;

    public CashInFromBankAccount() {
    }
}
