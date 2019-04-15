package io.fdlessard.codebites.customer.async.controllers;

import io.fdlessard.codebites.customer.async.model.Customer;
import io.fdlessard.codebites.customer.async.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/customers/listenable")
public class ListenableFutureCustomerController {

    private CustomerService customerService;

    public ListenableFutureCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/isAlive", produces = "application/json")
    public ListenableFuture<String> isAlive() {
        log.info("ListenableFutureCustomerController.isAlive()");
        return AsyncResult.forValue("Hello World from CallableCustomerController");
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ListenableFuture<Customer> get(@PathVariable long id) {
        log.info("ListenableFutureCustomerController.get({})", id);
        return AsyncResult.forValue(customerService.getCustomerById(id));
    }

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public Iterable<Customer> getAll() {
        log.info("ListenableFutureCustomerController.getAll()");
        return customerService.geAllCustomers();
    }
}
