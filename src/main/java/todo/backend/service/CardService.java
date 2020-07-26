package todo.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import todo.backend.entity.Card;
import todo.backend.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class CardService {

	private final CardRepository cardRepository;

	public List<Card> getCards() {
		return cardRepository.findAll();
	}

	public Card createCard(Card card) {
		return cardRepository.save(card);
	}

	public Card getCard(Long id) {
		return cardRepository.findId(id);
	}

	public Card updateCard(Long id, Card card) {
		return cardRepository.update(id, card);
	}

	public void deleteCard(Long id) {
		cardRepository.delete(id);
	}

}
