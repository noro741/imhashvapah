package com.example.imhashvapahversion1.version1.repository.cashIn;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn.CashInFromPointOfSale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.ArrayList;

public interface CashInFromPointOfSaleRepository  extends CrudRepository<CashInFromPointOfSale,Long>{
    @Query("Select c from CashInFromPointOfSale c INNER JOIN c.walletIn w WHERE w.inDate>=:startDate ")
    ArrayList findByRangeStart(@Param("startDate") Date startDate);
    @Query( "Select c from CashInFromPointOfSale c INNER JOIN c.walletIn w WHERE w.inDate>=:startDate and w.inDate<=:endDate ")
    ArrayList findByRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query( "Select c from CashInFromPointOfSale c INNER JOIN c.walletIn w WHERE w.inDate<=:endDate ")
    ArrayList  findByEnd(@Param("endDate")Date endDate);
}
