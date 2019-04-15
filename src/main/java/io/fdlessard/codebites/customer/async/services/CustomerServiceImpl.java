package io.fdlessard.codebites.customer.async.services;


import io.fdlessard.codebites.customer.async.gateways.TimedRequestGateway;
import io.fdlessard.codebites.customer.async.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private TimedRequestGateway timedRequestGateway;

    public CustomerServiceImpl(TimedRequestGateway timedRequestGateway) {
        this.timedRequestGateway = timedRequestGateway;
    }

    public Customer getCustomerById(long id) {

        log.debug("CustomerServiceImpl.get({})", id);
        String result = timedRequestGateway.makeTimedRequest(1000);
        return buildCustomer(id);
    }

    public List<Customer> geAllCustomers() {

        log.debug("CustomerController.getAll()");

        return Arrays.asList(
                buildCustomer(0L),
                buildCustomer(1L),
                buildCustomer(2L)
        );
    }

    private Customer buildCustomer(Long id) {
        return Customer.builder()
                .id(id)
                .lastName("lastName" + id)
                .firstName("firstName" + id)
                .company("company" + id)
                .addressId(id + 1)
                .productId(id + 2)
                .build();
    }

}
