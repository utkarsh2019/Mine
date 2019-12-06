package tech.mineapp.model.response;

import lombok.Data;

import java.util.List;

/**
 * Represent previous searches by the user,
 * divided into categories
 *
 * @author amolmoses
 */
@Data
public class SearchStatisticsResponseModel implements ResponseModel {
    private List<String> video;
    private List<String> tvseries;
    private List<String> movie;
    private List<String> written;
    private List<String> event;
    private List<String> audio;
}
