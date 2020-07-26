package todo.backend.entity;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card implements Comparable {

	private Long id;

	@NotBlank
	@Size(max = 20)
	private String title;

	@NotBlank
	@Pattern(regexp = "(^[a-zA-Z가-힣0-9]{1,15}+$)")
	private String assignee;

	private CardStatus status;

	@Min(1)
	@Max(3)
	private Integer priority;

	private LocalDate created = LocalDate.now();

	// TODO: add created, updated
	// TODO: add order

	@Override
	public int compareTo(Object o) {
		return this.getPriority().compareTo(((Card)o).getPriority());
	}
}
