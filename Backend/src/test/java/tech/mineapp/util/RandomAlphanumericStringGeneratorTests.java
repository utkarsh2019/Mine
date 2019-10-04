package tech.mineapp.util;

import org.junit.Test;
import tech.mineapp.constants.Constants.ApplicationConstants;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class RandomAlphanumericStringGeneratorTests {

    @Test
    public void checkGenerateIdForUserGeneratesRandomIds() {
        String firstId = RandomAlphanumericStringGenerator.generateAlphanumericString(
                ApplicationConstants.userIdLength);
        String secondId = RandomAlphanumericStringGenerator.generateAlphanumericString(
                ApplicationConstants.userIdLength
        );

        assertThat(
                "Both the Ids should be different",
                firstId,
                not(equalTo(secondId))
        );
    }

    @Test
    public void checkGenerateIdForUserGeneratesIdsOfSameLength() {
        String firstId = RandomAlphanumericStringGenerator.generateAlphanumericString(
                ApplicationConstants.userIdLength);
        String secondId = RandomAlphanumericStringGenerator.generateAlphanumericString(
                ApplicationConstants.userIdLength
        );

        assertThat(
                "Both the Ids should have the same length",
                firstId.length(),
                (equalTo(secondId.length()))
        );
    }
}
