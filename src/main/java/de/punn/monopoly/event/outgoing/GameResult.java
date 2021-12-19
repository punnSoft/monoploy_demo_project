package de.punn.monopoly.event.outgoing;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class GameResult {

    @NotBlank(message = "Winner should not be blank!")
    private String winner;

    @NotNull(message = "Balance should not be null!")
    private BigDecimal balance;

    @Min(value = 1, message = "Played rounds should not be less than 1!")
    @Max(value = 200, message = "Played rounds should not be greater than 200!")
    private int playedRounds;
}
