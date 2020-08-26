package todo.backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import todo.backend.controller.request.CreateCardRequest;
import todo.backend.exception.NotFoundException;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@AutoConfigureMockMvc
@SpringBootTest
class CardControllerIntegrationTest {

	final MockMvc mockMvc;
	final ObjectMapper objectMapper;

	@Test
	void getCards() throws Exception {

		// when
		ResultActions action = mockMvc.perform(get("/todo/cards"));

		// then
		action.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@Sql("/cards.sql")
	void getCardById() throws Exception {

		// when
		ResultActions action = mockMvc.perform(get("/todo/cards/1"));

		// then
		action.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void getCardById_NotFound() throws Exception {

		// when
		ResultActions action = mockMvc.perform(get("/todo/cards/100"));

		// then
		action.andExpect(status().is4xxClientError())
			.andExpect(result -> assertTrue((result.getResolvedException() instanceof NotFoundException)))
			.andDo(print());

	}

	@Test
	void createCard() throws Exception {

		// given
		CreateCardRequest req = new CreateCardRequest();
		req.setTitle("title");
		req.setAssignee("assignee");
		req.setPriority(1);

		final String body = objectMapper.writeValueAsString(req);

		// when
		ResultActions action = mockMvc.perform(
			post("/todo/cards")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// then
		action.andExpect(status().isCreated())
			.andDo(print());
	}
}
