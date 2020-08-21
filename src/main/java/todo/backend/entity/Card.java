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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank    //공백만 들어오는 것 허용x, 중간에 공백은 허용o
	@Size(max = 20)
	private String title;

	@Enumerated(EnumType.STRING)
	private CardStatus status;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]{1,15}+$")
	private String assignee;

	@Min(value = 1)
	@Max(value = 3)
	private Integer priority;

	private LocalDate created = LocalDate.now();

}
