package co.edu.university.fashionkids.controller;

import co.edu.university.fashionkids.model.Clothes;
import co.edu.university.fashionkids.model.ErrorResponse;
import co.edu.university.fashionkids.model.Product;
import co.edu.university.fashionkids.service.ClothesService;
import co.edu.university.fashionkids.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/clothes")
public class ClothesController {
    @Autowired
    private ClothesService clothesService;
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Clothes> list() {
        return clothesService.getClotheList();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addClothe(@RequestBody Clothes clothes) {
        if(clothes.getType() == Product.ProductType.CLOTHE) {
            Product product = productService.filterById(clothes.getId());
            if(product == null) {
                productService.addProduct(clothes);
                return ResponseEntity.ok(clothes);
            } else {
                return ResponseEntity.badRequest().body( new ErrorResponse("El producto ya existe, debes actualizarlo"));
            }
        } else {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error, el producto no es de tipo CLOTHE"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateClothe(@RequestBody Clothes clothes) {
        if(clothes.getType() == Product.ProductType.CLOTHE) {
            Product product = productService.filterById(clothes.getId());
            if(product != null) {
                Product successUpdate = productService.updateProduct(product, clothes);
                return ResponseEntity.ok(successUpdate);
            } else {
              return ResponseEntity.badRequest().body(new ErrorResponse("El producto no existe, debes crearlo"));
            }
        }else {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error, el producto no es de tipo CLOTHE"));
        }
    }
}
