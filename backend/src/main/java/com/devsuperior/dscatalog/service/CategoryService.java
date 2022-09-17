package com.devsuperior.dscatalog.service;

import com.devsuperior.dscatalog.dto.CategoryDto;
import com.devsuperior.dscatalog.entities.CategoryEntity;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
}
