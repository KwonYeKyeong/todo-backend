package todo.backend.repository;

import java.util.*;

import org.springframework.stereotype.Component;

import todo.backend.entity.Card;
import todo.backend.entity.CardStatus;


@Component
public class CardRepository {

	private final Map<Long, Card> cachedCards = new HashMap<>();

	public List<Card> findAll() {
		//List<Long> cardList = new ArrayList<>(cachedCards.keySet());

		//Collections.sort(cardList, (o1, o2) -> (cachedCards.get(o1).getPriority().compareTo(cachedCards.get(o2).getPriority())));

		return new ArrayList<>(cachedCards.values());
	}

	public Card save(Card card) {
		Long id = (long)cachedCards.size() + 1;
		card.setId(id);
		card.setStatus(CardStatus.TODO);

		return cachedCards.put(id, card);

	}

	public Card update(Long id, Card card) {
		if (!cachedCards.containsKey(id)) {
			throw new RuntimeException(
				String.format("card[id:%d] does not exits.", id)
			);
		}

		Card savedCard = cachedCards.get(id);
		savedCard.setStatus(card.getStatus());
		return savedCard;
	}

	public void delete(Long id) {
		if(!cachedCards.containsKey(id)){
			throw new RuntimeException(
					String.format("card[id:%d] does not exits.", id)
			);
		}

		cachedCards.remove(id);
	}
}
