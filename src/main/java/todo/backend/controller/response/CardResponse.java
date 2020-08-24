package todo.backend.controller.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import todo.backend.entity.Card;
import todo.backend.entity.CardStatus;

@Getter
@Setter
public class CardResponse {

	private Long id;
	private String title;
	private String assignee;
	private CardStatus status;
	private Integer priority;
	private LocalDate created;

	public static CardResponse create(Card card) {
		CardResponse response = new CardResponse();
		response.setId(card.getId());
		response.setTitle(card.getTitle());
		response.setAssignee(card.getAssignee());
		response.setStatus(card.getStatus());
		response.setPriority(card.getPriority());
		response.setCreated(card.getCreated());

		return response;
	}
}
