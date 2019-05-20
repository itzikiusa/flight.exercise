package com.flight.exercise.flight.excercise.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HttpExceptionResponse {

    @JsonProperty("error_message")
    private String errMsg;

}
