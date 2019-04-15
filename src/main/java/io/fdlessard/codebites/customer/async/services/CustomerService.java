package io.fdlessard.codebites.customer.async.services;

import io.fdlessard.codebites.customer.async.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer getCustomerById(long id);

    List<Customer> geAllCustomers();
}
