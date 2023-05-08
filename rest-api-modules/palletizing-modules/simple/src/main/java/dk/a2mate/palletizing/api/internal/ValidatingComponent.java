package dk.a2mate.palletizing.api.internal;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class ValidatingComponent {
    public int validateNotNull(@NotNull String data) {
        return data.length();
    }
}