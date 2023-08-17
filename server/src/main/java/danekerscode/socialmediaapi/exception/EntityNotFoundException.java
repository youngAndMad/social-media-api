package danekerscode.socialmediaapi.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Class<?> c) {
        super(c.getSimpleName() + " not found");
    }
}
