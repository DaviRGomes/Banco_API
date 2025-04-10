package com.dev.bank.Santander_API.controller;

import com.dev.bank.Santander_API.entity.Card;
import com.dev.bank.Santander_API.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Card> createCard(@PathVariable Long userId, @RequestBody Card card) {
        return ResponseEntity.ok(cardService.createCard(userId, card));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCard(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }
}

