package br.edu.ifce.springbootproject.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date date, String format, String description) {
    }
}