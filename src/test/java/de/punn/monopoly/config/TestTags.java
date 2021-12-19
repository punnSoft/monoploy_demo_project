package de.punn.monopoly.config;


import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@UtilityClass
public class TestTags {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Tag("integration")
    public @interface IntegrationTest {

    }
}
