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
		return cardRepository.findAllByOrderByPriorityAsc();
	}

	public Card createCard(Card card) {
		return cardRepository.save(card);
	}

	public Card getCard(Long id) {
		return cardRepository.findById(id).orElseThrow();
	}

	public Card updateCard(Long id, Card card) {
		Card savedCard = cardRepository.findById(id).orElseThrow();
		savedCard.setStatus(card.getStatus());
		return cardRepository.save(savedCard);
	}

	public void deleteCard(Long id) {
		cardRepository.deleteById(id);
	}

}
