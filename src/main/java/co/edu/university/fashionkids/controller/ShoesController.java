package co.edu.university.fashionkids.controller;

import co.edu.university.fashionkids.model.ErrorResponse;
import co.edu.university.fashionkids.model.Product;
import co.edu.university.fashionkids.model.Shoes;
import co.edu.university.fashionkids.service.ProductService;
import co.edu.university.fashionkids.service.ShoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/shoes")
public class ShoesController {
    @Autowired
    private ShoesService shoesService;
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Shoes> list() {
        return shoesService.getShoesList();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addShoes(@RequestBody Shoes shoes) {
        if(shoes.getType() == Product.ProductType.SHOES) {
            Product product = productService.filterById(shoes.getId());
            if(product == null) {
                productService.addProduct(shoes);
                return ResponseEntity.ok(shoes);
            } else {
                return ResponseEntity.badRequest().body(new ErrorResponse("El producto ya existe, debes actualizarlo"));
            }
        } else {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error, el producto no es de tipo SHOES"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShoes(@RequestBody Shoes shoes) {
        if(shoes.getType() == Product.ProductType.SHOES) {
            Product product = productService.filterById(shoes.getId());
            if(product != null) {
                Product successUpdate = productService.updateProduct(product, shoes);
                return ResponseEntity.ok(successUpdate);
            } else {
              return ResponseEntity.badRequest().body(new ErrorResponse("El producto no existe, debes crearlo"));
            }
        }else {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error, el producto no es de tipo SHOES"));
        }
    }
}
