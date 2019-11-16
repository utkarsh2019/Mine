package tech.mineapp.search.TVMaze.objects;

import org.junit.Test;

import tech.mineapp.search.tvmaze.objects.Show;

import static org.junit.Assert.assertEquals;

public class ShowTests {

    @Test
    public void testShowsAreCreatedCorrectly() {
        String test_name = "test_name";
        String test_summary = "test_summary";
        String test_site = "test_site";

        Show show = new Show();

        show.setName(test_name);
        show.setSummary(test_summary);
        show.setOfficialSite(test_site);

        assertEquals(show.getName(), test_name);
        assertEquals(show.getOfficialSite(), test_site);
        assertEquals(show.getSummary(), test_summary);
    }
}
