package co.edu.university.fashionkids.controller;

import co.edu.university.fashionkids.dto.SellProductRequest;
import co.edu.university.fashionkids.model.ErrorResponse;
import co.edu.university.fashionkids.model.Product;
import co.edu.university.fashionkids.model.SuccessResponse;
import co.edu.university.fashionkids.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> product() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> filterById(@PathVariable int productId) {
        Product product = productService.filterById(productId);
        if(product == null) {
            ErrorResponse error = new ErrorResponse("El producto no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/sell-product")
    public ResponseEntity<?> sellProduct(@RequestBody SellProductRequest productRequest) {
        Product product = productService.filterById(productRequest.getProductId());
        if (product != null) {
            SuccessResponse statusSellProduct = productService.sellProduct(product, productRequest.getQuantity());
            if(statusSellProduct != null) {
                return ResponseEntity.ok(statusSellProduct);
            }else {
                ErrorResponse error = new ErrorResponse("No hay suficiente productos en el inventario para esta venta.");
                return ResponseEntity.badRequest().body(error);
            }
        } else {
            ErrorResponse error = new ErrorResponse("El producto no existe.");
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/low-stock-list")
    public List<Product> getProductsLowStock() {
        return productService.filterByLowStock();
    }
}
