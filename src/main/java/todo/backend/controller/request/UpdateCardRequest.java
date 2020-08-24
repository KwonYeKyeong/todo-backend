package todo.backend.controller.request;

import lombok.Getter;
import lombok.Setter;
import todo.backend.entity.CardStatus;

@Getter
@Setter
public class UpdateCardRequest {

	private CardStatus status;
}
