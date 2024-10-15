package co.edu.university.fashionkids.service;

import co.edu.university.fashionkids.model.Product;
import co.edu.university.fashionkids.model.Shoes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoesService {
    private final ProductService productService;
    private ArrayList<Shoes> shoesList;

    public ShoesService(ProductService productService) {
        shoesList = new ArrayList<>();
        this.productService = productService;
    }

    public ArrayList<Shoes> getShoesList() {
        List<Product> products = productService.getProducts();
        ArrayList<Shoes> shoesList = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof Shoes && product.getType() == Product.ProductType.SHOES) {
                shoesList.add((Shoes) product);
            }
        }
        return shoesList;
    }

    public void setShoesList(ArrayList<Shoes> shoesList) {
        this.shoesList = shoesList;
    }

    public void addShoes(Shoes shoes) {
        shoesList.add(shoes);
    }
}
