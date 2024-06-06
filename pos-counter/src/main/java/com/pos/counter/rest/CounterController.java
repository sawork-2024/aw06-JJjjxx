package com.pos.counter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pos.api.CounterApi;
import com.pos.counter.service.CounterService;
import com.pos.dto.CartDto;

@RestController
public class CounterController implements CounterApi {

    private CounterService counterService;

    @Autowired
    public void setCounterService(CounterService counterService) {
        this.counterService = counterService;
    }

    public ResponseEntity<Double> checkout(CartDto cartDto) {
        return ResponseEntity.ok(counterService.getTotal(cartDto));
    }

}
