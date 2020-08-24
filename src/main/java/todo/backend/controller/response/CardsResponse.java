package todo.backend.controller.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import todo.backend.entity.Card;

@Getter
@Setter
public class CardsResponse {

	List<CardResponse> cards;

	public static CardsResponse create(List<Card> cards) {
		List<CardResponse> cardResponses = cards.stream()
			.map(CardResponse::create)
			.collect(Collectors.toList());

		CardsResponse response = new CardsResponse();
		response.setCards(cardResponses);

		return response;
	}
}
