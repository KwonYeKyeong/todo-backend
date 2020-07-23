package todo.backend.entity;

import javax.validation.constraints.*;
import java.time.LocalDate;


public class Card {

	private Long id;


	@NotBlank	//공백만 들어오는 것 허용x, 중간에 공백은 허용o
	@Size(max=20)
	private String title;

	private CardStatus status;

	@NotBlank
	@Pattern(regexp="^[a-zA-Z0-9가-힣]{1,15}+$")
	private String assignee;

	@Min(value=1)
	@Max(value=3)
	private Integer priority;

	private LocalDate created= LocalDate.now();

	// TODO: add created, updated
	// TODO: add order

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CardStatus getStatus() {
		return status;
	}

	public void setStatus(CardStatus status) {
		this.status = status;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public LocalDate getCreated() {
		return created;
	}

	//public void setCreated(LocalDate created) {this.created = created;}
}
