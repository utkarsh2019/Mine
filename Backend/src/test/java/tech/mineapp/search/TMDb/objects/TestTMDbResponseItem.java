package tech.mineapp.search.TMDb.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTMDbResponseItem {

    @Test
    public void TestTMDbResponseItemAreCreatedCorrectly() {
        String original_title = "original_title";
        String overview = "overview";
        String poster_path = "poster_path";

        TMDbResponseItem responseItem = new TMDbResponseItem();

        responseItem.setOriginal_title(original_title);
        responseItem.setOverview(overview);
        responseItem.setPoster_path(poster_path);

        assertEquals(responseItem.getOriginal_title(), original_title);
        assertEquals(responseItem.getOverview(), overview);
        assertEquals(responseItem.getPoster_path(), poster_path);
    }
}
