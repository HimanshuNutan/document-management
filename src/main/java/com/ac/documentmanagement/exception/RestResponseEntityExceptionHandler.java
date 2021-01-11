package com.ac.documentmanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

  @ExceptionHandler(DocDataNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorResponse handleDocDataNotFoundException(DocDataNotFoundException e, final HttpServletRequest request) {

    return
            new ErrorResponse.ErrorResponseBuilder()
                    .message(ErrorMessage.DOC_DATA_NOT_FOUND.getMessage())
                    .errorStatus(ErrorStatus.RESOURCE_NOT_FOUND)
                    .requestedURI(request.getRequestURI())
                    .build();
  }

  @ExceptionHandler(MetaDataNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorResponse handleDocDataNotFoundException(MetaDataNotFoundException e, final HttpServletRequest request) {

    return
            new ErrorResponse.ErrorResponseBuilder()
                    .message(ErrorMessage.DOC_DATA_NOT_FOUND.getMessage())
                    .errorStatus(ErrorStatus.RESOURCE_NOT_FOUND)
                    .requestedURI(request.getRequestURI())
                    .build();
  }

  @ExceptionHandler(ResourceAccessException.class)
  @ResponseStatus(HttpStatus.BAD_GATEWAY)
  public @ResponseBody
  ErrorResponse handleResourceNotFoundException(final ResourceAccessException exception,
                                                final HttpServletRequest request) {

    return new ErrorResponse.ErrorResponseBuilder()
            .message("Problem connecting to other systems")
            .errorStatus(ErrorStatus.REMOTE_NOT_FOUND)
            .requestedURI(request.getRequestURI())
            .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody
  ErrorResponse handleException(final Exception exception,
                                final HttpServletRequest request) {

    return new ErrorResponse.ErrorResponseBuilder()
            .message(exception.getMessage())
            .errorStatus(ErrorStatus.UNKNOWN_ERROR)
            .requestedURI(request.getRequestURI())
            .build();
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody
  ErrorResponse handleAccessDeniedException(final Exception exception,
                                            final HttpServletRequest request) {

    return new ErrorResponse.ErrorResponseBuilder()
            .message(exception.getMessage())
            .errorStatus(ErrorStatus.FORBIDDEN)
            .requestedURI(request.getRequestURI())
            .build();
  }

}
