package jp.co.ricoh.cotos.commonlib.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnprocessableeEntityException extends ResponseStatusException {

    public UnprocessableeEntityException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
