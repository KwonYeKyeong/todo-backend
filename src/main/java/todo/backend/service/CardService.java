package todo.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import todo.backend.entity.Card;
import todo.backend.repository.CardRepository;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {this.cardRepository = cardRepository;}

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Long id, Card card) {
        return cardRepository.update(id, card);
    }
}
