package todo.backend.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {

	private Long id;
	private String title;
	private String assignee;
	private CardStatus status;
	private Integer priority;
	private LocalDate created = LocalDate.now();

	// TODO: add updated
}
