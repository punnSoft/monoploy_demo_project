package de.punn.monopoly.event.outgoing;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@Schema(example = "{ \"winner\": \"car\", \"balance\": 7200, \"playedRounds\": 20 }")
@NoArgsConstructor
@AllArgsConstructor
public class GameResult {

    @NotBlank(message = "Winner should not be blank!")
    @Pattern(regexp = "dog|iron|battleship|cannon|magicHat|horse|shoe|car", message = "The name of the winner is not valid!")
    private String winner;

    @NotNull(message = "Balance should not be null!")
    private BigDecimal balance;

    @Min(value = 1, message = "Played rounds should not be less than 1!")
    @Max(value = 200, message = "Played rounds should not be greater than 200!")
    private int playedRounds;
}
