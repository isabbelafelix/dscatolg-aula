package com.devsuperior.dscatalog.service;

import com.devsuperior.dscatalog.dto.CategoryDto;
import com.devsuperior.dscatalog.entities.CategoryEntity;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        List<CategoryEntity> list = this.repository.findAll();

        //converter uma lista de categoryEntity para uma lista de categoryDto
        //Pra cada elemento x -> vou transformar ele em outro elemento

        return list.stream()
                .map(x -> new CategoryDto(x))
                .collect(Collectors.toList());

        /**Implementação com expressão lambda;
         * Stream, é converter a sua coleção normal, um list, exemplo
         * Convertendo ele para um stream. Um recurso que permite
         * trabalhar com funções de alta ordem, funções com expressões lambda.
         * Podendo fazer transformações na sua coleção.
         * Uma função muito comum é a função Map() = tranforma cada elemento original
         * em outras coisas, aplicando uma função a cada elemento da sua lista
         */

    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        //vai no repository, faz um findById e retorna um Optional
        Optional<CategoryEntity> entityOptional = this.repository.findById(id);
        //tento acessar o objeto que está no Optional(categoryEntity)
        //se o category não existir eu vou instanciar uma excessão
        CategoryEntity entity =
                entityOptional.orElseThrow(() -> new EntityNotFoundException("Entity not found"));

        return new CategoryDto(entity);
    }
    @Transactional
    public CategoryDto insert(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());

        entity = repository.save(entity);
        return new CategoryDto(entity);
    }
}
