package com.dev.bank.Santander_API.service;

import com.dev.bank.Santander_API.entity.Card;
import com.dev.bank.Santander_API.entity.User;
import com.dev.bank.Santander_API.repository.CardRepository;
import com.dev.bank.Santander_API.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public CardService(UserRepository userRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    public Card createCard(Long userId, Card card) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        card.setUser(user);
        user.setCard(card);

        cardRepository.save(card);
        userRepository.save(user);

        return card;
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
    }
}