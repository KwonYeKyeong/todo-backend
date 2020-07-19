package todo.backend.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;


import org.springframework.stereotype.Component;

import springfox.documentation.schema.Entry;
import todo.backend.entity.Card;
import todo.backend.entity.CardStatus;

@Component
public class CardRepository {

	private final Map<Long, Card> cachedCards = new HashMap<>();

	public List<Card> findAll() {
		/*List<Entry<Long, Card>> list_cachedCards=new ArrayList<Entry<Long, Card>>(cachedCards.entrySet());
		Collections.sort(list_cachedCards, new Comparator<Entry<Long,Card>>(){
			public int compare(Entry<Long, Card> obj1, Entry<Long,Card> obj2){
				return obj1.getValue().compareTo(obj2.getValue());
			}
		});
	}
		/*List <Card> cachedCardsList=new ArrayList<>(cachedCards.keySet());
		Collections.sort(cachedCardsList, new Comparator(){
			@Override
			public int compare(Card c1, Card c2){
				return c1.getPriority().compareTo(c2.getPriority());
			}
		});*/
		return new ArrayList<>(cachedCards.values());

	}

	public Card save(Card card) {
		Integer priority=card.getPriority();
		Long id = (long)cachedCards.size() + 1;
		if (priority>3 || priority<0) {
			throw new RuntimeException(
					String.format("card[id:%d] 우선순위 안됨.", id)
			);
		}
		else{
			card.setId(id);
			card.setStatus(CardStatus.TODO);
			return cachedCards.put(id, card);
		}
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
	public void delete(Long id){
		if (!cachedCards.containsKey(id)) {
			throw new RuntimeException(
					String.format("card[id:%d] does not exits.", id)
			);
		}
		cachedCards.remove(id);
	}
}
