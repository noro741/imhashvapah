package com.example.imhashvapahversion1.version1.controller.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;

import com.example.imhashvapahversion1.version1.Entity.cash.*;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.*;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn.*;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.*;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer.CustomerClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer.CustomerIndividual;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner.OtherPartnerClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner.OtherPartnerIndividual;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierIndividual;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.*;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.OtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.Supplier;
import com.example.imhashvapahversion1.version1.Entity.showClasses.CashDtailsShow;
import com.example.imhashvapahversion1.version1.Entity.showClasses.CashInShow;
import com.example.imhashvapahversion1.version1.Entity.showClasses.DebtDetailsShow;
import com.example.imhashvapahversion1.version1.Entity.showClasses.FinancialMeans;
import com.example.imhashvapahversion1.version1.controller.BaseController;
import com.example.imhashvapahversion1.version1.repository.*;
import com.example.imhashvapahversion1.version1.repository.cashIn.*;
import com.example.imhashvapahversion1.version1.repository.cashOut.*;

import com.example.imhashvapahversion1.version1.repository.customer.*;
import com.example.imhashvapahversion1.version1.repository.otherpartners.*;

import com.example.imhashvapahversion1.version1.repository.suppliers.CompanySupplierRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.IndividualSupplierRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.SupplierClientOrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.SupplierIndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;

import java.util.*;

@Controller
@RequestMapping(value="/account/cash")
@SessionAttributes({"modelTrans"})
public class CashController extends BaseController {
    @Autowired
    CashInFromBankAccountRepository cashInFromBankAccountRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    CashInFromSaleOfGoodsRepository cashInFromSaleOfGoodsRepository;

    @Autowired
    IndividualSupplierRepository individualSupplierRepository;
    @Autowired
    CompanySupplierRepository companySupplierRepository;

            @Autowired
    CompanyCustomerRepository companyCustomerRepository;
            @Autowired
    IndividualCustomerRepository individualCustomerRepository;

            @Autowired
    CustomerClientOrganizationRepository customerClientOrganizationRepository;
            @Autowired
            IndividualOtherPartnerRepository individualOtherPartnerRepository;
    @Autowired
    PrivateEntrepreneurOtherPartnerRepository privateEntrepreneurOtherPartnerRepository;
    @Autowired
    CustomerIndividualRepository customerIndividualRepository;
    @Autowired
    OtherPartnerClientOrganizationRepository otherPartnerClientOrganizationRepository;
    @Autowired
    OtherPartnerIndividualRepository otherPartnerIndividualRepository;
    @Autowired
    SupplierClientOrganizationRepository supplierClientOrganizationRepository;
    @Autowired
    SupplierIndividualRepository supplierIndividualRepository;
    @Autowired
    CashInFromPointOfSaleRepository cashInFromPointOfSaleRepository;
    @Autowired
    CashInFromServiceProvisionRepository cashInFromServiceProvisionRepository;
    @Autowired
    PrivateEntrepreneurCustomerRepository privateEntrepreneurCustomerRepository;
    @Autowired
    CompanyOtherPartnerRepository companyOtherPartnerRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    CashInFromLoanRepository cashInFromLoanRepository;
    @Autowired
    CashInFromCreditRepository cashInFromCreditRepository;
    @Autowired
    WalletOutRepository walletOutRepository;
    @Autowired
    WalletDataRepository walletDataRepository;
    @Autowired
    CashOutForBankAccountRepository cashOutForBankAccountRepository;
    @Autowired
    CashOutForCreditPaymentRepository cashOutForCreditPaymentRepository;
    @Autowired
    CashOutForGoodsProviderRepository cashOutForGoodsProviderRepository;
    @Autowired
    CashOutForLoanPaymentRepository cashOutForLoanPaymentRepository;
    @Autowired
    CashOutForRedemptionPercentRepository cashOutForRedemptionPercentRepository;
    @Autowired
    CashOutForTaxRepository cashOutForTaxRepository;
    @Autowired
    CashOutForRentRepository cashOutForRentRepository;
    @Autowired
    CashOutForSalaryRepository cashOutForSalaryRepository;
    @Autowired
    CashOutForSerivceProviderRepository cashOutForSerivceProviderRepository;
    @Autowired
    CashOutForOtherExpensesRepository cashOutForOtherExpensesRepository;
    @Autowired
    CashOutForBankSpendingRepository cashOutForBankSpendingRepository;
    @Autowired
    WalletInRepository walletInRepository;



    private List colleaguesList= new ArrayList() ;
  private   List customerList = new ArrayList();

    @InitBinder()
    public void registerConversionServices(WebDataBinder dataBinder) {
        dataBinder.addCustomFormatter(new Formatter<Organization>() {

            @Override
            public String print(Organization organization, Locale locale) {
                return organization.getId().toString();
            }

            @Override
            public Organization parse(String organizationId, Locale locale) throws ParseException {
                return organizationRepository.findOne(Long.parseLong(organizationId));
            }

        });
    }



