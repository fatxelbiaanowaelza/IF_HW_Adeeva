package cucumber;

import io.cucumber.java.ParameterType;

public class MaskedString {
    private final String value;

    public MaskedString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "*****";
    }

    @ParameterType(".*")
    public static MaskedString password(String value) {
        return new MaskedString(value);
    }
}