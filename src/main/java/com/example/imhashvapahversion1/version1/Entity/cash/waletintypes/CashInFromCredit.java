package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CashInFromCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public CashInFromCredit() {
    }

}
