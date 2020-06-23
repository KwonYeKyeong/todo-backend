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

	public List<Card> getCards() {
		return cardRepository.findAllOrderByPriorityAsc();
	}

	public Card createCard(Card card) {
		return cardRepository.save(card);
	}

	public Card updateCard(Long id, Card card) {
		validateCardOrThrow(id);

		return cardRepository.update(id, card);
	}

	public void deleteCard(Long id) {
		validateCardOrThrow(id);

		cardRepository.deleteById(id);
	}

	private void validateCardOrThrow(Long id) {
		if (!cardRepository.doesCardExist(id)) {
			throw new RuntimeException(
				String.format("card[id:%d] does not exits.", id)
			);
		}
	}
}
