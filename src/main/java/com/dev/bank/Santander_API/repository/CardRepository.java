package com.dev.bank.Santander_API.repository;

import com.dev.bank.Santander_API.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
