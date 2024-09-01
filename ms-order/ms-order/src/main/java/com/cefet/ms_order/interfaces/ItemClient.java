package com.cefet.ms_order.interfaces;

import com.cefet.ms_order.dto.ItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-item")
public interface ItemClient {

    @GetMapping("/item/{idItem}")
    ItemDTO getItemById(@PathVariable("idItem") String idItem);
}
