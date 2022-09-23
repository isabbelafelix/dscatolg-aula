package com.devsuperior.dscatalog.controller;

import com.devsuperior.dscatalog.dto.CategoryDto;
import com.devsuperior.dscatalog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<CategoryDto> list = this.service.findAllPaged(pageRequest);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    //@PathVariable é que vai na url com a barra
    //dado obrigatorio
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {

        CategoryDto dto = this.service.findById(id);

        return ResponseEntity.ok().body(dto);
    }

    //Quando for inserir ou atualizar, pode inserir vários dados daquele objeto
    @PostMapping
    public ResponseEntity<CategoryDto> insert(@RequestBody CategoryDto dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto dto) {
        dto = service.update(id, dto);

        return ResponseEntity.ok().body(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();

    }

}
