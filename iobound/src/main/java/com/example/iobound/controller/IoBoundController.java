package com.example.iobound.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@RestController
public class IoBoundController {

    private static final Logger logger = LoggerFactory.getLogger(IoBoundController.class);

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/io-bound/{url}")
    public String ioBound(@PathVariable String url) throws InterruptedException, IOException, IOException {
        logger.info("Received request to /io-bound/{}", url);

        // Simulate a network call
        String result = restTemplate.getForObject("http://" + url, String.class);
        logger.info("Response from {}: {}", url, result);

        // Simulate reading from a file
        ClassPathResource resource = new ClassPathResource("testfile.txt");
        String fileContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        logger.info("Read file content: {}", fileContent);

        // Simulate a delay
        TimeUnit.SECONDS.sleep(2);

        // Simulate another network call
        ResponseEntity<String> secondResult = restTemplate.getForEntity("https://github.com/soaresmaric?tab=repositories", String.class);
        logger.info("Response from example.com: {}", secondResult.getBody());

        return result + "\n" + fileContent + "\n" + secondResult.getBody();
    }

    @GetMapping("/test")
    public String test() {
        return "Test route working!";
    }

    @GetMapping("/heavy-processing")
    public String heavyProcessing() throws InterruptedException {
        logger.info("Heavy processing started");

        // Simulate heavy processing
        TimeUnit.SECONDS.sleep(5);

        logger.info("Heavy processing finished");
        return "Heavy processing complete";
    }
}