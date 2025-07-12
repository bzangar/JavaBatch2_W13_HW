package kz.flowers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //для того чтобы когда не нашлось flower выводилась 404
public class FlowerNotFoundException extends RuntimeException {
    public FlowerNotFoundException(String message) {
        super(message);
    }
}
