package co.edu.university.fashionkids.service;

import co.edu.university.fashionkids.model.Clothes;
import co.edu.university.fashionkids.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClothesService {
    private final ProductService productService;
    private ArrayList<Clothes> clothesList;

    public ClothesService(ProductService productService) {
        clothesList = new ArrayList<>();
        this.productService = productService;
    }

    public ArrayList<Clothes> getClotheList() {
        List<Product> products = productService.getProducts();
        ArrayList<Clothes> clothesList = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof Clothes && product.getType() == Product.ProductType.CLOTHE) {
                clothesList.add((Clothes) product);
            }
        }
        return clothesList;
    }

    public void setClotheList(ArrayList<Clothes> clothesList) {
        this.clothesList = clothesList;
    }

    public void addClothes(Clothes clothes) {
        clothesList.add(clothes);
    }
}
