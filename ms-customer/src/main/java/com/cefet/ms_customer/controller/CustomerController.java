package com.cefet.ms_customer.controller;

import com.cefet.ms_customer.model.CustomerModel;
import com.cefet.ms_customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customer) {
        try {
            CustomerModel newCustomer = customerService.createCustomer(customer);
            return ResponseEntity.ok(newCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idCustomer}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable String idCustomer) {
        CustomerModel customer = customerService.getCustomerById(idCustomer);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerModel> getCustomerByEmail(@PathVariable String email) {
        CustomerModel customer = customerService.getCustomerByEmail(email);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{idCustomer}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable String idCustomer, @RequestBody CustomerModel customerUpdated) {
        CustomerModel updatedCustomer = customerService.updateCustomer(idCustomer, customerUpdated);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idCustomer}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String idCustomer) {
        boolean deleted = customerService.deleteCustomerById(idCustomer);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getAllCustomer() {
        List<CustomerModel> customers = customerService.getAllCustomer();
        return ResponseEntity.ok(customers);
    }
}
