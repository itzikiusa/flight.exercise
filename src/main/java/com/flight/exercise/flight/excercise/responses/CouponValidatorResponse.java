package com.flight.exercise.flight.excercise.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CouponValidatorResponse {

    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("error_message")
    private String errMsg;

}
