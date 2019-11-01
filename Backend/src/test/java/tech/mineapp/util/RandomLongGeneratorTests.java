package tech.mineapp.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class RandomLongGeneratorTests {

    @Test
    public void checkGenerateIdForUserGeneratesRandomIds() {
        Long firstId = RandomLongGeneratorUtil.generateRandomLong();
        Long secondId = RandomLongGeneratorUtil.generateRandomLong();

        assertThat(
                "Both the Ids should be different",
                firstId,
                not(equalTo(secondId))
        );
    }

    @Test
    public void checkGenerateIdForUserGeneratesIdsOfSameLength() {
    	Long firstId = RandomLongGeneratorUtil.generateRandomLong();
        Long secondId = RandomLongGeneratorUtil.generateRandomLong();

        assertThat(
                "Both the Ids should have the same length",
                ("" + firstId).length(),
                equalTo(("" + secondId).length())
        );
    }
}
