package com.harsh.productapi;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
public class ProductApplication extends ResourceConfig {
 public ProductApplication(){ packages("com.harsh.productapi"); register(JacksonFeature.class); }
}