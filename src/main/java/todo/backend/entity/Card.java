package todo.backend.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Card {

    private Long id;

    @Size(max = 20)
    @NotBlank
    private String title;

    private CardStatus status;

    // TODO: add created, updated
    // TODO: add order

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }
}
