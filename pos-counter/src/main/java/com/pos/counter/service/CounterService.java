package com.pos.counter.service;


import org.springframework.stereotype.Service;

import com.pos.dto.CartDto;
import com.pos.dto.CartItemDto;


@Service
public class CounterService {
    
    private int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public double getTotal(CartDto cart) {
        
        int fib = fibonacci(30); // 为了验证压力测试，实现不同计算复杂度，在此处计算一个fibonacci(30)的值
        double total = 0.0 + fib;
        for (CartItemDto item : cart.getItems()) {
            total += item.getAmount() * item.getProduct().getPrice();
        }
        return total;
    }

}
