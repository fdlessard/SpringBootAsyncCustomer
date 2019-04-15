package io.fdlessard.codebites.customer.async.controllers;

import io.fdlessard.codebites.customer.async.model.Customer;
import io.fdlessard.codebites.customer.async.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@Slf4j
@RestController
@RequestMapping(value = "/customers/deferred")
public class DeferredResultCustomerController {

    private CustomerService customerService;

    public DeferredResultCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/isAlive", produces = "application/json")
    public DeferredResult<ResponseEntity<String>> isAlive() {
        log.info("DeferredResultCustomerController.isAlive()");
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        result.setResult(ResponseEntity.ok("Hello World from CallableCustomerController"));
        return result;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public DeferredResult<ResponseEntity<Customer>> get(@PathVariable long id) {
        log.info("DeferredResultCustomerController.get({})", id);
        DeferredResult<ResponseEntity<Customer>> result = new DeferredResult<>();
        result.setResult(ResponseEntity.ok(customerService.getCustomerById(id)));
        return result;
    }

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public Iterable<Customer> getAll() {
        log.info("DeferredResultCustomerController.getAll()");
        return customerService.geAllCustomers();
    }
}
