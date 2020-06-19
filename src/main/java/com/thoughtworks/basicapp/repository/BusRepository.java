package com.thoughtworks.basicapp.repository;

import com.thoughtworks.basicapp.domain.Bus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
}
