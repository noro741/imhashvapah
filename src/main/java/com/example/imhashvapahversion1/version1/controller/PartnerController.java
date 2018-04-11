package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.IndividualCustomer;
import com.example.imhashvapahversion1.version1.repository.OrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.UniversalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Locale;


@Controller
@RequestMapping("account/partner")
public class PartnerController extends BaseController {


    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    UniversalRepository universalRepository;



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

            return  modelAndView;
    }

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




