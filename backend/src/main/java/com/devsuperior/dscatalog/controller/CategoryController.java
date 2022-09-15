package com.devsuperior.dscatalog.controller;

import com.devsuperior.dscatalog.entities.CategoryEntity;
import com.devsuperior.dscatalog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService service;
    @GetMapping
    public ResponseEntity<List<CategoryEntity>> findAll(){
        List<CategoryEntity> list = this.service.findAll();

        return ResponseEntity.ok().body(list);
    }

}
