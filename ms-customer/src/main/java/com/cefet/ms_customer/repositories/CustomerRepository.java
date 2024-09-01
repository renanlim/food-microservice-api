package com.cefet.ms_customer.repositories;

import com.cefet.ms_customer.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, String> {
    Optional<CustomerModel> findById(String idCustomer);
    Optional<CustomerModel> findByEmail(String email);
    CustomerModel save(CustomerModel customer);
    void deleteById(String idCustomer);
    List<CustomerModel> findAll();
}