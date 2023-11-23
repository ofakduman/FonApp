package com.digitalcreators.digicreatefon.repository;

import com.digitalcreators.digicreatefon.model.SystemDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemDateRepository extends JpaRepository<SystemDate, Long> {
    Optional<SystemDate> findTopByOrderByIdDesc();
}
