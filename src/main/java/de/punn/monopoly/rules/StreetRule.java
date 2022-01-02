package de.punn.monopoly.rules;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.street.Street;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Rule(name = "street rule", description = "If our position at a street square we can buy it or we have to pay a rent.")
public class StreetRule {

    private final List<Street> streets;

    public StreetRule(Street... street) {
        this.streets = List.of(street);
    }


    @Condition
    public boolean isSquareRelevant(@Fact("player") Player player) {

        return this.streets.stream()
                .anyMatch(street -> street.getSquarePosition() == player.getSquarePosition());
    }

    @Action
    public void buyOrPayRentForStreet(@Fact("player") Player player) {

        this.streets.stream()
                .filter(street -> street.getSquarePosition() == player.getSquarePosition())
                .findFirst()
                .ifPresentOrElse(street -> buyOrPayRentForStreet(player, street),
                        () -> log.warn("No street for given square position {} found!", player.getSquarePosition()));
    }

    private void buyOrPayRentForStreet(Player player, Street street) {

        if (street.isAvailable()) {

            player.setBalance(player.getBalance().subtract(street.getPurchasePrice()));
            street.setOwner(player);
            player.getPropertyList().add(street);
            log.info("street rule has fired! Player bought {}. Player {}'s balance is {} now.",
                    street.getName(), player.getName(), player.getBalance());

        } else if (street.getOwner() != player) {

            var rentToPay = street.getRent();
            player.setBalance(player.getBalance().subtract(rentToPay));

            var streetOwner = street.getOwner();
            streetOwner.setBalance(streetOwner.getBalance().add(rentToPay));
            log.info("street rule has fired! Player {} paid the rent of {} to street owner {}. Player {}'s balance is {} now.",
                    player.getName(), rentToPay, streetOwner.getName(), player.getName(), player.getBalance());
        }
    }
}
