package com.satyam.BookMyShowBackend.Repository;

import com.satyam.BookMyShowBackend.Model.Theatre;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theatre, Integer> {
}
