package com.riwi.LibrosYa.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.LibrosYa.infrastructure.persistence.LoanEntity;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
  List<LoanEntity> findByUserId(Long id);

  List<LoanEntity> findByBookId(Long id);
}
