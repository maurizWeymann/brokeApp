package de.htwberlin.webtech.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalculationRepository extends JpaRepository<CalculationEntity, Long> {

    List<CalculationEntity> findAllById(Integer id);

}
