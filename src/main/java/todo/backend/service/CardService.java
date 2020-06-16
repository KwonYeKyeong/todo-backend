package todo.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import todo.backend.entity.Card;
import todo.backend.entity.CardStatus;
import todo.backend.repository.CardRepository;

@RequiredArgsConstructor
@Service
public class CardService {

	private final CardRepository cardRepository;

	public List<Card> getCards() {
		return cardRepository.findAll();
	}

	public Card createCard(Card card) {
		card.setStatus(CardStatus.TODO);
		return cardRepository.save(card);
	}

	// @Transactional
	public Card updateCard(Long id, Card card) {
		Card savedCard = cardRepository.findById(id).orElseThrow();
		savedCard.setStatus(card.getStatus());
		cardRepository.save(savedCard);
		return savedCard;
	}

}
