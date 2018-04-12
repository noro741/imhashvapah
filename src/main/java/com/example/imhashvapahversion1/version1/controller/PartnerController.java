package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.IndividualCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.PrivateEntrepreneurCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.repository.*;
import com.example.imhashvapahversion1.version1.repository.otherpartners.CompanyOtherPartnerRepository;
import com.example.imhashvapahversion1.version1.repository.otherpartners.IndividualOtherPartnerRepository;
import com.example.imhashvapahversion1.version1.repository.otherpartners.PrivateEntrepreneurOtherPartnerRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.CompanySupplierRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.IndividualSupplierRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.PrivateEntrepreneurSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;


@Controller
@RequestMapping("account/partner")
public class PartnerController extends BaseController {


    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    UniversalRepository universalRepository;
    @Autowired
    CompanyCustomerRepository companyCustomerRepository;
    @Autowired
    IndividualCustomerRepository individualCustomerRepository;
    @Autowired
    PrivateEntrepreneurCustomerRepository privateEntrepreneurCustomerRepository;
    @Autowired
    CompanyOtherPartnerRepository companyOtherPartnerRepository;
    @Autowired
    IndividualOtherPartnerRepository individualOtherPartnerRepository;
    @Autowired
    PrivateEntrepreneurOtherPartnerRepository privateEntrepreneurOtherPartnerRepository;
    @Autowired
    CompanySupplierRepository companySupplierRepository;
    @Autowired
    IndividualSupplierRepository individualSupplierRepository;
    @Autowired
    PrivateEntrepreneurSupplierRepository privateEntrepreneurSupplierRepository;
    @InitBinder()
    public void registerConversionServices(WebDataBinder dataBinder) {
        dataBinder.addCustomFormatter(new Formatter<Organization>() {

            @Override
            public String print(Organization organization, Locale locale) {
                return organization.getId().toString();
            }
            @Override
            public Organization parse(String organizationId, Locale locale){
                return organizationRepository.findOne(Long.parseLong(organizationId));
            }

        });
    }

