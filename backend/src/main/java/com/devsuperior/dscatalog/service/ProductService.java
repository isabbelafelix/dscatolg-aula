package com.devsuperior.dscatalog.service;

import com.devsuperior.dscatalog.dto.ProductDto;
import com.devsuperior.dscatalog.entities.ProductEntity;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.service.exceptions.DataBaseException;
import com.devsuperior.dscatalog.service.exceptions.ResourceNotFoundException;
import com.sun.xml.bind.v2.TODO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAllPaged(PageRequest pageRequest) {
        Page<ProductEntity> list = this.repository.findAll(pageRequest);

        //converter uma lista de categoryEntity para uma lista de categoryDto
        //Pra cada elemento x -> vou transformar ele em outro elemento

        return list.map(x -> new ProductDto(x));

//        return list.stream()
//                .map(x -> new ProductDto(x))
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
    public ProductDto findById(Long id) {
        //vai no repository, faz um findById e retorna um Optional
        Optional<ProductEntity> entityOptional = this.repository.findById(id);
        //tento acessar o objeto que está no Optional(categoryEntity)
        //se o category não existir eu vou instanciar uma excessão
        ProductEntity entity =
                entityOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new ProductDto(entity, entity.getCategories());
    }

    @Transactional
    public ProductDto insert(ProductDto dto) {
        ProductEntity entity = new ProductEntity();
        //TODO: modificar
        entity.setName(dto.getName());

        entity = repository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional
    public ProductDto update(Long id, ProductDto dto) {
        try {
            ProductEntity entity = repository.getReferenceById(id);
            //TODO: modificar
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new ProductDto(entity);
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
