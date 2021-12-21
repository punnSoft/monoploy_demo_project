package de.punn.monopoly;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.punn.monopoly.config.TestTags;
import de.punn.monopoly.event.outgoing.GameResult;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@TestTags.FunctionalTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MonopolyJourney {

    private HttpClient httpClient;

    @BeforeEach
    void setUp() {

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    @Test
    @SneakyThrows
    void shouldStartASimulationOfGameWithHorseAndCarOverTwentyRounds() {
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("http://localhost:8080/start-monopoly-game"))
                .POST(HttpRequest.BodyPublishers.ofString("{ \"rounds\" : 20, \"playerNames\" : [\"horse\", \"car\"] }"))
                .header("Content-type", "application/json")
                .build();

        HttpResponse<String> response = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        assertThat(HttpStatus.valueOf(response.statusCode())).isEqualTo(HttpStatus.OK);
        GameResult gameResult = new ObjectMapper().createParser(response.body()).readValueAs(GameResult.class);

        assertThat(gameResult).isNotNull();
        assertThat(gameResult.getWinner()).isNotBlank();
        assertThat(gameResult.getWinner().matches("horse|car")).isTrue();
        assertThat(gameResult.getPlayedRounds()).isEqualTo(20);
        assertThat(gameResult.getBalance()).isNotNull();
    }
}
