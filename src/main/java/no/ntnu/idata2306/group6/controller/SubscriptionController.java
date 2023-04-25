package no.ntnu.idata2306.group6.controller;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.idata2306.group6.entity.Subscription;
import no.ntnu.idata2306.group6.repository.SubscriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private SubscriptionRepository subscriptionRepository;
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class.getSimpleName());

    public SubscriptionController(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping
    @Operation(
            summary = "Get all subscriptions",
            description = "List of all the subscriptions currently in the collection"
    )
    public ResponseEntity<Object> getALl() {
        logger.info("Getting all ");
        Iterable<Subscription> subscriptions = subscriptionRepository.findAll();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
}