package com.cefet.ms_customer.services;

import com.cefet.ms_customer.model.CustomerModel;
import com.cefet.ms_customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel getCustomerById(String idCustomer) {
        return customerRepository.findById(idCustomer).orElse(null);
    }

    public CustomerModel getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }

    public boolean deleteCustomerById(String idCustomer) {
        try {
            customerRepository.deleteById(idCustomer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public CustomerModel createCustomer(CustomerModel customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Email já está em uso");
        }
        return customerRepository.save(customer);
    }

    public CustomerModel updateCustomer(String idCustomer, CustomerModel customerUpdated) {
        Optional<CustomerModel> optionalCustomer = customerRepository.findById(idCustomer);
        if (optionalCustomer.isPresent()) {
            CustomerModel customer = optionalCustomer.get();

            if (customerUpdated.getName() != null) {
                customer.setName(customerUpdated.getName());
            }
            if (customerUpdated.getAddress() != null) {
                customer.setAddress(customerUpdated.getAddress());
            }
            if (customerUpdated.getEmail() != null) {
                customer.setEmail(customerUpdated.getEmail());
            }
            if (customerUpdated.getPassword() != null) {
                customer.setPassword(customerUpdated.getPassword());
            }

            return customerRepository.save(customer);
        }
        return null;
    }

    public List<CustomerModel> getAllCustomer() {
        return customerRepository.findAll();
    }
}
