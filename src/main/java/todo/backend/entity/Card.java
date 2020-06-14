package todo.backend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {

	private Long id;

	private String title;

	private CardStatus status;

	// TODO: add created, updated
	// TODO: add order

}
