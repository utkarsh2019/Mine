package tech.mineapp.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import tech.mineapp.search.SearchItem;
import tech.mineapp.search.TVMaze.TVMazeController;
import tech.mineapp.search.TVMaze.TVMazeResponseModel;
import tech.mineapp.search.TVMaze.objects.Image;
import tech.mineapp.search.TVMaze.objects.Show;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TVSearchServiceTests {

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    TVSearchService tvSearchService;

    @Mock
    TVMazeController tvMazeController;

    @Test
    public void testControllerIsCalledWithCorrectArguments() {
        String testQuery = "test";
        int testNumOfQuery = 3;

        TVMazeResponseModel responseModel = new TVMazeResponseModel();
        Show show = new Show();
        Image image = new Image();

        show.setName("test_name");
        show.setOfficialSite("test_official_site");
        show.setSummary("test_summary");

        image.setMedium("medium_test_image");
        show.setImage(image);

        responseModel.setShow(show);

        TVMazeResponseModel[] testResponses = { responseModel };

        when(tvMazeController.TVMazeSearch(eq(testQuery), eq(testNumOfQuery))).thenReturn(testResponses);

        tvSearchService.searchTVMaze("test", 3);

        verify(tvMazeController, times(1)).TVMazeSearch(eq("test"), eq(3));
    }

    @Test
    public void testServiceMapsResponsesCorrectly() {
        String testQuery = "test";
        String test_name = "test_name";
        String test_official_site = "test_official_site";
        String test_summary = "test_summary";
        String medium_test_image = "medium_test_image";

        int testNumOfQuery = 3;

        TVMazeResponseModel responseModel = new TVMazeResponseModel();
        Show show = new Show();
        Image image = new Image();


        show.setName(test_name);
        show.setOfficialSite(test_official_site);
        show.setSummary(test_summary);

        image.setMedium(medium_test_image);
        show.setImage(image);

        responseModel.setShow(show);

        TVMazeResponseModel[] testResponses = { responseModel };

        SearchItem searchItem = new SearchItem(
                test_name,
                test_summary,
                test_official_site,
                medium_test_image
        );

        when(tvMazeController.TVMazeSearch(eq(testQuery), eq(testNumOfQuery))).thenReturn(testResponses);

        List<SearchItem> searchResult = tvSearchService.searchTVMaze("test", 3);

        assertNotNull(searchResult);

        assertThat(
                "There should only be one result in the resulting list",
                searchResult.size(),
                is(equalTo(1))
        );

        assertThat(
                "The resulting name should be set correctly",
                searchResult.get(0).getName(),
                is(equalTo(test_name))
        );

        assertThat(
                "The resulting summary should be set correctly",
                searchResult.get(0).getDescription(),
                is(equalTo(test_summary))
        );

        assertThat(
                "The resulting image url should be set correctly",
                searchResult.get(0).getImageUrl(),
                is(equalTo(medium_test_image))
        );

        assertThat(
                "The resulting url should be set correctly",
                searchResult.get(0).getUrl(),
                is(equalTo(test_official_site))
        );
    }
}
