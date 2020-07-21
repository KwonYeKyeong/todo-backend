package todo.backend.entity;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class Card implements Comparable{

	private Long id;

	@NotBlank
	@Size(max=20)
	private String title;

	@NotBlank
	@Pattern(regexp="(^[a-zA-Z가-힣0-9]{1,15}+$)")
	private String assignee;

	private CardStatus status;

	@Min(1)
	@Max(3)
	private Integer priority;

	private LocalDate created = LocalDate.now();

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

	public String getAssignee() { return assignee; }

	public void setAssignee(String assignee) { this.assignee = assignee; }

	public Integer getPriority() { return priority; }

	public void setPriority(Integer priority) { this.priority = priority; }

	public LocalDate getCreated() { return created; }

	public void setCreated(LocalDate created) { this.created = created; }

	@Override
	public int compareTo(Object o) {
		return this.getPriority().compareTo(((Card)o).getPriority());
	}
}
