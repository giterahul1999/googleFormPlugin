package com.googleFormPlugin.googleFormPlugin.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@Service
public class FormFillingService {
    // @Scheduled(cron = "0 * * * * ?")
    // public void fillGoogleForm() {
    //     try {
    //         submitForm();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // private void submitForm() throws IOException {
        // Define input parameters
        // String formActionURL = "https://docs.google.com/forms/d/e/1FAIpQLScZKQQlJYk9i-dwWTpf0-mu8hsE7S8EHpJVJcYcisqnnBp3jg/viewform?pli=1";
        // String formActionURL = "https://docs.google.com/forms/d/e/1FAIpQLScZKQQlJYk9i-dwWTpf0-mu8hsE7S8EHpJVJcYcisqnnBp3jg/viewform?usp=sf_link";
        // String Name = "Rahul Gite";
        // String Email = "ragite@mitaoe.ac.in";
        // String Address = "Jalgaon";

        // // Create the form data
        // String formData = "entry.2005620554=" + Name + "&entry.1045781291=" + Email  + "&entry.1065046570=" + Address;

        // // Send HTTP POST request to submit the form
        // sendPostRequest(formActionURL, formData);
    // }

    // private void sendPostRequest(String url, String formData) throws IOException {
    //     java.net.URL obj = new java.net.URL(url);
    //     java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();
    //     con.setRequestMethod("POST");
    //     con.setDoOutput(true);
        
    //     java.io.OutputStream os = con.getOutputStream();
    //     os.write(formData.getBytes());
    //     os.flush();
    //     os.close();

    //     int responseCode = con.getResponseCode();
    //     System.out.println("POST Response Code :: " + responseCode);
    // }

    //     private void sendPostRequest(String url, String formData) throws IOException {
    //     URL obj = new URL(url);
    //     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    //     con.setRequestMethod("POST");
    //     con.setDoOutput(true);
    //     con.setRequestProperty("User-Agent", "Mozilla/5.0");
    //     con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

    //     try (OutputStream os = con.getOutputStream()) {
    //         os.write(formData.getBytes());
    //         os.flush();
    //     }

    //     int responseCode = con.getResponseCode();
    //     System.out.println("POST Response Code :: " + responseCode);

    //     if (responseCode == HttpURLConnection.HTTP_OK) { // success
    //         System.out.println("Form submitted successfully.");
    //     } else {
    //         System.out.println("Form submission failed.");
    //     }
    // }

    @Value("${google.form.url}")
    private String googleFormUrl;

    private final RestTemplate restTemplate;

    public FormFillingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 60000)
    public void submitForm() {
        Map<String, String> formData = new HashMap<>();
        formData.put("entry.2005620554", "Prfajyot Palande"); // Replace with actual form field IDs
        formData.put("entry.1045781291", "prpalande@mitaoe.ac.in"); // Replace with actual form field IDs
        formData.put("entry.1065046570", "Germany"); // Replace with actual form field IDs
        formData.put("entry.1166974658", "555-555-5555"); // Replace with actual form field IDs
        formData.put("entry.839337160", "Ok"); // Replace with actual form field IDs

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String formEncodedData = formData.entrySet()
                .stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        HttpEntity<String> request = new HttpEntity<>(formEncodedData, headers);

        try {
            URI uri = new URI(googleFormUrl);
            ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Form submitted successfully");
            } else {
                System.out.println("Form submission failed");
            }
        } catch (HttpClientErrorException e) {
    System.out.println("HTTP Status: " + e.getStatusCode());
    System.out.println("Response Body: " + e.getResponseBodyAsString());
    e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Form submission failed with exception: " + e.getMessage());
    }
    
    
    }
}
