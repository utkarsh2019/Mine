package tech.mineapp.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import tech.mineapp.config.ApiPropertiesConfig;
import tech.mineapp.search.SearchItem;
import tech.mineapp.search.TMDb.TMDbController;
import tech.mineapp.search.TMDb.TMDbResponseModel;
import tech.mineapp.search.TMDb.objects.TMDbResponseItem;
import tech.mineapp.util.SearchQueryParser;
import tech.mineapp.config.ApiPropertiesConfig.TmdbApi;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieSearchTests {

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    MovieSearchService movieSearchService;

    @Mock
    TMDbController tmDbController;

    @Mock
    SearchQueryParser searchQueryParser;

    @Mock
    ApiPropertiesConfig propertiesConfig;

    @Mock
    TmdbApi tmdbApi;

    @Test
    public void testControllerIsCalledWithCorrectArguments() {
        String original_title = "original_title";
        String overview = "overview";
        String poster_path = "poster_path";
        String image_url = "image_url";

        String search_query = "query";
        int num_searches = 3;

        TMDbResponseModel responseModel = new TMDbResponseModel();
        TMDbResponseItem responseItem = new TMDbResponseItem();

        responseItem.setOriginal_title(original_title);
        responseItem.setOverview(overview);
        responseItem.setPoster_path(poster_path);

        responseModel.setResults(new TMDbResponseItem[] {responseItem});

        when(tmDbController.searchTMDb(eq(search_query))).thenReturn(responseModel);
        when(searchQueryParser.parseSpaceDeLimitedQueries(eq(search_query))).thenReturn(search_query);
        when(propertiesConfig.getTmdb()).thenReturn(tmdbApi);
        when(tmdbApi.getImageUrl()).thenReturn(image_url);

        movieSearchService.searchTMDb(search_query, num_searches);

        verify(tmDbController, times(1)).searchTMDb(eq(search_query));
    }

    @Test
    public void testServiceMapsResponsesCorrectly() {
        String original_title = "original_title";
        String overview = "overview";
        String poster_path = "poster_path";
        String image_url = "image_url";

        String search_query = "query";
        int num_searches = 3;

        TMDbResponseModel responseModel = new TMDbResponseModel();
        TMDbResponseItem responseItem = new TMDbResponseItem();

        responseItem.setOriginal_title(original_title);
        responseItem.setOverview(overview);
        responseItem.setPoster_path(poster_path);

        responseModel.setResults(new TMDbResponseItem[] {responseItem});

        when(tmDbController.searchTMDb(eq(search_query))).thenReturn(responseModel);
        when(searchQueryParser.parseSpaceDeLimitedQueries(eq(search_query))).thenReturn(search_query);
        when(propertiesConfig.getTmdb()).thenReturn(tmdbApi);
        when(tmdbApi.getImageUrl()).thenReturn(image_url);

        List<SearchItem> results = movieSearchService.searchTMDb(search_query, num_searches);

        assertNotNull(results);

        assertEquals(results.size(), 1);

        assertEquals(results.get(0).getName(), original_title);
        assertEquals(results.get(0).getDescription(), overview);
        assertEquals(results.get(0).getImageUrl(), image_url + poster_path);
        assertEquals(results.get(0).getUrl(), "https://www.google.com/search?q=" + search_query);
    }
}
