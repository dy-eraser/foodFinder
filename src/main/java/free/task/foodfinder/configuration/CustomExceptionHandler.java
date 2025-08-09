package free.task.foodfinder.configuration;

import free.task.foodfinder.exception.BaseException;
import free.task.foodfinder.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public final ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException ex) {
		logger.error("an exception is thrown!", ex);
		return ResponseEntity.badRequest()
				.body(BaseResponse.notSuccess(ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<BaseResponse> handleGeneralException(Exception ex) {
		logger.error("general exception", ex);
		return ResponseEntity.unprocessableEntity()
				.body(BaseResponse.notSuccess(ex.getMessage()));
	}

}
