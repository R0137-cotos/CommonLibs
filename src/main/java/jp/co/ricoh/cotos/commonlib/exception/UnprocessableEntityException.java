package jp.co.ricoh.cotos.commonlib.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnprocessableEntityException extends ResponseStatusException {

    public UnprocessableEntityException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
