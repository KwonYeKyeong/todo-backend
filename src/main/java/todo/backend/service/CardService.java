package todo.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import todo.backend.entity.Card;
import todo.backend.repository.CardRepository;

@RequiredArgsConstructor
@Service
public class CardService {

	private final CardRepository cardRepository;

	public Card getCard(Long id) {
		validateCardOrThrow(id);

		return cardRepository.findById(id).orElseThrow();
	}

	public List<Card> getCards() {
		return cardRepository.findAllByOrderByPriorityAsc();
	}

	public Card createCard(Card card) {
		return cardRepository.save(card);
	}

	// @Transactional
	public Card updateCard(Long id, Card card) {
		validateCardOrThrow(id);

		Card savedCard = cardRepository.findById(id).orElseThrow();
		savedCard.setStatus(card.getStatus());

		return cardRepository.save(savedCard);
	}

	public void deleteCard(Long id) {
		validateCardOrThrow(id);

		cardRepository.deleteById(id);
	}

	private void validateCardOrThrow(Long id) {
		if (!cardRepository.existsById(id)) {
			throw new RuntimeException(
				String.format("card[id:%d] does not exits.", id)
			);
		}
	}

}
