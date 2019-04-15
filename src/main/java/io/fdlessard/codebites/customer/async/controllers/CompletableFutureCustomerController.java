package io.fdlessard.codebites.customer.async.controllers;

import io.fdlessard.codebites.customer.async.model.Customer;
import io.fdlessard.codebites.customer.async.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping(value = "/customers/completable")
public class CompletableFutureCustomerController {

    private CustomerService customerService;

    public CompletableFutureCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/isAlive", produces = "application/json")
    public CompletableFuture<String> isAlive() {
        log.info("CompletableFutureCustomerController.isAlive()");
        return CompletableFuture.completedFuture("Hello World from CallableCustomerController");
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public CompletableFuture<Customer> get(@PathVariable long id) {
        log.info("CompletableFutureCustomerController.get({})", id);
        return CompletableFuture.completedFuture(customerService.getCustomerById(id));
    }

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public Iterable<Customer> getAll() {
        log.info("CompletableFutureCustomerController.getAll()");
        return customerService.geAllCustomers();
    }
}
