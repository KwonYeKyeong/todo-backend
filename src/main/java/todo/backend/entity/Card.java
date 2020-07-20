package todo.backend.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Card implements Comparable{

	private Long id;

	@NotBlank
	@Size(max=20)
	private String title;

	private String assignee;

	private CardStatus status;

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
