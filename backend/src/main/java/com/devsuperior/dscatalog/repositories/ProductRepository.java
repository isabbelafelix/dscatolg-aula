package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.CategoryEntity;
import com.devsuperior.dscatalog.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
