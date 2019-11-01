package tech.mineapp.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

public class SearchQueryParserTests {

    @Test
    public void checkInitialization() {
        SearchQueryParser searchQueryParser = new SearchQueryParser();
        assertNotNull(searchQueryParser);
    }

    @Test
    public void checkSpaceDeLimitedParsingWorksCorrectly() {
        String testString = "A simple space delimited string";
        String expectedString = "A+simple+space+delimited+string";
        SearchQueryParser searchQueryParser = new SearchQueryParser();

        String result = searchQueryParser.parseSpaceDeLimitedQueries(testString);

        assertThat(
                "Input String should be successfully parsed",
                result,
                is(equalTo(expectedString))
        );
    }
}
