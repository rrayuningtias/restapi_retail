package com.learn.restapi.exception;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
//@XmlRootElement
public class ErrorResponse {
    private String message;
    private List<String> details;
    /**
     * This is a Javadoc comment
     * @param message, details
     */
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
}
