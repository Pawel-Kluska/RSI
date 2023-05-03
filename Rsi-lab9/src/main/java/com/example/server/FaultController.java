package com.example.server;

import com.example.server.exceptions.BadRequestEx;
import com.example.server.exceptions.PersonNotFoundEx;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FaultController {

    @ResponseBody
    @ExceptionHandler(PersonNotFoundEx.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    ResponseEntity<?> PNFEHandler(PersonNotFoundEx e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create().withStatus(HttpStatus.NOT_FOUND)
                        .withTitle(HttpStatus.NOT_FOUND.name())
                        .withDetail("The person of ID=" + e.getMessage() + " DOES NOT EXIST"));

    }

    @ResponseBody
    @ExceptionHandler(BadRequestEx.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    ResponseEntity<?> BREHandler(BadRequestEx e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create().withStatus(HttpStatus.BAD_REQUEST)
                        .withTitle(HttpStatus.BAD_REQUEST.name())
                        .withDetail("The person of ID=" + e.getMessage() + " ALREADY EXISTS"));
    }
}
