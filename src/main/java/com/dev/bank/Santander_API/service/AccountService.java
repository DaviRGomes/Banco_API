package com.dev.bank.Santander_API.service;

import com.dev.bank.Santander_API.entity.Account;
import com.dev.bank.Santander_API.entity.User;
import com.dev.bank.Santander_API.repository.AccountRepository;
import com.dev.bank.Santander_API.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public AccountService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Long userId, Account account) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        account.setUser(user);
        user.setAccount(account);

        accountRepository.save(account);
        userRepository.save(user);

        return account;
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }
}
