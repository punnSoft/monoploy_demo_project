package de.punn.monopoly.config;

import de.punn.monopoly.rules.GoSquareRule;
import de.punn.monopoly.rules.IncomeTaxRule;
import de.punn.monopoly.rules.LuxuryTaxRule;
import org.jeasy.rules.api.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RulesConfig {

    @Autowired
    private GoSquareRule goSquareRule;

    @Autowired
    private IncomeTaxRule incomeTaxRule;

    @Autowired
    private LuxuryTaxRule luxuryTaxRule;

    @Bean
    public Rules rules() {

        Rules rules = new Rules();
        rules.register(this.goSquareRule);
        rules.register(this.incomeTaxRule);
        rules.register(this.luxuryTaxRule);

        return rules;
    }
}