    @GetMapping(value = "")
    public ModelAndView cash(ModelAndView modelAndView,HttpSession httpSession) {

        if(walletDataRepository.count()<1){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.noWalletData);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            return modelAndView;
        }
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        return modelAndView;
    }
    @GetMapping(value = "/details")
    public ModelAndView cashDetails(ModelAndView modelAndView,HttpSession httpSession) {


        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashDtails);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        return modelAndView;
    }

    @PostMapping(value = "/details/show")
    public @ResponseBody ArrayList cashDetailsShow(@RequestBody DateRange dateRang ) {
        ArrayList<CashDtailsShow> cashDtailsShows = new ArrayList<>();
        CashDtailsShow cashDtailsShow;

        List<CashInFromBankAccount> cashInFromBankAccounts = cashInFromBankAccountRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashInFromCredit> cashInFromCredits = cashInFromCreditRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashInFromLoan> cashInFromLoans = cashInFromLoanRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashInFromPointOfSale> cashInFromPointOfSales = cashInFromPointOfSaleRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashInFromSaleOfGoods> cashInFromSaleOfGoodss = cashInFromSaleOfGoodsRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashInFromServiceProvision> cashInFromServiceProvisions = cashInFromServiceProvisionRepository.findByRange(dateRang.getStart(),dateRang.getEnd());

        List<CashOutForBankAccount>       cashOutForBankAccounts=     cashOutForBankAccountRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashOutForBankSpending>      cashOutForBankSpendings =   cashOutForBankSpendingRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashOutForCreditPayment>     cashOutForCreditPayments=   cashOutForCreditPaymentRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashOutForGoodsProvider>     cashOutForGoodsProviders=   cashOutForGoodsProviderRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashOutForLoanPayment>       cashOutForLoanPayments =    cashOutForLoanPaymentRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashOutForOtherExpenses>     cashOutForOtherExpensess=   cashOutForOtherExpensesRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashOutForRedemptionPercent> cashOutForRedemptionPercents=cashOutForRedemptionPercentRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashOutForRent>              cashOutForRents    =        cashOutForRentRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashOutForSerivceProvider>   cashOutForSerivceProviders= cashOutForSerivceProviderRepository.findByRange(dateRang.getStart(),dateRang.getEnd());
        List<CashOutForTax>               cashOutForTaxs  =           cashOutForTaxRepository.findByRange(dateRang.getStart(),dateRang.getEnd());

        if (dateRang.getStart() != null && dateRang.getEnd() != null) {

            for (CashInFromBankAccount cashInFromBankAccount : cashInFromBankAccounts) {

                cashDtailsShow = new CashDtailsShow(cashInFromBankAccount.getWalletIn().getInDate(),
                        cashInFromBankAccount.getId(),
                        cashInFromBankAccount.getWalletIn().getInCash(),
                        null,
                        cashInFromBankAccount.getWalletIn().getInType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }
            for (CashInFromCredit cashInFromCredit : cashInFromCredits) {

                cashDtailsShow = new CashDtailsShow(cashInFromCredit.getWalletIn().getInDate(),
                        cashInFromCredit.getId(),
                        cashInFromCredit.getWalletIn().getInCash(),
                        null,
                        cashInFromCredit.getWalletIn().getInType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }
            for (CashInFromLoan cashInFromLoan : cashInFromLoans) {

                cashDtailsShow = new CashDtailsShow(cashInFromLoan.getWalletIn().getInDate(),
                        cashInFromLoan.getId(),
                        cashInFromLoan.getWalletIn().getInCash(),
                        null,
                        cashInFromLoan.getWalletIn().getInType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }
            for (CashInFromPointOfSale cashInFromPointOfSale : cashInFromPointOfSales) {

                cashDtailsShow = new CashDtailsShow(cashInFromPointOfSale.getWalletIn().getInDate(),
                        cashInFromPointOfSale.getId(),
                        cashInFromPointOfSale.getWalletIn().getInCash(),
                        null,
                        cashInFromPointOfSale.getWalletIn().getInType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }
            for (CashInFromSaleOfGoods cashInFromSaleOfGoods : cashInFromSaleOfGoodss) {

                cashDtailsShow = new CashDtailsShow(cashInFromSaleOfGoods.getWalletIn().getInDate(),
                        cashInFromSaleOfGoods.getId(),
                        cashInFromSaleOfGoods.getWalletIn().getInCash(),
                        null,
                        cashInFromSaleOfGoods.getWalletIn().getInType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }
            for (CashInFromServiceProvision cashInFromServiceProvision : cashInFromServiceProvisions) {

                cashDtailsShow = new CashDtailsShow(cashInFromServiceProvision.getWalletIn().getInDate(),
                        cashInFromServiceProvision.getId(),
                        cashInFromServiceProvision.getWalletIn().getInCash(),
                        null,
                        cashInFromServiceProvision.getWalletIn().getInType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }

            for (CashOutForBankAccount cashOutForBankAccount : cashOutForBankAccounts) {

                cashDtailsShow = new CashDtailsShow(cashOutForBankAccount.getWalletOut().getOutDate(),
                        cashOutForBankAccount.getId(),
                       null,
                        cashOutForBankAccount.getWalletOut().getOutCash(),
                        cashOutForBankAccount.getWalletOut().getOutType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }
            for (CashOutForBankSpending cashOutForBankSpending : cashOutForBankSpendings) {

                cashDtailsShow = new CashDtailsShow(cashOutForBankSpending.getWalletOut().getOutDate(),
                        cashOutForBankSpending.getId(),
                       null,
                        cashOutForBankSpending.getWalletOut().getOutCash(),
                        cashOutForBankSpending.getWalletOut().getOutType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }for (CashOutForCreditPayment cashOutForCreditPayment : cashOutForCreditPayments) {

                cashDtailsShow = new CashDtailsShow(cashOutForCreditPayment.getWalletOut().getOutDate(),
                        cashOutForCreditPayment.getId(),
                       null,
                        cashOutForCreditPayment.getWalletOut().getOutCash(),
                        cashOutForCreditPayment.getWalletOut().getOutType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }
            for (CashOutForGoodsProvider cashOutForGoodsProvider : cashOutForGoodsProviders) {

                cashDtailsShow = new CashDtailsShow(cashOutForGoodsProvider.getWalletOut().getOutDate(),
                        cashOutForGoodsProvider.getId(),
                       null,
                        cashOutForGoodsProvider.getWalletOut().getOutCash(),
                        cashOutForGoodsProvider.getWalletOut().getOutType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }for (CashOutForLoanPayment cashOutForLoanPayment : cashOutForLoanPayments) {

                cashDtailsShow = new CashDtailsShow(cashOutForLoanPayment.getWalletOut().getOutDate(),
                        cashOutForLoanPayment.getId(),
                       null,
                        cashOutForLoanPayment.getWalletOut().getOutCash(),
                        cashOutForLoanPayment.getWalletOut().getOutType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }for (CashOutForOtherExpenses cashOutForOtherExpenses : cashOutForOtherExpensess) {

                cashDtailsShow = new CashDtailsShow(cashOutForOtherExpenses.getWalletOut().getOutDate(),
                        cashOutForOtherExpenses.getId(),
                       null,
                        cashOutForOtherExpenses.getWalletOut().getOutCash(),
                        cashOutForOtherExpenses.getWalletOut().getOutType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }
            for (CashOutForRedemptionPercent cashOutForRedemptionPercent : cashOutForRedemptionPercents) {

                cashDtailsShow = new CashDtailsShow(cashOutForRedemptionPercent.getWalletOut().getOutDate(),
                        cashOutForRedemptionPercent.getId(),
                       null,
                        cashOutForRedemptionPercent.getWalletOut().getOutCash(),
                        cashOutForRedemptionPercent.getWalletOut().getOutType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }for (CashOutForRent cashOutForRent : cashOutForRents) {

                cashDtailsShow = new CashDtailsShow(cashOutForRent.getWalletOut().getOutDate(),
                        cashOutForRent.getId(),
                       null,
                        cashOutForRent.getWalletOut().getOutCash(),
                        cashOutForRent.getWalletOut().getOutType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }for (CashOutForSerivceProvider cashOutForSerivceProvider : cashOutForSerivceProviders) {

                cashDtailsShow = new CashDtailsShow(cashOutForSerivceProvider.getWalletOut().getOutDate(),
                        cashOutForSerivceProvider.getId(),
                       null,
                        cashOutForSerivceProvider.getWalletOut().getOutCash(),
                        cashOutForSerivceProvider.getWalletOut().getOutType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }for (CashOutForTax cashOutForTax : cashOutForTaxs) {

                cashDtailsShow = new CashDtailsShow(cashOutForTax.getWalletOut().getOutDate(),
                        cashOutForTax.getId(),
                       null,
                        cashOutForTax.getWalletOut().getOutCash(),
                        cashOutForTax.getWalletOut().getOutType()
                );
                cashDtailsShows.add(cashDtailsShow);
            }





            Collections.sort(cashDtailsShows);
            return cashDtailsShows;
        }

        return cashDtailsShows;
    }

    @PostMapping(value = "/show")
    public @ResponseBody ArrayList cashShow(@RequestBody DateRange dateRange ,HttpSession httpSession) {

        ArrayList showResult = new ArrayList();
        FinancialMeans financialMeans = new FinancialMeans();
        Long openingBalanceSum = 0L;
        Long inSum = 0L;
        Long outSum = 0L;
        Long finalBalanceSum = 0L;

         if (dateRange.getStart() != null && dateRange.getEnd() == null) {

             /* openingPalanceSum = walletInRepository.returnSumOfInsByStart(dateRange.getStart());
             inSum =walletInRepository.returnAllSumOfIns()-openingPalanceSum;

                financialMeans.setOpeningPalance(openingPalanceSum);
                financialMeans.setIn(inSum);
                financialMeans.setName("Դրամարկղ");
             showResult.add(financialMeans);*/

             return showResult;

         } else if (dateRange.getStart() == null && dateRange.getEnd() != null) {
            /* openingPalanceSum = walletInRepository.returnSumOfInsByEnd(dateRange.getEnd());
             inSum =walletInRepository.returnAllSumOfIns()-openingPalanceSum;

             financialMeans.setOpeningPalance(openingPalanceSum);
             financialMeans.setIn(inSum);
             financialMeans.setName("Դրամարկղ");
             showResult.add(financialMeans);*/
             return showResult;

        }else if (dateRange.getStart() != null && dateRange.getEnd() != null) {
             WalletData walletData = walletDataRepository.findOne(1L);
             openingBalanceSum = walletData.getStartBalance();
            Long wInTemp= walletInRepository.returnSumOfInsForOpeningBalance(dateRange.getStart())==null?0:walletInRepository.returnSumOfInsForOpeningBalance(dateRange.getStart()) ;
             Long wOutTemp = walletOutRepository.returnSumOfOutsForFinalBalanceSum(dateRange.getStart())==null?0:walletOutRepository.returnSumOfOutsForFinalBalanceSum(dateRange.getStart());



             openingBalanceSum = openingBalanceSum+(wInTemp-wOutTemp);
             inSum = walletInRepository.returnSumOfInsByRange(dateRange.getStart(),dateRange.getEnd())==null?0:walletInRepository.returnSumOfInsByRange(dateRange.getStart(),dateRange.getEnd());

             outSum = walletOutRepository.returnSumOfOutsByRange(dateRange.getStart(),dateRange.getEnd())==null?0:walletOutRepository.returnSumOfOutsByRange(dateRange.getStart(),dateRange.getEnd());
             finalBalanceSum = openingBalanceSum +(inSum-outSum);
             financialMeans.setOpeningBalance(openingBalanceSum);
             financialMeans.setIn(inSum);
             financialMeans.setOut(outSum);
             financialMeans.setFinalBalance(finalBalanceSum);

             financialMeans.setName("Դրամարկղ");
             showResult.add(financialMeans);

             return showResult;

        }

        return showResult;
    }

    @GetMapping(value = "/cashdesk")
    public ModelAndView cashdesk( ModelAndView modelAndView, HttpSession httpSession) {

        WalletData walletData = new WalletData();
        modelAndView.setViewName("app/app");
        walletData.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        walletData.setStartingNumberCashBookPapers(1);
        walletData.setCashBbookPapers(50);
        walletData.setOutNumbering(1);
        walletData.setStartingNumbering(1);
        walletData.setWalletName("Դրամարկղ");
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashdeskFragment);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "/cashdesk")
    public ModelAndView cashdeskCreate(@Valid WalletData walletData, BindingResult bindingResult, ModelAndView modelAndView,HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashdeskFragment);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            modelAndView.addObject("walletData", walletData);
            return modelAndView;
        }


        modelAndView.setViewName("app/app");
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        walletDataRepository.save(walletData);

        return modelAndView;
    }

    @GetMapping(value = "/bankaccount")
    public ModelAndView bankАccount( ModelAndView modelAndView,HttpSession httpSession) {


        if(walletDataRepository.count()<1){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.noWalletData);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            return modelAndView;
        }
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.bankaccount);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }
    @GetMapping(value = "/bankaccount/create")
    public ModelAndView bankАccountCreate( ModelAndView modelAndView,HttpSession httpSession) {
        if(walletDataRepository.count()<1){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.noWalletData);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            return modelAndView;
        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("bankAccount", bankAccount);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.bankaccountCreate);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "bankaccount/create/getbankname")
    public @ResponseBody String getBankName(@RequestBody Integer accountStartNumbers) {

        if (11500 >= accountStartNumbers && accountStartNumbers <= 11554)
            return "ՀԱՅԲԻԶՆԵՍԲԱՆԿ ՓԲԸ ";
        if (11800 >= accountStartNumbers && accountStartNumbers <= 11817)
            return "ԱՆԵԼԻՔ ԲԱՆԿ ՓԲԸ";
        if (15100 >= accountStartNumbers && accountStartNumbers <= 15170)
            return "ԱՐԱՐԱՏԲԱՆԿ ԲԲԸ";
        if (15700 >= accountStartNumbers && accountStartNumbers <= 15715)
            return "ԱՄԵՐԻԱԲԱՆԿ ՓԲԸ";

        if (16000 >= accountStartNumbers && accountStartNumbers <= 16104)
            return "ՎՏԲ-ՀԱՅԱՍՏԱՆ ԲԱՆԿ ՓԲԸ";


        if (16300 >= accountStartNumbers && accountStartNumbers <= 16334)
            return "ՀԱՅԷԿՈՆՈՄԲԱՆԿ ԲԲԸ";


        if (16335 >= accountStartNumbers && accountStartNumbers <= 16345)
            return "ՀԷԲ Ագարակ մասնաճյուղ";


        if (16346 >= accountStartNumbers && accountStartNumbers <= 16367)
            return "ՀԱՅԷԿՈՆՈՄԲԱՆԿ ԲԲԸ";



        if (16600 >= accountStartNumbers && accountStartNumbers <= 16612)
            return "ԷՎՈԿԱԲԱՆԿ ՓԲԸ";


        if (17500 >= accountStartNumbers && accountStartNumbers <= 17505)
            return "ԲՏԱ Ինվեստ Բանկ";


        if (18100 >= accountStartNumbers && accountStartNumbers <= 18102)
            return "Զարգացման Հայկական  բանկ ԲԲԸ";

        if (19300 >= accountStartNumbers && accountStartNumbers <= 19337)
            return "ԿՈՆՎԵՐՍ ԲԱՆԿ ՓԲԸ";
        if (20500 >= accountStartNumbers && accountStartNumbers <= 20523)
            return "ԻՆԵԿՈԲԱՆԿ ՓԲԸ";
        if (20800 == accountStartNumbers)
            return "ՄԵԼԼԱԹ ԲԱՆԿ ՓԲԸ ";

        if (21400 >= accountStartNumbers && accountStartNumbers <= 21405)
            return "ԲԻԲԼՈՍ ԲԱՆԿ ԱՐՄԵՆԻԱ ՓԲԸ";
        if (21700 >= accountStartNumbers && accountStartNumbers <= 21713)
            return "Էյչ-Էս-Բի-Սի ԲԱՆԿ ՀԱՅԱՍՏԱՆ ՓԲԸ";
        if (22000 >= accountStartNumbers && accountStartNumbers <= 22058)
            return "ԱԿԲԱ-ԿՐԵԴԻՏ ԱԳՐԻԿՈԼ ԲԱՆԿ ՓԲԸ";
        if (22300 >= accountStartNumbers && accountStartNumbers <= 22324)
            return "ԱՐՑԱԽԲԱՆԿ ՓԲԸ";

        if (23500 == accountStartNumbers)
            return "Կասկադ Բանկ ՓԲԸ ՓԲԸ";

        if (23800 >= accountStartNumbers && accountStartNumbers <= 23810)
            return "Առէկսիմբանկ ՓԲԸ";
        if (24100 >= accountStartNumbers && accountStartNumbers <= 24151)
            return "ՅՈՒՆԻԲԱՆԿ ԲԲԸ";
        if (24400 == accountStartNumbers )
            return "ՄԻՋՊԵՏԱԿԱՆ ԲԱՆԿ ՆԵՐԿԱՅԱՑՈՒՑՉՈՒԹՅՈՒՆ ՀԱՅԱՍՏԱՆՈՒՄ";
        if (24700 >= accountStartNumbers && accountStartNumbers <= 25000)
            return "ԱՐԴՇԻՆԲԱՆԿ ՓԲԸ";
        if ( 25300== accountStartNumbers )
            return "Պրոկրեդիտբանկ ՓԲԸ";

        if (80100 == accountStartNumbers)
            return "ԱՐՔԱ";
        if (80200 == accountStartNumbers)
            return "Արմենիան Քարդ";
        if (90000 == accountStartNumbers)
            return "Կենտրոնական գանձապետարան";
        if (90001 == accountStartNumbers)
            return "Երևանի թիվ 1 ՏԳԲ";
        if (90002 == accountStartNumbers)
            return "Երևանի թիվ 4 ՏԳԲ";
        if (90003 == accountStartNumbers)
            return "Երևանի թիվ 3 ՏԳԲ";
        if (90005 == accountStartNumbers)
            return "Երևանի թիվ 2 ՏԳԲ";
        if (90010 == accountStartNumbers)
            return "Աբովյանի գանձապետական բաժամունք";
        if (90011 == accountStartNumbers)
            return "Եղվարդի քաղաքային գանձապետական բաժամունք";
        if (90012 == accountStartNumbers)
            return "Հրազդանի գանձապետական բաժամունք";
        if (90013 == accountStartNumbers)
            return "Չարենցավանի գանձապետական բաժամունք";
        if (90014 == accountStartNumbers)
            return "Մարտունու գանձապետական բաժամունք";
        if (90015 == accountStartNumbers)
            return "Վարդենիսի գանձապետական բաժամունք";
        if (90016 == accountStartNumbers)
            return "Սևանի գանձապետական բաժամունք";
        if (90017 == accountStartNumbers)
            return "Գավառի գանձապետական բաժամունք";
        if (90018 == accountStartNumbers)
            return "Ճամբարակի գանձապետական բաժամունք";
        if (90019 == accountStartNumbers)
            return "Աշոցկի գանձապետական բաժամունք";
        if (90020 == accountStartNumbers)
            return "Արթիկի գանձապետական բաժամունք";
        if (90021 == accountStartNumbers)
            return "Գյումրիի գանձապետական բաժամունք";
        if (90022 == accountStartNumbers)
            return "Ամասիայի գանձապետական բաժամունք";
        if (90023 == accountStartNumbers)
            return "Վանաձորի գանձապետական բաժամունք";
        if (90024 == accountStartNumbers)
            return "Սպիտակի գանձապետական բաժամունք";
        if (90025 == accountStartNumbers)
            return "Ստեփանավանի քաղաքային գանձապետական բաժամունք";
        if (90026 == accountStartNumbers)
            return "Ալավերդու գանձապետական բաժամունք";
        if (90027 == accountStartNumbers)
            return "Տաշիրի քաղաքային գանձապետական բաժամունք";
        if (90028 == accountStartNumbers)
            return "Գորիսի գանձապետական բաժամունք";
        if (90029 == accountStartNumbers)
            return "Սիսիանի գանձապետական բաժամունք";
        if (90030 == accountStartNumbers)
            return "Մեղրու գանձապետական բաժամունք";
        if (90031 == accountStartNumbers)
            return "Կապանի գանձապետական բաժամունք";
        if (90032 == accountStartNumbers)
            return "Վաղարշապատի գանձապետական բաժամունք";
        if (90033 == accountStartNumbers)
            return "Արմավիրի գանձապետական բաժամունք";
        if (90034 == accountStartNumbers)
            return "Վայքի գանձապետական բաժամունք";

        if (90035 == accountStartNumbers)
            return "Եղեգնաձորի գանձապետական բաժամունք";

        if (90036 == accountStartNumbers)
            return "Ջերմուկի գանձապետական բաժամունք";

        if (90037 == accountStartNumbers)
            return "Իջևանի գանձապետական բաժամունք";

        if (90038 == accountStartNumbers)
            return "Բերդի գանձապետական բաժամունք";

        if (90039 == accountStartNumbers)
            return "Նոյեմբերանի գանձապետական բաժամունք";

        if (90040 == accountStartNumbers)
            return "Դիլիջանի գանձապետական բաժամունք";
        if (90041 == accountStartNumbers)
            return "Արտաշատի գանձապետական բաժամունք";
        if (90042 == accountStartNumbers)
            return "Արարատի քաղաքային գանձապետական բաժամունք";
        if (90043 == accountStartNumbers)
            return "Մասիսի գանձապետական բաժամունք";
        if (90044 == accountStartNumbers)
            return "Աշտարակի քաղաքային գանձապետական բաժամունք";
        if (90045 == accountStartNumbers)
            return "Ապարանի գանձապետական բաժամունք";
        if (90046 == accountStartNumbers)
            return "Թալինի գանձապետական բաժամունք";
        if (90047 == accountStartNumbers)
            return "Բաղրամյանի գանձապետական բաժամունք";
        if (90048 == accountStartNumbers)
            return "Ծաղկահովիտի գանձապետական բաժամունք";
        if (90049 == accountStartNumbers)
            return "Մարալիկի գանձապետական բաժամունք";
        if (90300 == accountStartNumbers)
            return "Գանձապետական պահառու";
        if (91500 == accountStartNumbers)
            return "ԼՂՀ կենտրոնական գանձապետարան";
        if (91501 == accountStartNumbers)
            return "ԼՂՀ գանձ. Ստեփանակերտի տեղ.բաժ.";
        if (91502 == accountStartNumbers)
            return "ԼՂՀ գանձ.Հադրութի տեղ.բաժ.";
        if (91503 == accountStartNumbers)
            return "ԼՂՀ գանձ.Շուշիի  տեղ.բաժ.";
        if (91504 == accountStartNumbers)
            return "ԼՂՀ գանձ.Ասկերանի տեղ.բաժ.  ";
        if (91505 == accountStartNumbers)
            return "ԼՂՀ գանձ.Մարտակերտու տեղ.բաժ.";
        if (91506 == accountStartNumbers)
            return "ԼՂՀ գանձ.Մարտունու տեղ.բաժ.";

        return "";
    }
    @PostMapping(value = "bankaccount/create")
    public  ModelAndView bankАccountCreate(@Valid BankAccount bankAccount , BindingResult bindingResult, ModelAndView modelAndView  ) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bankAccount", bankAccount);
            modelAndView.addObject("organization", bankAccount.getOrganization());
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("fragment", this.bankaccountCreate);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

            return modelAndView;
        }
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization",bankAccount.getOrganization());
        modelAndView.addObject( "navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.bankaccount);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        bankAccountRepository.save(bankAccount);
        return  modelAndView;
    }
    @GetMapping(value = "cashin/cashdesk")
    public ModelAndView cashIncashdesk(ModelAndView modelAndView, HttpSession httpSession) {

        if(walletDataRepository.count()<1){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.noWalletData);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);

        modelAndView.addObject("fragment", this.cashInFragment);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/show")
    public @ResponseBody ArrayList cashinCashdeskShow(@RequestBody DateRange dateRange ) {
        List<GetWaletIn> temp = new ArrayList();
        ArrayList<CashInShow> showResult = new ArrayList();
        if (dateRange.isShowAll()) {
        temp.addAll((ArrayList)cashInFromBankAccountRepository.findAll());
        temp.addAll((ArrayList)cashInFromCreditRepository.findAll());
        temp.addAll((ArrayList)cashInFromLoanRepository.findAll());
        temp.addAll((ArrayList)cashInFromPointOfSaleRepository.findAll());
        temp.addAll((ArrayList)cashInFromSaleOfGoodsRepository.findAll());
        temp.addAll((ArrayList)cashInFromServiceProvisionRepository.findAll());
            for(GetWaletIn each:temp) {

                showResult.add(new CashInShow(each.getCashInId(),each.getWalletInImpl().getInType(),each.getWalletInImpl().getInDate(),each.getWalletInImpl().getInCash()));
            }

            return showResult;
        }else

        if (dateRange.getStart() != null && dateRange.getEnd() == null) {
            temp.addAll(cashInFromBankAccountRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromCreditRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromLoanRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromPointOfSaleRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromSaleOfGoodsRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromServiceProvisionRepository.findByRangeStart(dateRange.getStart()));
            for(GetWaletIn each:temp) {

                showResult.add(new CashInShow(each.getCashInId(),each.getWalletInImpl().getInType(),each.getWalletInImpl().getInDate(),each.getWalletInImpl().getInCash()));
            }

            return showResult;
        }else
        if (dateRange.getStart() == null && dateRange.getEnd() != null) {
            temp.addAll(cashInFromBankAccountRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromCreditRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromLoanRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromPointOfSaleRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromSaleOfGoodsRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromServiceProvisionRepository.findByEnd(dateRange.getEnd()));
            for(GetWaletIn each:temp) {

                showResult.add(new CashInShow(each.getCashInId(),each.getWalletInImpl().getInType(),each.getWalletInImpl().getInDate(),each.getWalletInImpl().getInCash()));
            }

            return showResult;
        }else
        if (dateRange.getStart() != null && dateRange.getEnd() != null) {

           temp.addAll(cashInFromBankAccountRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
           temp.addAll(cashInFromCreditRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
           temp.addAll(cashInFromLoanRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
           temp.addAll(cashInFromPointOfSaleRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
           temp.addAll(cashInFromSaleOfGoodsRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
           temp.addAll(cashInFromServiceProvisionRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;

            for(GetWaletIn each:temp) {
                showResult.add(new CashInShow(each.getCashInId(),each.getWalletInImpl().getInType(),each.getWalletInImpl().getInDate(),each.getWalletInImpl().getInCash()));
            }

            return showResult;
        }
        return showResult;
    }
    @GetMapping(value = "cashin/cashdesk/create")
    public ModelAndView cashIncashdeskCreate(ModelAndView modelAndView ,HttpSession httpSession) {

        if(walletDataRepository.count()<1){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.noWalletData);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            return modelAndView;
        }
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateFragment);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        return modelAndView;

    }
    @GetMapping(value = "/cashin/cashdesk/delete")
    public ModelAndView cashinDelete(@RequestParam("cashintype") String cashInType,@RequestParam("cashinid") Long cashInId, ModelAndView modelAndView, HttpSession httpSession) {

        if(cashInType.equals("CashInFromCredit"))
            cashInFromCreditRepository.delete(cashInId);
        if(cashInType.equals("CashInFromLoan"))
            cashInFromLoanRepository.delete(cashInId);
        if(cashInType.equals("CashInFromBankAccount"))
            cashInFromBankAccountRepository.delete(cashInId);
        if(cashInType.equals("CashInFromPointOfSale"))
            cashInFromPointOfSaleRepository.delete(cashInId);
        if(cashInType.equals("CashInFromSaleOfGoods"))
            cashInFromSaleOfGoodsRepository.delete(cashInId);
        if(cashInType.equals("CashInFromServiceProvision"))
            cashInFromServiceProvisionRepository.delete(cashInId);




        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInFragment);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }


    @GetMapping(value = "/cashin/cashdesk/edit")
    public ModelAndView cashinEdit(@RequestParam("cashintype") String cashInType,@RequestParam("cashinid") Long cashInId, ModelAndView modelAndView, HttpSession httpSession) {
        customerList = new ArrayList();
        List<CompanyCustomer> companyCustomers = (List<CompanyCustomer>) companyCustomerRepository.findAll();
        List<IndividualCustomer> individualCustomers = (List<IndividualCustomer>) individualCustomerRepository.findAll();
        List<PrivateEntrepreneurCustomer>  privateEntrepreneurCustomers  = (List<PrivateEntrepreneurCustomer>)privateEntrepreneurCustomerRepository.findAll();


        for(CompanyCustomer companyCustomer :companyCustomers){
            customerList.add(new Customer(companyCustomer.getId(),"CompanyCustomer",companyCustomer.getName()));
        }
        for(IndividualCustomer individualCustomer :individualCustomers){
            customerList.add(new Customer(individualCustomer.getId(),"IndividualCustomer",individualCustomer.getName()));
        }
        for(PrivateEntrepreneurCustomer privateEntrepreneurCustomer:privateEntrepreneurCustomers){
            customerList.add(new Customer(privateEntrepreneurCustomer.getId(),"PrivateEntrepreneurCustomer",privateEntrepreneurCustomer.getName()));
        }

        colleaguesList = new ArrayList();
        List<CompanyOtherPartner> companyOtherPartners ;
        companyOtherPartners = (List<CompanyOtherPartner>) companyOtherPartnerRepository.findAll();
        List<IndividualOtherPartner> individualOtherPartners;
        individualOtherPartners = (List<IndividualOtherPartner>) individualOtherPartnerRepository.findAll();
        List<PrivateEntrepreneurOtherPartner>  privateEntrepreneurOtherPartners;
        privateEntrepreneurOtherPartners = (List<PrivateEntrepreneurOtherPartner>) privateEntrepreneurOtherPartnerRepository.findAll();
        for(CompanyOtherPartner companyOtherPartner:companyOtherPartners){
            colleaguesList.add(new OtherPartner(companyOtherPartner.getId(),"CompanyOtherPartner",companyOtherPartner.getName()));
        }
        for(IndividualOtherPartner individualOtherPartner:individualOtherPartners){
            colleaguesList.add(new OtherPartner(individualOtherPartner.getId(),"IndividualOtherPartner",individualOtherPartner.getName()));
        }
        for(PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner:privateEntrepreneurOtherPartners){
            colleaguesList.add(new OtherPartner(privateEntrepreneurOtherPartner.getId(),"PrivateEntrepreneurOtherPartner",privateEntrepreneurOtherPartner.getName()));
        }


        if(cashInType.equals("CashInFromCredit")){

            CashInFromCredit cashInFromCredit = cashInFromCreditRepository.findOne(cashInId);
            modelAndView.addObject("cashInFromCredit",cashInFromCredit);
            modelAndView.addObject("colleaguesList", colleaguesList);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromCredit);
        }
        if(cashInType.equals("CashInFromLoan")){

            CashInFromLoan cashInFromLoan = cashInFromLoanRepository.findOne(cashInId);
            modelAndView.addObject("cashInFromLoan",cashInFromLoan);
            modelAndView.addObject("colleaguesList", colleaguesList);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromLoan);
        }
        if(cashInType.equals("CashInFromSaleOfGoods")){

            CashInFromSaleOfGoods cashInFromSaleOfGoods = cashInFromSaleOfGoodsRepository.findOne(cashInId);
            modelAndView.addObject("customerList", customerList);
            modelAndView.addObject("cashInFromSaleOfGoods",cashInFromSaleOfGoods);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateFragmentSaleOfGoods);
        }
        if(cashInType.equals("CashInFromServiceProvision")){

            CashInFromServiceProvision cashInFromServiceProvision = cashInFromServiceProvisionRepository.findOne(cashInId);
            modelAndView.addObject("customerList", customerList);
            modelAndView.addObject("cashInFromServiceProvision",cashInFromServiceProvision);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromServiceProvision);
        }
        if(cashInType.equals("CashInFromPointOfSale")){

            CashInFromPointOfSale cashInFromPointOfSale = cashInFromPointOfSaleRepository.findOne(cashInId);

            modelAndView.addObject("cashInFromPointOfSale",cashInFromPointOfSale);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromPointOfSale);
        }
        if(cashInType.equals("CashInFromBankAccount")){

            CashInFromBankAccount cashInFromBankAccount = cashInFromBankAccountRepository.findOne(cashInId);

            modelAndView.addObject("cashInFromBankAccount",cashInFromBankAccount);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateBankAccount);
        }


        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }

    @GetMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods")
    public   ModelAndView cashinfromsaleofGoodsCreate(ModelAndView modelAndView, HttpSession httpSession) {

        if(walletDataRepository.count()<1){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.noWalletData);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            return modelAndView;
        }


         WalletIn walletIn=new WalletIn();
        CashInFromSaleOfGoods cashInFromSaleOfGoods = new CashInFromSaleOfGoods();
        cashInFromSaleOfGoods.setWalletIn(walletIn);
        cashInFromSaleOfGoods.setOrganization((Organization) httpSession.getAttribute("organizationId"));


        customerList = new ArrayList();
        List<CompanyCustomer> companyCustomers =(List<CompanyCustomer>) companyCustomerRepository.findAll();
        List<IndividualCustomer> individualCustomers = (List<IndividualCustomer>) individualCustomerRepository.findAll();
        List<PrivateEntrepreneurCustomer>  privateEntrepreneurCustomers  = (List<PrivateEntrepreneurCustomer>)privateEntrepreneurCustomerRepository.findAll();


        for(CompanyCustomer companyCustomer :companyCustomers){
            customerList.add(new Customer(companyCustomer.getId(),"CompanyCustomer",companyCustomer.getName()));
        }
        for(IndividualCustomer individualCustomer :individualCustomers){
            customerList.add(new Customer(individualCustomer.getId(),"IndividualCustomer",individualCustomer.getName()));
        }
        for(PrivateEntrepreneurCustomer privateEntrepreneurCustomer:privateEntrepreneurCustomers){
            customerList.add(new Customer(privateEntrepreneurCustomer.getId(),"PrivateEntrepreneurCustomer",privateEntrepreneurCustomer.getName()));
        }


        modelAndView.setViewName("app/app");
        modelAndView.addObject("cashInFromSaleOfGoods", cashInFromSaleOfGoods);
        modelAndView.addObject("customerList", customerList);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateFragmentSaleOfGoods);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods" )
    public   ModelAndView cashinfromsaleofGoodsCreate(@Valid CashInFromSaleOfGoods cashInFromSaleOfGoods ,BindingResult bindingResult,ModelAndView modelAndView  ) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {

            modelAndView.addObject("cashInFromSaleOfGoods", cashInFromSaleOfGoods);
            modelAndView.addObject("customerList", customerList);

            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateFragmentSaleOfGoods);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

            return modelAndView;
        }


        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        if(cashInFromSaleOfGoods.getCustomerType().equals("CompanyCustomer")){
            cashInFromSaleOfGoods.setCompanyCustomer(companyCustomerRepository.findOne(cashInFromSaleOfGoods.getCustomerId()));
        }
        if(cashInFromSaleOfGoods.getCustomerType().equals("IndividualCustomer")){
            cashInFromSaleOfGoods.setIndividualCustomer(individualCustomerRepository.findOne(cashInFromSaleOfGoods.getCustomerId()));
        }
        if(cashInFromSaleOfGoods.getCustomerType().equals("PrivateEntrepreneurCustomer")){
            cashInFromSaleOfGoods.setPrivateEntrepreneurCustomer(privateEntrepreneurCustomerRepository.findOne(cashInFromSaleOfGoods.getCustomerId()));
        }


        cashInFromSaleOfGoods.getWalletIn().setInType("CashInFromSaleOfGoods");
        cashInFromSaleOfGoods.getWalletIn().setOrganization(cashInFromSaleOfGoods.getOrganization());
        cashInFromSaleOfGoodsRepository.save(cashInFromSaleOfGoods);
        return  modelAndView;
    }

    @GetMapping(value = "cashin/cashdesk/create/cashinfrombankaccount")
    public   ModelAndView cashinFromBankAccount( ModelAndView modelAndView , HttpSession httpSession) {


        if(walletDataRepository.count()<1){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.noWalletData);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            return modelAndView;
        }
        ArrayList accountList;

        WalletIn walletIn=new WalletIn();
        CashInFromBankAccount cashInFromBankAccount = new CashInFromBankAccount();
        cashInFromBankAccount.setWalletIn(walletIn);
        cashInFromBankAccount.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        accountList=(ArrayList)bankAccountRepository.findAll();


        modelAndView.setViewName("app/app");

        modelAndView.addObject("accountList", accountList);
        modelAndView.addObject("cashInFromBankAccount", cashInFromBankAccount);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.cashInCreateBankAccount);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfrombankaccount" )
    public   ModelAndView cashinFromBankAccountCreate(@Valid CashInFromBankAccount cashInFromBankAccount ,BindingResult bindingResult,ModelAndView modelAndView  ) {
        ArrayList accountList;
        if (bindingResult.hasErrors()) {

            accountList=(ArrayList)bankAccountRepository.findAll();


            modelAndView.addObject("cashInFromSaleOfGoods", cashInFromBankAccount);
            modelAndView.addObject("accountList", accountList);
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateBankAccount);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

        cashInFromBankAccount.getWalletIn().setInType("CashInFromBankAccount");
        cashInFromBankAccount.getWalletIn().setOrganization(cashInFromBankAccount.getOrganization());

        cashInFromBankAccountRepository.save(cashInFromBankAccount);
        return  modelAndView;
    }

        @GetMapping(value = "cashin/cashdesk/create/cashinfromloan")
    public   ModelAndView cashInFromLoan( ModelAndView modelAndView ,HttpSession httpSession) {

            if(walletDataRepository.count()<1){
                modelAndView.setViewName("app/app");
                modelAndView.addObject("navBar", this.cashNavBar);
                modelAndView.addObject("fragment", this.noWalletData);
                modelAndView.addObject("appFragment", this.appFragment);
                modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
                return modelAndView;
            }
            colleaguesList = new ArrayList();
            List<CompanyOtherPartner> companyOtherPartners ;
            companyOtherPartners = (List<CompanyOtherPartner>) companyOtherPartnerRepository.findAll();
            List<IndividualOtherPartner> individualOtherPartners;
            individualOtherPartners = (List<IndividualOtherPartner>) individualOtherPartnerRepository.findAll();
            List<PrivateEntrepreneurOtherPartner>  privateEntrepreneurOtherPartners;
            privateEntrepreneurOtherPartners = (List<PrivateEntrepreneurOtherPartner>) privateEntrepreneurOtherPartnerRepository.findAll();
            for(CompanyOtherPartner companyOtherPartner:companyOtherPartners){
                colleaguesList.add(new OtherPartner(companyOtherPartner.getId(),"CompanyOtherPartner",companyOtherPartner.getName()));
            }
            for(IndividualOtherPartner individualOtherPartner:individualOtherPartners){
                colleaguesList.add(new OtherPartner(individualOtherPartner.getId(),"IndividualOtherPartner",individualOtherPartner.getName()));
            }
            for(PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner:privateEntrepreneurOtherPartners){
                colleaguesList.add(new OtherPartner(privateEntrepreneurOtherPartner.getId(),"PrivateEntrepreneurOtherPartner",privateEntrepreneurOtherPartner.getName()));
            }


        WalletIn walletIn=new WalletIn();
        CashInFromLoan cashInFromLoan = new CashInFromLoan();
        cashInFromLoan.setWalletIn(walletIn);
        cashInFromLoan.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("colleaguesList", colleaguesList);
        modelAndView.addObject("cashInFromLoan", cashInFromLoan);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.cashInCreateCashInFromLoan);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfromloan" )
    public   ModelAndView cashInFromLoanCreate(@Valid CashInFromLoan cashInFromLoan ,BindingResult bindingResult,ModelAndView modelAndView  ) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {

            modelAndView.addObject("colleaguesList", colleaguesList);
            modelAndView.addObject("cashInFromLoan", cashInFromLoan);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromLoan);
            modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

            return modelAndView;


        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

        if(cashInFromLoan.getOtherPartnerType().equals("CompanyOtherPartner")){
            cashInFromLoan.setCompanyOtherPartner(companyOtherPartnerRepository.findOne(cashInFromLoan.getOtherPartnerId()));
        }

        if(cashInFromLoan.getOtherPartnerType().equals("IndividualOtherPartner")){
            cashInFromLoan.setIndividualOtherPartner(individualOtherPartnerRepository.findOne(cashInFromLoan.getOtherPartnerId()));
        }

        if(cashInFromLoan.getOtherPartnerType().equals("PrivateEntrepreneurOtherPartner")){
            cashInFromLoan.setPrivateEntrepreneurOtherPartner(privateEntrepreneurOtherPartnerRepository.findOne(cashInFromLoan.getOtherPartnerId()));
        }
        cashInFromLoan.getWalletIn().setInType("CashInFromLoan");
        cashInFromLoan.getWalletIn().setOrganization(cashInFromLoan.getOrganization());
        cashInFromLoanRepository.save(cashInFromLoan);
        return  modelAndView;
    }

    @GetMapping(value = "cashin/cashdesk/create/cashinfrompointofsale")
    public   ModelAndView cashinfrompointofSaleCreate( ModelAndView modelAndView,HttpSession httpSession) {

        if(walletDataRepository.count()<1){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.noWalletData);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            return modelAndView;
        }
        WalletIn walletIn=new WalletIn();
        CashInFromPointOfSale cashInFromPointOfSale = new CashInFromPointOfSale();
        cashInFromPointOfSale.setWalletIn(walletIn);
        cashInFromPointOfSale.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("cashInFromPointOfSale", cashInFromPointOfSale);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.cashInCreateCashInFromPointOfSale);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfrompointofsale" )
    public   ModelAndView cashinfrompointofSaleCreate(@Valid CashInFromPointOfSale cashInFromPointOfSale ,BindingResult bindingResult,ModelAndView modelAndView  ) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashInFromPointOfSale", cashInFromPointOfSale);
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromPointOfSale);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        cashInFromPointOfSale.getWalletIn().setInType("CashInFromPointOfSale");
        cashInFromPointOfSale.getWalletIn().setOrganization(cashInFromPointOfSale.getOrganization());
        cashInFromPointOfSaleRepository.save(cashInFromPointOfSale);
        return  modelAndView;
    }

    @GetMapping(value = "cashin/cashdesk/create/cashinfromserviceprovision")
    public   ModelAndView cashinfrompointofsaleCreate( ModelAndView modelAndView ,HttpSession httpSession) {
        if(walletDataRepository.count()<1){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.noWalletData);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            return modelAndView;
        }

        WalletIn walletIn=new WalletIn();
        CashInFromServiceProvision cashInFromServiceProvision = new CashInFromServiceProvision();
        cashInFromServiceProvision.setWalletIn(walletIn);
        cashInFromServiceProvision.setOrganization((Organization) httpSession.getAttribute("organizationId"));


        customerList = new ArrayList();
        List<CompanyCustomer> companyCustomers = (List<CompanyCustomer>) companyCustomerRepository.findAll();
        List<IndividualCustomer> individualCustomers = (List<IndividualCustomer>) individualCustomerRepository.findAll();
        List<PrivateEntrepreneurCustomer>  privateEntrepreneurCustomers  = (List<PrivateEntrepreneurCustomer>)privateEntrepreneurCustomerRepository.findAll();


        for(CompanyCustomer companyCustomer :companyCustomers){
            customerList.add(new Customer(companyCustomer.getId(),"CompanyCustomer",companyCustomer.getName()));
        }
        for(IndividualCustomer individualCustomer :individualCustomers){
            customerList.add(new Customer(individualCustomer.getId(),"IndividualCustomer",individualCustomer.getName()));
        }
        for(PrivateEntrepreneurCustomer privateEntrepreneurCustomer:privateEntrepreneurCustomers){
            customerList.add(new Customer(privateEntrepreneurCustomer.getId(),"PrivateEntrepreneurCustomer",privateEntrepreneurCustomer.getName()));
        }



        modelAndView.setViewName("app/app");
        modelAndView.addObject("customerList", customerList);
        modelAndView.addObject("cashInFromServiceProvision", cashInFromServiceProvision);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.cashInCreateCashInFromServiceProvision);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfromserviceprovision" )
    public   ModelAndView cashinfrompointofsaleCreateSave(@Valid CashInFromServiceProvision cashInFromServiceProvision ,BindingResult bindingResult,ModelAndView modelAndView  ) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {

            modelAndView.addObject("cashInFromServiceProvision", cashInFromServiceProvision);
            modelAndView.addObject("customerList", customerList);
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromServiceProvision);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

            return modelAndView;
        }


        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

        if(cashInFromServiceProvision.getCustomerType().equals("CompanyCustomer")){
            cashInFromServiceProvision.setCompanyCustomer(companyCustomerRepository.findOne(cashInFromServiceProvision.getCustomerId()));
        }
        if(cashInFromServiceProvision.getCustomerType().equals("IndividualCustomer")){
            cashInFromServiceProvision.setIndividualCustomer(individualCustomerRepository.findOne(cashInFromServiceProvision.getCustomerId()));
        }
        if(cashInFromServiceProvision.getCustomerType().equals("PrivateEntrepreneurCustomer")){
            cashInFromServiceProvision.setPrivateEntrepreneurCustomer(privateEntrepreneurCustomerRepository.findOne(cashInFromServiceProvision.getCustomerId()));
        }


        cashInFromServiceProvision.getWalletIn().setInType("CashInFromServiceProvision");
        cashInFromServiceProvision.getWalletIn().setOrganization(cashInFromServiceProvision.getOrganization());

        cashInFromServiceProvisionRepository.save(cashInFromServiceProvision);
        return  modelAndView;
    }

    @GetMapping(value = "cashin/cashdesk/create/cashinfromcredit")
    public   ModelAndView createCashInFromCredit(ModelAndView modelAndView,HttpSession httpSession) {



        if(walletDataRepository.count()<1){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.noWalletData);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            return modelAndView;
        }

        colleaguesList = new ArrayList();
        List<CompanyOtherPartner> companyOtherPartners ;
        companyOtherPartners = (List<CompanyOtherPartner>) companyOtherPartnerRepository.findAll();
        List<IndividualOtherPartner> individualOtherPartners;
        individualOtherPartners = (List<IndividualOtherPartner>) individualOtherPartnerRepository.findAll();
        List<PrivateEntrepreneurOtherPartner>  privateEntrepreneurOtherPartners;
        privateEntrepreneurOtherPartners = (List<PrivateEntrepreneurOtherPartner>) privateEntrepreneurOtherPartnerRepository.findAll();
        for(CompanyOtherPartner companyOtherPartner:companyOtherPartners){
            colleaguesList.add(new OtherPartner(companyOtherPartner.getId(),"CompanyOtherPartner",companyOtherPartner.getName()));
        }
        for(IndividualOtherPartner individualOtherPartner:individualOtherPartners){
            colleaguesList.add(new OtherPartner(individualOtherPartner.getId(),"IndividualOtherPartner",individualOtherPartner.getName()));
        }
        for(PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner:privateEntrepreneurOtherPartners){
            colleaguesList.add(new OtherPartner(privateEntrepreneurOtherPartner.getId(),"PrivateEntrepreneurOtherPartner",privateEntrepreneurOtherPartner.getName()));
        }

        WalletIn walletIn=new WalletIn();
        CashInFromCredit cashInFromCredit = new CashInFromCredit();
        cashInFromCredit.setWalletIn(walletIn);
        cashInFromCredit.setOrganization((Organization) httpSession.getAttribute("organizationId"));

        modelAndView.setViewName("app/app");
        modelAndView.addObject("colleaguesList", colleaguesList);
        modelAndView.addObject("cashInFromCredit", cashInFromCredit);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.cashInCreateCashInFromCredit);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfromcredit" )
    public   ModelAndView createCashInFromCredit(@Valid CashInFromCredit cashInFromCredit ,BindingResult bindingResult,ModelAndView modelAndView,HttpSession httpSession  ) {

        if (bindingResult.hasErrors()) {

            modelAndView.setViewName("app/app");
            modelAndView.addObject("organization",cashInFromCredit.getOrganization());
            modelAndView.addObject("colleaguesList", colleaguesList);
            modelAndView.addObject("cashInFromCredit", cashInFromCredit);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.cashInFragments);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromCredit);
            modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

            return modelAndView;
        }



        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

        if(cashInFromCredit.getOtherPartnerType().equals("CompanyOtherPartner")){
            cashInFromCredit.setCompanyOtherPartner(companyOtherPartnerRepository.findOne(cashInFromCredit.getOtherPartnerId()));
        }

        if(cashInFromCredit.getOtherPartnerType().equals("IndividualOtherPartner")){
            cashInFromCredit.setIndividualOtherPartner(individualOtherPartnerRepository.findOne(cashInFromCredit.getOtherPartnerId()));
        }

        if(cashInFromCredit.getOtherPartnerType().equals("PrivateEntrepreneurOtherPartner")){
            cashInFromCredit.setPrivateEntrepreneurOtherPartner(privateEntrepreneurOtherPartnerRepository.findOne(cashInFromCredit.getOtherPartnerId()));
        }

        cashInFromCredit.getWalletIn().setInType("CashInFromCredit");
        cashInFromCredit.getWalletIn().setOrganization(cashInFromCredit.getOrganization());
        cashInFromCreditRepository.save(cashInFromCredit);
        return  modelAndView;
    }


    @GetMapping(value = "create/customer")
    public   ModelAndView cashinfrompointofsaleCreateCustomer( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.customerAndColleaguesCreate);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;

    }

    @GetMapping(value = "create/supplier")
    public   ModelAndView cashCreateSupplier( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.customerAndColleaguesCreateSupplier);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        return  modelAndView;
    }

    @GetMapping(value = "create/otherpartner")
    public   ModelAndView cashinfrompointofsaleCreateotherPartner( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.customerAndColleaguesCreateOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }





    @GetMapping(value = "create/customer/customerclientorganization" )
    public   ModelAndView customerClientOrganizationCreate( ModelAndView modelAndView,HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        CustomerClientOrganization customerClientOrganization = new CustomerClientOrganization();
        customerClientOrganization.setOrganization((Organization)httpSession.getAttribute("organizationId"));
        modelAndView.addObject("customerClientOrganization", customerClientOrganization);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.createCustomerClientOrganization);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "create/customer/customerclientorganization")
    public   ModelAndView customerClientOrganizationCreate(@Valid CustomerClientOrganization clientOrganization, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("clientOrganization", clientOrganization);
            modelAndView.addObject("organization", clientOrganization.getOrganization());
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragment", this.createCustomerClientOrganization);
            modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.cashInCreateFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        customerClientOrganizationRepository.save(clientOrganization);
        CompanyCustomer companyCustomer = new CompanyCustomer();
        companyCustomer.setOrganization(clientOrganization.getOrganization());
        companyCustomer.setClientOrganization(clientOrganization);
        companyCustomerRepository.save(companyCustomer);
        return  modelAndView;
    }
    @GetMapping(value = "create/customer/customerindividual")
    public   ModelAndView customerIndividualCreate( ModelAndView modelAndView,HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        CustomerIndividual customerIndividual = new CustomerIndividual();
        customerIndividual.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.addObject("customerIndividual", customerIndividual);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.createCustomerIndividual);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "create/customer/customerindividual")
    public   ModelAndView customerIndividualCreate(@Valid CustomerIndividual customerIndividual, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("customerIndividual", customerIndividual);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragment", this.createCustomerIndividual);
            modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.cashInCreateFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        customerIndividualRepository.save(customerIndividual);
        IndividualCustomer individualCustomer = new IndividualCustomer();
        individualCustomer.setOrganization(customerIndividual.getOrganization());
        individualCustomer.setIndividual(customerIndividual);
       individualCustomerRepository.save(individualCustomer);
        return modelAndView;
    }


    /*other partners coleguas*/
    @GetMapping(value = "create/otherpartner/otherpartnerclientorganization" )
    public   ModelAndView otherPartnerCreateOrganization( ModelAndView modelAndView,HttpSession httpSession) {

       modelAndView.setViewName("app/app");
        OtherPartnerClientOrganization otherPartnerClientOrganization = new OtherPartnerClientOrganization();
        otherPartnerClientOrganization.setOrganization((Organization)httpSession.getAttribute("organizationId"));
        modelAndView.addObject("otherPartnerClientOrganization", otherPartnerClientOrganization);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.createOtherPartnerClientOrganization);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "create/otherpartner/otherpartnerclientorganization")
    public   ModelAndView otherPartnerCreateOrganization(@Valid OtherPartnerClientOrganization otherPartnerClientOrganization, BindingResult bindingResult, ModelAndView modelAndView,HttpSession httpSession) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("otherPartnerClientOrganization", otherPartnerClientOrganization);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragment", this.createOtherPartnerClientOrganization);
            modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
            return modelAndView;

        }



        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.cashInCreateFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        otherPartnerClientOrganizationRepository.save(otherPartnerClientOrganization);
        CompanyOtherPartner companyOtherPartner = new CompanyOtherPartner();
        companyOtherPartner.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        companyOtherPartner.setClientOrganization(otherPartnerClientOrganization);
        companyOtherPartnerRepository.save(companyOtherPartner);
        return  modelAndView;
     }
    @GetMapping(value = "create/otherpartner/otherpartnerindividual" )
    public   ModelAndView otherPartnerIndividual( ModelAndView modelAndView,HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        OtherPartnerIndividual otherPartnerIndividual =new OtherPartnerIndividual() ;
        otherPartnerIndividual.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.addObject("otherPartnerIndividual", otherPartnerIndividual);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.createOtherPartnerIndividual);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "create/otherpartner/otherpartnerindividual")
    public   ModelAndView otherPartnerIndividual(@Valid OtherPartnerIndividual otherPartnerIndividual, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("otherPartnerIndividual", otherPartnerIndividual);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragment", this.createOtherPartnerIndividual);
            modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashInFragments);
        modelAndView.addObject("fragment", this.cashInCreateFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        otherPartnerIndividualRepository.save(otherPartnerIndividual);
        IndividualOtherPartner individualOtherPartner = new IndividualOtherPartner();
        individualOtherPartner.setIndividual(otherPartnerIndividual);
        individualOtherPartner.setOrganization(otherPartnerIndividual.getOrganization());
        individualOtherPartnerRepository.save(individualOtherPartner);

        return  modelAndView;
    }


    /*suppliers coleguas*/
    @GetMapping(value = "create/supplier/supplierclientorganization" )
    public   ModelAndView supplierCreateOrganization( ModelAndView modelAndView,HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        SupplierClientOrganization supplierClientOrganization  = new SupplierClientOrganization();
        supplierClientOrganization.setOrganization((Organization)httpSession.getAttribute("organizationId"));
        modelAndView.addObject("supplierClientOrganization", supplierClientOrganization );
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.createSupplierClientOrganization);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "create/supplier/supplierclientorganization")
    public   ModelAndView supplierCreateOrganization(@Valid SupplierClientOrganization supplierClientOrganization, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("supplierClientOrganization", supplierClientOrganization);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragment", this.createSupplierClientOrganization);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        supplierClientOrganizationRepository.save(supplierClientOrganization);
        CompanySupplier companySupplier = new CompanySupplier();
        companySupplier.setClientOrganization(supplierClientOrganization);
        companySupplier.setOrganization(supplierClientOrganization.getOrganization());
        companySupplierRepository.save(companySupplier);
        return  modelAndView;
    }
    @GetMapping(value = "create/supplier/supplierindividual" )
    public   ModelAndView supplierIndividual( ModelAndView modelAndView,HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        SupplierIndividual supplierIndividual =new SupplierIndividual() ;
        supplierIndividual.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.addObject("supplierIndividual", supplierIndividual);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.createSupplierIndividual);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "create/supplier/supplierindividual")
    public   ModelAndView supplierIndividual(@Valid SupplierIndividual supplierIndividual, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("supplierIndividual", supplierIndividual);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragment", this.createSupplierIndividual);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        supplierIndividualRepository.save(supplierIndividual);
        IndividualSupplier individualSupplier = new IndividualSupplier();
        individualSupplier.setIndividual(supplierIndividual);
        individualSupplier.setOrganization(supplierIndividual.getOrganization());
        individualSupplierRepository.save(individualSupplier);
        return  modelAndView;
    }


}
