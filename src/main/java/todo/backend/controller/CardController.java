package todo.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import todo.backend.entity.Card;
import todo.backend.service.CardService;

@RestController
@RequestMapping("/todo/cards")
public class CardController {

	private final CardService cardService;

	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<Card> getCards() {
		return cardService.getCards();
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Card createCard(@RequestBody Card card) {
		return cardService.createCard(card);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Card updateCard(@PathVariable Long id, @RequestBody Card card) {
		return cardService.updateCard(id, card);
	}

	// TODO: implement - delete card
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCard(@PathVariable Long id){
		cardService.deleteCard(id);
	}



}
