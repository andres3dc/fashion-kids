package co.edu.university.fashionkids.service;

import co.edu.university.fashionkids.model.Product;
import co.edu.university.fashionkids.model.SuccessResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ArrayList<Product> products;

    public ProductService() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }


    public Product filterById(int id) {
        for(Product product:  products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public SuccessResponse sellProduct(Product product, int quantity) {
        if(product.getQuantity() >= quantity && product.getQuantity() - quantity >= 0) {
            product.setQuantity(product.getQuantity() - quantity);
            return new SuccessResponse("Venta completada");
        }
        return null;
    }

    public ArrayList<Product> filterByLowStock() {
        return products.stream().filter(product -> product.getQuantity() <= 10).collect(Collectors.toCollection(ArrayList::new));
    }

    public Product updateProduct(Product product, Product updateProduct) {
        int index = products.indexOf(product);
        System.out.println(index);
        products.set(index, updateProduct);
        return updateProduct;
    }
}
