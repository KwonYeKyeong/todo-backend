package todo.backend.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import todo.backend.controller.request.CreateCardRequest;
import todo.backend.controller.response.CardResponse;
import todo.backend.entity.Card;
import todo.backend.entity.CardStatus;
import todo.backend.service.CardService;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@WebMvcTest(CardController.class)
class CardControllerTest {

	final MockMvc mockMvc;
	final ObjectMapper objectMapper;

	@MockBean
	CardService cardService;

	@Test
	void getCards() throws Exception {

		// given
		Card c1 = Card.builder()
			.id(1L)
			.title("title1")
			.priority(1)
			.assignee("a1")
			.status(CardStatus.TODO)
			.created(LocalDate.now())
			.build();
		Card c2 = Card.builder()
			.id(2L)
			.title("title2")
			.priority(2)
			.assignee("a2")
			.status(CardStatus.DONE)
			.created(LocalDate.now())
			.build();
		List<Card> expected = Arrays.asList(c1, c2);

		given(cardService.getCards()).willReturn(expected);

		// when
		ResultActions result = mockMvc.perform(get("/todo/cards"));

		//then
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.*", not(empty())))
			.andExpect(jsonPath("$.*", hasSize(2)))
			.andExpect(jsonPath("$.[0].id", is(1)))
			.andExpect(jsonPath("$.[1].id", is(2)))
			.andDo(print());
	}

	@Test
	void createCard() throws Exception {

		// given
		CreateCardRequest req = new CreateCardRequest();
		req.setTitle("title1");
		req.setAssignee("a1");
		req.setPriority(1);

		final String body = objectMapper.writeValueAsString(req);

		Card card = Card.builder()
			.title(req.getTitle())
			.assignee(req.getAssignee())
			.priority(req.getPriority())
			.build();

		String expected = objectMapper.writeValueAsString(CardResponse.create(card));

		given(cardService.createCard(any())).willReturn(card);

		// when
		ResultActions action = mockMvc.perform(
			post("/todo/cards")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// then
		action.andExpect(status().isCreated())
			.andExpect(content().string(expected))
			.andDo(print());
	}

	@DisplayName("생성할 카드의 title은 빈 값이되면 안된다.")
	@Test
	void createCard_titleIsEmpty() throws Exception {

		// given
		CreateCardRequest req = new CreateCardRequest();
		req.setTitle("");
		req.setAssignee("a1");
		req.setPriority(1);

		final String body = objectMapper.writeValueAsString(req);

		// when
		ResultActions action = mockMvc.perform(
			post("/todo/cards")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// then
		action.andExpect(status().is4xxClientError())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
			.andDo(print());
	}

}
