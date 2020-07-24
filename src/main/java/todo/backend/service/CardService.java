package todo.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import todo.backend.entity.Card;
import todo.backend.repository.CardRepository;

@Service
public class CardService {

	private final CardRepository cardRepository;

	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public List<Card> getCards() {
		return cardRepository.findAll();
	}

	public Card createCard(Card card) {
		return cardRepository.save(card);
	}

	public Card getCard(Long id) {
		//validateCardOrThrow(id);
		return cardRepository.findId(id);
	}

	public Card updateCard(Long id, Card card) {
		return cardRepository.update(id, card);
	}

	public void deleteCard(Long id){
		cardRepository.delete(id);
	}

//	private void validateCardOrThrow(Long id){
//		if(!cardRepository.doesCardExist(id)){
//			throw new ExceptionController(
//					String.format("Card[id:%d] does not exits.",id )
//			);
//		}
//	}
}
