package de.punn.monopoly.port;

import de.punn.monopoly.event.incoming.GameConfig;
import de.punn.monopoly.event.outgoing.GameResult;
import de.punn.monopoly.service.GameManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@AllArgsConstructor
@Validated
public class GamePort {

    private final GameManager gameManager;

    @Operation(summary = "Start a monopoly game with the given configuration.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Simulation was successful.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GameResult.class))}),
            @ApiResponse(responseCode = "400", description = "Validation went wrong. Please check your input data!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Something went wrong", content = @Content)})
    @RequestMapping(value = "/start-monopoly-game", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<GameResult> startGame(@Parameter(schema =
    @Schema(implementation = GameConfig.class)) @RequestBody @NotNull @Valid GameConfig gameConfig) {

        log.info("Get a trigger for a new game!");
        try {

            GameResult gameResult = this.gameManager.startGame(gameConfig);
            return new ResponseEntity<>(gameResult, HttpStatus.OK);

        } catch (Exception exception) {
            log.error("Something went wrong!", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
