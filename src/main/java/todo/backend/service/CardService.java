package todo.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todo.backend.entity.Card;
import todo.backend.repository.CardRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CardService {

	private final CardRepository cardRepository;

	public List<Card> getCards() {
		return cardRepository.findAll();
	}

	public Card createCard(Card card) {
		return cardRepository.save(card);
	}

	public Card updateCard(Long id, Card card) {
		return cardRepository.update(id, card);
	}

	public void deleteCard(Long id){ cardRepository.delete(id); }
}
