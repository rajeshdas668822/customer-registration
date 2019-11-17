package org.spring.cloud.demo.registration.repository;

import org.spring.cloud.demo.registration.entity.insurance.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio,Integer> {
}
