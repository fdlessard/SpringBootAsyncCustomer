package io.fdlessard.codebites.customer.async.controllers;


import io.fdlessard.codebites.customer.async.model.Customer;
import io.fdlessard.codebites.customer.async.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@Slf4j
@RestController
@RequestMapping(value = "/customers/callable")
public class CallableCustomerController {

    private CustomerService customerService;

    public CallableCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/isAlive", produces = "application/json")
    public Callable<String> isAlive() {
        log.info("CallableCustomerController.isAlive()");
        return () -> "Hello World from CallableCustomerController";
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public Callable<Customer> get(@PathVariable long id) {
        log.info("CallableCustomerController.get({})", id);
        return () -> customerService.getCustomerById(id);
    }

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public Iterable<Customer> getAll() {
        log.info("CallableCustomerController.getAll()");
        return customerService.geAllCustomers();
    }
}

