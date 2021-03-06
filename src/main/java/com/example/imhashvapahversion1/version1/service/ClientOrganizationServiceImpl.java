package com.example.imhashvapahversion1.version1.service;


import com.example.imhashvapahversion1.version1.repository.customer.CustomerClientOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ClientOrganizationServiceImpl implements ClientOrganizationService {
    @Autowired
    private CustomerClientOrganizationRepository clientOrganizationRepository;

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        Assert.notNull(fieldName);

        if (!fieldName.equals("clientOrganizationName")) {
            throw new UnsupportedOperationException("Field name not supported");
        }

        if (value == null) {
            return false;
        }

        if(clientOrganizationRepository.getByName(value.toString())!=null)
            return true;
        else
        return false;
    }

}