package com.comeon.gamelove.model.requestAndResponseForApi;

import lombok.Data;

@Data
public class GenericResponse {
    private boolean success = true;
    private String message;
}
