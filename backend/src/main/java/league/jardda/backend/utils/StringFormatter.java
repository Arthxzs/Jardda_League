package league.jardda.backend.utils;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringFormatter {
    public static String toCapitalize(String base) {
        return Arrays.stream(base
                        .toLowerCase()
                        .split(" "))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(" "));
    }
}
