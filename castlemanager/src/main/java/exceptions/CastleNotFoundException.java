package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CastleNotFoundException extends RuntimeException{

	public CastleNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
