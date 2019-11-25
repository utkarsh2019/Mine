package tech.mineapp.search;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * SearchSnippet representing previous searches
 */
@Data
@AllArgsConstructor
public class SearchSnippet {
    private String query;
}
