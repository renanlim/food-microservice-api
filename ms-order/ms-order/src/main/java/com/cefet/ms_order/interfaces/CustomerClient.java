package com.cefet.ms_order.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-customer")
public interface CustomerClient {

    @GetMapping("/cliente/{idCustomer}")
    boolean existsById(@PathVariable("idCustomer") String idCustomer);
}
