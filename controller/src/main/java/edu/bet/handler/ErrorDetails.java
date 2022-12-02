package edu.bet.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorDetails {

    private Date timestamp;

    private String message;

    private String details;

    private HttpStatus httpStatus;

    private int code;
}
