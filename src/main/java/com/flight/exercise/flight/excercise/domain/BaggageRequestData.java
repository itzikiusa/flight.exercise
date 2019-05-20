package com.flight.exercise.flight.excercise.domain;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BaggageRequestData {

    private Integer destinationId;
    private String baggageId;

}
