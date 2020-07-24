package todo.backend.exception;

public class NotFoundException extends RuntimeException {

    private final Object id;

    public NotFoundException(Object id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("card[id:%d] does not exists.", id);
    }
}