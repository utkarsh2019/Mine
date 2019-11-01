package tech.mineapp.search.TVMaze.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageTests {

    @Test
    public void testImagesAreCreatedCorrectly() {
        String medium_image = "medium_image";
        String original_image = "original_image";

        Image image = new Image();

        image.setMedium(medium_image);
        image.setOriginal(original_image);

        assertEquals(image.getMedium(), medium_image);
        assertEquals(image.getOriginal(), original_image);
    }
}