    /*partner Customers*/
    @GetMapping(value = "/customer")
    public ModelAndView partners( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerCustomers);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @GetMapping(value = "/customer/create/individualcustomer")
    public ModelAndView partnerCustomer(ModelAndView modelAndView, HttpSession httpSession) {
        IndividualCustomer individualCustomer = new IndividualCustomer();
        individualCustomer.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("individualCustomer",individualCustomer);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.individualCustomerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "/customer/create/individualcustomer")
    public ModelAndView partnerCustomer(@Valid IndividualCustomer individualCustomer, BindingResult bindingResult , ModelAndView modelAndView) {
       if(bindingResult.hasErrors()) {
           modelAndView.setViewName("app/app");
           modelAndView.addObject("individualCustomer", individualCustomer);
           modelAndView.addObject("navBar", this.partnerNavBar);
           modelAndView.addObject("fragment", this.individualCustomerCreate);
           modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
           return modelAndView;
       }
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerCustomers);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        individualCustomerRepository.save(individualCustomer);
            return  modelAndView;
    }

    @GetMapping(value = "/customer/create/companycustomer")
    public ModelAndView companyCustomerCreate(ModelAndView modelAndView, HttpSession httpSession) {
        CompanyCustomer companyCustomer = new CompanyCustomer();
        companyCustomer.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("companyCustomer",companyCustomer);
         modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.companyCustomerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
   @PostMapping(value = "/customer/create/companycustomer")
    public ModelAndView partnerCustomer(@Valid CompanyCustomer companyCustomer, BindingResult bindingResult , ModelAndView modelAndView) {
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("app/app");
            modelAndView.addObject("companyCustomer", companyCustomer);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragment", this.companyCustomerCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
            return modelAndView;
        }


       modelAndView.addObject("navBar", this.partnerNavBar);
       modelAndView.addObject("fragment", this.partnerCustomers);
       modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
       companyCustomerRepository.save(companyCustomer);
         return  modelAndView;
    }


    @GetMapping(value = "/customer/create/privateentrepreneurcustomer")
    public ModelAndView privateentrepreneurcustomerCreate(ModelAndView modelAndView, HttpSession httpSession) {
        PrivateEntrepreneurCustomer privateEntrepreneurCustomer = new PrivateEntrepreneurCustomer();
        privateEntrepreneurCustomer.setOrganization((Organization) httpSession.getAttribute("organizationId"));
          modelAndView.setViewName("app/app");
        modelAndView.addObject("privateEntrepreneurCustomer",privateEntrepreneurCustomer);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.privateEntrepreneurCustomerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "/customer/create/privateentrepreneurcustomer")
    public ModelAndView privateentrepreneurcustomerCreate(@Valid PrivateEntrepreneurCustomer privateEntrepreneurCustomer, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("privateEntrepreneurCustomer", privateEntrepreneurCustomer);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragment", this.privateEntrepreneurCustomerCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerCustomers);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        privateEntrepreneurCustomerRepository.save(privateEntrepreneurCustomer);
        return  modelAndView;
    }

    /*--partner Customers--*/


    /*partner Otherpartner*/

    @GetMapping(value = "/otherpartner")
    public ModelAndView partnersOtherPartner( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }

    @GetMapping(value = "/otherpartner/create/companyotherpartner")
    public ModelAndView companyOtherPartnerCreate(ModelAndView modelAndView, HttpSession httpSession) {
        CompanyOtherPartner companyOtherPartner = new CompanyOtherPartner();
        companyOtherPartner.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
       modelAndView.addObject("companyOtherPartner",companyOtherPartner);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.companyOtherPartnerCreate);
         modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value ="/otherpartner/create/companyotherpartner")
    public ModelAndView companyOtherPartnerCreate(@Valid CompanyOtherPartner companyOtherPartner, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("companyOtherPartner",companyOtherPartner);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragment", this.companyOtherPartnerCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        companyOtherPartnerRepository.save(companyOtherPartner);
        return  modelAndView;
     }

    @GetMapping(value = "/otherpartner/create/individualotherpartner")
    public ModelAndView individualOtherPartnerCreate(ModelAndView modelAndView, HttpSession httpSession) {
       IndividualOtherPartner individualOtherPartner = new IndividualOtherPartner();
        individualOtherPartner.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("individualOtherPartner",individualOtherPartner);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.individualOtherPartnerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "/otherpartner/create/individualotherpartner")
    public ModelAndView individualOtherPartnerCreate(@Valid IndividualOtherPartner individualOtherPartner, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("individualOtherPartner",individualOtherPartner);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragment", this.individualOtherPartnerCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
        individualOtherPartnerRepository.save(individualOtherPartner);
        return  modelAndView;
    }

    @GetMapping(value = "/otherpartner/create/privateentrepreneurotherpartner")
    public ModelAndView privateEntrepreneurOtherPartnerCreate(ModelAndView modelAndView, HttpSession httpSession) {
         PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner = new PrivateEntrepreneurOtherPartner();
         privateEntrepreneurOtherPartner.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("privateEntrepreneurOtherPartner",privateEntrepreneurOtherPartner);
         modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.privateEntrepreneurOtherPartnerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value ="/otherpartner/create/privateentrepreneurotherpartner")
    public ModelAndView privateEntrepreneurOtherPartnerCreate(@Valid PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

             modelAndView.addObject("privateEntrepreneurOtherPartner",privateEntrepreneurOtherPartner);
             modelAndView.addObject("navBar", this.partnerNavBar);
             modelAndView.addObject("fragment", this.privateEntrepreneurOtherPartnerCreate);
             modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
            return  modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        privateEntrepreneurOtherPartnerRepository.save(privateEntrepreneurOtherPartner);
        return  modelAndView;
    }
    /*--partner Otherpartner--*/
    /*partner Supplier*/

    @GetMapping(value = "/supplier")
    public ModelAndView supplierPartner( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
              modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplier);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);


        return modelAndView;
    }


    @GetMapping( value ="/supplier/create/individualsupplier")
   public ModelAndView individualSupplierCreate(ModelAndView modelAndView, HttpSession httpSession) {
        IndividualSupplier individualSupplier = new IndividualSupplier();
        individualSupplier.setOrganization((Organization) httpSession.getAttribute("organizationId"));
       modelAndView.setViewName("app/app");
        modelAndView.addObject("individualSupplier",individualSupplier);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.individualSupplierCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);


         return modelAndView;
    }
    @PostMapping(value ="/supplier/create/individualsupplier")
    public ModelAndView individualSupplierCreate(@Valid IndividualSupplier individualSupplier, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("individualSupplier",individualSupplier);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragment", this.individualSupplierCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
        individualSupplierRepository.save(individualSupplier);
        return  modelAndView;
    }


    /*--partner Supplier--*/


    @GetMapping(value = "/customer/debt")
    public ModelAndView partnerCustomerDebt( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerFragment);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }

    @GetMapping(value = "/supplier/debt")
    public ModelAndView partnerSupplierDebt( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplierFragment);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);


        return modelAndView;
    }

    @GetMapping( value = "/otherpartner/debt")
    public ModelAndView partnerOtherPartnerDebt( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerOtherPartnerFragment);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }


}




