package com.pos.product.repository;

// import com.pos.product.model.Product;
// import org.jsoup.Jsoup;
// import org.jsoup.nodes.Document;
// import org.jsoup.nodes.Element;
// import org.jsoup.select.Elements;
// import org.springframework.stereotype.Repository;

// import java.io.IOException;
// import java.net.URL;
// import java.util.ArrayList;
// import java.util.List;

// @Repository
// public class ProductDB implements ProductRepository {
//     private List<Product> products = null;

//     ProductDB() {
//         products = new ArrayList<>();
//         products.add(new Product("1", "cola", 3, "Cola.jpg", 1, 13));
//         products.add(new Product("2", "sprite", 4, "Sprite.png", 1, 15));
//         products.add(new Product("3", "red bull", 5, "Redbull.jpg", 1, 5));
//         products.add(new Product("4", "Milk", 5, "Milk.jpg", 1, 5));

//     }

//     @Override
//     public List<Product> allProducts() {
//         return products;
//     }

//     @Override
//     public Product findProduct(String productId) {
//         for (Product p : allProducts()) {
//             if (p.getId().equals(productId)) {
//                 return p;
//             }
//         }
//         return null;
//     }


// }
