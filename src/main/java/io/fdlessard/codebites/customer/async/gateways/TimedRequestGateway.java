package io.fdlessard.codebites.customer.async.gateways;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;


@Slf4j
@Component
public class TimedRequestGateway {

    private RestOperations timedRequestRestTemplate;

    public TimedRequestGateway(RestOperations timedRequestRestTemplate) {
        this.timedRequestRestTemplate = timedRequestRestTemplate;
    }

    public String makeTimedRequest(int milliseconds) {

        return timedRequestRestTemplate.getForObject("http://localhost:8090/timedRequest/{milliseconds}", String.class, milliseconds);
    }
}
