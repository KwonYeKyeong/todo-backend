package todo.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import todo.backend.controller.request.CreateCardRequest;
import todo.backend.controller.request.UpdateCardRequest;
import todo.backend.controller.response.CardResponse;
import todo.backend.entity.Card;
import todo.backend.service.CardService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo/cards")
public class CardController {

	private final CardService cardService;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<CardResponse> getCards() {
		return cardService.getCards()
			.stream()
			.map(CardResponse::create)
			.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CardResponse getCard(@PathVariable Long id) {
		Card card = cardService.getCard(id);
		return CardResponse.create(card);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public CardResponse createCard(@RequestBody @Valid CreateCardRequest body) {
		Card card = Card.builder()
			.title(body.getTitle())
			.assignee(body.getAssignee())
			.priority(body.getPriority())
			.build();

		Card savedCard = cardService.createCard(card);

		return CardResponse.create(savedCard);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CardResponse updateCard(@PathVariable Long id, @RequestBody UpdateCardRequest body) {
		Card card = Card.builder()
			.status(body.getStatus())
			.build();

		Card updatedCard = cardService.updateCard(id, card);

		return CardResponse.create(updatedCard);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCard(@PathVariable Long id) {
		cardService.deleteCard(id);
	}
}
