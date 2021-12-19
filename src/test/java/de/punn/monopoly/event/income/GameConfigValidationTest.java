package de.punn.monopoly.event.income;

import de.punn.monopoly.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class GameConfigValidationTest {

    private  Validator validator;

    @BeforeEach
    void setUp () {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    void shouldBeValid () {

        var gameConfig = new GameConfig();
        gameConfig.setRounds(10);
        gameConfig.setPlayerNames(Arrays.stream(Player.PlayerName.values())
                .map(Enum::name)
                .collect(Collectors.toList()));

        Set<ConstraintViolation<GameConfig>> violations = this.validator.validate(gameConfig);

        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    void shouldNotBeValidBecauseRoundsIsLessThanMinValue () {

        var gameConfig = new GameConfig();
        gameConfig.setRounds(0);
        gameConfig.setPlayerNames(Arrays.stream(Player.PlayerName.values())
                .map(Enum::name)
                .collect(Collectors.toList()));

        Set<ConstraintViolation<GameConfig>> violations = this.validator.validate(gameConfig);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
        violations.forEach(validator -> assertThat(validator.getMessage()).isEqualTo("Rounds should not be less than 1!"));
    }

    @Test
    void shouldNotBeValidBecauseRoundsIsGreaterThanMaxValue () {

        var gameConfig = new GameConfig();
        gameConfig.setRounds(500);
        gameConfig.setPlayerNames(Arrays.stream(Player.PlayerName.values())
                .map(Enum::name)
                .collect(Collectors.toList()));

        Set<ConstraintViolation<GameConfig>> violations = this.validator.validate(gameConfig);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
        violations.forEach(validator -> assertThat(validator.getMessage()).isEqualTo("Rounds should not be greater than 200!"));
    }

    @Test
    void shouldNotBeValidBecausePlayerNamesIsEmpty () {

        var gameConfig = new GameConfig();
        gameConfig.setRounds(5);
        gameConfig.setPlayerNames(List.of());

        Set<ConstraintViolation<GameConfig>> violations = this.validator.validate(gameConfig);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
        violations.forEach(validator -> assertThat(validator.getMessage()).isEqualTo("Player names should be between 1 and 8!"));
    }

    @Test
    void shouldNotBeValidBecausePlayerNamesIsNull () {

        var gameConfig = new GameConfig();
        gameConfig.setRounds(5);

        Set<ConstraintViolation<GameConfig>> violations = this.validator.validate(gameConfig);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
        violations.forEach(validator -> assertThat(validator.getMessage()).isEqualTo("Player names should not be null!"));
    }

    @Test
    void shouldNotBeValidBecausePlayerNamesIsGreaterThanMax () {

        List<String> playerNames = Arrays.stream(Player.PlayerName.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        playerNames.add("some-other-player");

        var gameConfig = new GameConfig();
        gameConfig.setRounds(5);
        gameConfig.setPlayerNames(playerNames);

        Set<ConstraintViolation<GameConfig>> violations = this.validator.validate(gameConfig);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
        violations.forEach(validator -> assertThat(validator.getMessage()).isEqualTo("Player names should be between 1 and 8!"));
    }

}