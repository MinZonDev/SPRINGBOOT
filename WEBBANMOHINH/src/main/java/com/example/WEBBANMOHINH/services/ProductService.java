package com.example.WEBBANMOHINH.services;

import com.example.WEBBANMOHINH.entity.Product;
import com.example.WEBBANMOHINH.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> GetAll(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        return productRepository.findAll(pageRequest);
    }
    public Page<Product> GetSearchProducts(String key, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.searchProducts(key, pageable);
    }
    public List<Product> GetAll() {
        return productRepository.findAll();
    }

    public void add(Product newProduct) {
        productRepository.save(newProduct);
    }

    public Product get(int id) {
        return productRepository.findById(id).stream().findFirst().orElse(null);
    }

//    public void edit(Product editedProduct) {
//        productRepository.save(editedProduct);
//    }
//    public void edit(Product editProduct) {
//        Product find = get(editProduct.getId());
//        if(find!=null) {
//            ///or implement clon()
//            find.setName(editProduct.getName());
//            find.setImage(editProduct.getImage());
//            find.setPrice(editProduct.getPrice());
//            productRepository.save(find);
//        }
//    }
    public void edit(Product editProduct, MultipartFile imageProduct) throws IOException {
        Product find = productRepository.findById(editProduct.getId()).orElse(null);
        if (find != null) {
            find.setName(editProduct.getName());
            if (imageProduct != null && !imageProduct.isEmpty()) {
                String imageName = imageProduct.getOriginalFilename();
                String imagePath = "D:\\DO_AN_JAVA\\SPRINGBOOT\\WEBBANMOHINH\\src\\main\\resources\\static\\images";
                Path path = Paths.get(imagePath + File.separator + imageName);
                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                find.setImage(imageName);
            }
            find.setPrice(editProduct.getPrice());
            productRepository.save(find);
        }
    }

}
