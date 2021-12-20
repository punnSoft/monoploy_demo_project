package de.punn.monopoly.event.incoming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Schema(example = "{ \"rounds\" : 20, \"playerNames\" : [\"horse\", \"car\"] }", description = "Game configuration")
public class GameConfig {

    @Min(value = 1, message = "Rounds should not be less than 1!")
    @Max(value = 200, message = "Rounds should not be greater than 200!")
    private int rounds;

    @NotNull(message = "Player names should not be null!")
    @Size(min = 1, max = 8, message = "Player names should be between 1 and 8!")
    @Schema(allowableValues = {"dog", "iron", "battleship", "cannon", "magicHat", "horse", "shoe", "car"})
    private List<String> playerNames;
}
