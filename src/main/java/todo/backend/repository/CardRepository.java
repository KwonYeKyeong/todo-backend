package todo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import todo.backend.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
