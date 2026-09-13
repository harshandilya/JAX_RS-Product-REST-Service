package com.harsh.productapi.service;
import com.harsh.productapi.model.*; import com.harsh.productapi.repository.ProductRepository;
import java.util.*;
public class ProductService {
 private static final ProductService INSTANCE=new ProductService(); private final ProductRepository repository=ProductRepository.getInstance();
 public static ProductService getInstance(){return INSTANCE;}
 public PagedProducts list(int page,int size){List<Product> all=repository.findAll();int from=Math.min(page*size,all.size());int to=Math.min(from+size,all.size());int pages=(int)Math.ceil(all.size()/(double)size);return new PagedProducts(all.subList(from,to),page,size,all.size(),pages);}
 public Optional<Product> find(long id){return repository.findById(id);} public boolean exists(long id){return repository.exists(id);} public Product create(Product p){return repository.save(p);}
 public List<String> validate(Product p){List<String> e=new ArrayList<>();if(p==null){e.add("Request body is required");return e;}if(p.getId()==null||p.getId()<=0)e.add("id must be a positive integer");if(p.getName()==null||p.getName().isBlank())e.add("name is required");else if(p.getName().length()>100)e.add("name must not exceed 100 characters");if(p.getDescription()!=null&&p.getDescription().length()>500)e.add("description must not exceed 500 characters");if(p.getPrice()==null||p.getPrice().signum()<=0)e.add("price must be greater than 0");if(p.getStock()==null||p.getStock()<0)e.add("stock must be 0 or greater");return e;}
}