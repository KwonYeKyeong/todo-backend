package todo.backend.repository;

import java.util.ArrayList;
import java.util.Collection;
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

	public List<Card> findAll() {
		Collection<Card> cards = cachedCards.values();
		List<Card> list = new ArrayList<>(cards);
		Collections.sort(list, Card::compareTo);

		return list;
	}

	public Card save(Card card) {
		Long id = (long)cachedCards.size() + 1;
		card.setId(id);
		card.setStatus(CardStatus.TODO);

		return cachedCards.put(id, card);
	}

	public Card update(Long id, Card card) {
		if (!cachedCards.containsKey(id)) {
			throw new NotFoundException(id);
		}

		Card savedCard = cachedCards.get(id);
		savedCard.setStatus(card.getStatus());
		return savedCard;
	}

	public void delete(Long id) {
		if (!cachedCards.containsKey(id)) {
			throw new NotFoundException(id);
		}

		cachedCards.remove(id);
	}
}
