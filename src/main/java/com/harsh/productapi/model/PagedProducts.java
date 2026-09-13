package com.harsh.productapi.model;
import java.util.List;
public record PagedProducts(List<Product> items,int page,int size,int totalItems,int totalPages) {}