package com.devsuperior.dscatalog.service;

import com.devsuperior.dscatalog.dto.CategoryDto;
import com.devsuperior.dscatalog.entities.CategoryEntity;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.service.exceptions.DataBaseException;
import com.devsuperior.dscatalog.service.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDto> findAllPaged(PageRequest pageRequest) {
        Page<CategoryEntity> list = this.repository.findAll(pageRequest);

        //converter uma lista de categoryEntity para uma lista de categoryDto
        //Pra cada elemento x -> vou transformar ele em outro elemento

        return list.map(x -> new CategoryDto(x));

//        return list.stream()
//                .map(x -> new CategoryDto(x))
//                .collect(Collectors.toList());

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
                entityOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new CategoryDto(entity);
    }

    @Transactional
    public CategoryDto insert(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());

        entity = repository.save(entity);
        return new CategoryDto(entity);
    }

    @Transactional
    public CategoryDto update(Long id, CategoryDto dto) {
        try {
            CategoryEntity entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }

    }
}
