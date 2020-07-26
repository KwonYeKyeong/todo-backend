package todo.backend.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import todo.backend.entity.Card;
import todo.backend.entity.CardStatus;
import todo.backend.exception.NotFoundException;

@Component
public class CardRepository {

	private final Map<Long, Card> cachedCards = new HashMap<>();

	public void validId(Long id) {
		if (!cachedCards.containsKey(id)) {
			throw new NotFoundException(id);
		}
	}

	public Card findId(Long id) {
		validId(id);
		Card savedCard = cachedCards.get(id);
		return savedCard;
	}

	public List<Card> findAll() {

		List<Card> cardList = new ArrayList<>(cachedCards.values());
		Collections.sort(cardList, (a, b) -> a.getPriority() - b.getPriority());
		return cardList;
	}

	public Card save(Card card) {
		Integer priority = card.getPriority();
		Long id = (long)cachedCards.size() + 1;
		card.setId(id);
		card.setStatus(CardStatus.TODO);
		return cachedCards.put(id, card);
	}

	public Card update(Long id, Card card) {
		validId(id);
		Card savedCard = cachedCards.get(id);
		savedCard.setStatus(card.getStatus());
		return savedCard;
	}

	public void delete(Long id) {
		validId(id);
		cachedCards.remove(id);
	}

}
