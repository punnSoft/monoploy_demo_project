package de.punn.monopoly.event.outgoing;

import de.punn.monopoly.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp () {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    void shouldBeValid () {

        GameResult gameResult = GameResult.builder()
                .winner(Player.PlayerName.car.name())
                .playedRounds(20)
                .balance(BigDecimal.TEN)
                .build();

        Set<ConstraintViolation<GameResult>> violations = this.validator.validate(gameResult);

        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    void shouldNotBeValidBecauseWinnerIsEmpty () {

        GameResult gameResult = GameResult.builder()
                .balance(BigDecimal.TEN)
                .playedRounds(10)
                .build();

        Set<ConstraintViolation<GameResult>> violations = this.validator.validate(gameResult);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
        violations.forEach(validator -> assertThat(validator.getMessage()).isEqualTo("Winner should not be blank!"));
    }

    @Test
    void shouldNotBeValidBecauseBalanceIsNull () {

        GameResult gameResult = GameResult.builder()
                .winner(Player.PlayerName.car.name())
                .playedRounds(10)
                .build();

        Set<ConstraintViolation<GameResult>> violations = this.validator.validate(gameResult);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
        violations.forEach(validator -> assertThat(validator.getMessage()).isEqualTo("Balance should not be null!"));
    }

    @Test
    void shouldNotBeValidBecausePlayedRoundsIsLessThanMinValue () {

        GameResult gameResult = GameResult.builder()
                .winner(Player.PlayerName.car.name())
                .playedRounds(0)
                .balance(BigDecimal.TEN)
                .build();

        Set<ConstraintViolation<GameResult>> violations = this.validator.validate(gameResult);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
        violations.forEach(validator -> assertThat(validator.getMessage()).isEqualTo("Played rounds should not be less than 1!"));
    }

    @Test
    void shouldNotBeValidBecausePlayedRoundsIsGreaterThanMaxValue () {

        GameResult gameResult = GameResult.builder()
                .winner(Player.PlayerName.car.name())
                .playedRounds(500)
                .balance(BigDecimal.TEN)
                .build();

        Set<ConstraintViolation<GameResult>> violations = this.validator.validate(gameResult);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
        violations.forEach(validator -> assertThat(validator.getMessage()).isEqualTo("Played rounds should not be greater than 200!"));
    }

}