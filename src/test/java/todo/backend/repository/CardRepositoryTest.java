package todo.backend.repository;

import static org.assertj.core.api.BDDAssertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestConstructor;

import lombok.AllArgsConstructor;
import todo.backend.entity.Card;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AllArgsConstructor
@DataJpaTest
class CardRepositoryTest {

	final CardRepository cardRepository;
	final TestEntityManager entityManager;

	@Test
	@DisplayName("저장 시 id, created 자동 초기화")
	void save() {

		// given
		Card card = Card.builder()
			.title("t1")
			.assignee("a1")
			.priority(3)
			.created(LocalDate.now())
			.build();

		// when
		Card savedCard = cardRepository.save(card);

		// then
		then(savedCard.getId()).isNotNull();
		then(savedCard.getCreated()).isNotNull();
	}

	@Test
	@DisplayName("카드 우선순위 오름차순으로 리턴")
	void findAllByOrderByPriorityAsc() {

		// given
		Card c1 = Card.builder()
			.title("t1")
			.assignee("a1")
			.priority(3)
			.created(LocalDate.now())
			.build();
		Card c2 = Card.builder()
			.title("t2")
			.assignee("a2")
			.priority(1)
			.created(LocalDate.now())
			.build();
		Card c3 = Card.builder()
			.title("t3")
			.assignee("a3")
			.priority(2)
			.created(LocalDate.now())
			.build();

		entityManager.persist(c1);
		entityManager.persist(c2);
		entityManager.persist(c3);

		// when
		List<Card> savedCards = cardRepository.findAllByOrderByPriorityAsc();

		// then
		then(savedCards.get(0).getPriority()).isEqualTo(1);
		then(savedCards.get(0).getTitle()).isEqualTo("t2");
		then(savedCards.get(2).getPriority()).isEqualTo(3);
		then(savedCards.get(2).getTitle()).isEqualTo("t1");

	}
}
