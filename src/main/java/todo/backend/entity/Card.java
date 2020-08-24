package todo.backend.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String title;

	@Pattern(regexp = "^[가-힣a-zA-Z0-9]{1,15}+$")
	private String assignee;

	@Enumerated(EnumType.STRING)
	private CardStatus status = CardStatus.TODO;

	@Min(1)
	@Max(3)
	private Integer priority;

	@CreatedDate
	private LocalDate created;
}
