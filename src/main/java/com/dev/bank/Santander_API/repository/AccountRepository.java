package com.dev.bank.Santander_API.repository;

import com.dev.bank.Santander_API.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
