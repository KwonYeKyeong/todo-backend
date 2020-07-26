package todo.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import todo.backend.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

	List<Card> findAllByOrderByPriorityAsc();
}