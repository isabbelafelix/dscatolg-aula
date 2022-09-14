package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryResource {

    private final CategoryService service;
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = this.service.findAll();

        return ResponseEntity.ok().body(list);
    }

}
