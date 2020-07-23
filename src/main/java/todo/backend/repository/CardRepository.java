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

		List<Card> cardList=new ArrayList<>(cachedCards.values());
		System.out.println(cardList);
		cardList.sort(new Comparator<Card>(){
			@Override
			public int compare(Card arg1, Card arg2){
				int age1=arg1.getPriority();
				int age2=arg2.getPriority();

				if(age1==age2) return 0;
				else if(age1>age1)return 1;
				else return -1;
			}
		});

		return cardList;

		//return new ArrayList<>(cachedCards.values());

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
