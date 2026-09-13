package com.harsh.productapi.repository;
import com.harsh.productapi.model.Product;
import java.math.BigDecimal; import java.util.*; import java.util.concurrent.ConcurrentHashMap;
public class ProductRepository {
 private static final ProductRepository INSTANCE=new ProductRepository(); private final Map<Long,Product> data=new ConcurrentHashMap<>();
 private ProductRepository(){data.put(1L,new Product(1L,"Laptop","14-inch student laptop",new BigDecimal("54999.00"),10));data.put(2L,new Product(2L,"Wireless Mouse","USB wireless mouse",new BigDecimal("799.00"),40));}
 public static ProductRepository getInstance(){return INSTANCE;}
 public List<Product> findAll(){return data.values().stream().sorted(Comparator.comparing(Product::getId)).toList();}
 public Optional<Product> findById(long id){return Optional.ofNullable(data.get(id));}
 public boolean exists(long id){return data.containsKey(id);} public Product save(Product p){data.put(p.getId(),p);return p;}
}