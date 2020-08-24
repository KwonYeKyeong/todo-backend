package todo.backend.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Card {

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20, nullable = false)
	private String title;

	@Column(length = 15, nullable = false)
	private String assignee;

	@Enumerated(EnumType.STRING)
	private CardStatus status = CardStatus.TODO;

	@Column(nullable = false)
	private Integer priority;

	@CreatedDate
	@Column(nullable = false)
	private LocalDate created;

	@Builder
	private Card(String title, String assignee, Integer priority) {
		this.title = title;
		this.assignee = assignee;
		this.priority = priority;
	}
}

