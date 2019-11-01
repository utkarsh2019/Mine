package tech.mineapp.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.util.Arrays.stream;

/**
 * Parses input searche query
 *
 * @author amolmoses
 */
@Component
public class SearchQueryParser {
    public String parseSpaceDeLimitedQueries(String query) {
        String delimitedString =  Arrays.stream(query.split(" "))
                .reduce("", (accumulator, nextStringElement) -> accumulator + nextStringElement+"+");

        return delimitedString.substring(0, delimitedString.length() - 1);
    }
}
