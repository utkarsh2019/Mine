package tech.mineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mineapp.constants.Category;
import tech.mineapp.entity.SearchEntity;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.repository.SearchRepository;
import tech.mineapp.repository.UserRepository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchStatisticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SearchRepository searchRepository;

    public List<String> getPreviousSearchesForUserAndCategory(Long userId, Category category, int numOfSearches)
            throws SQLException {

        Optional<UserEntity> userByUserId = userRepository.findUserByUserId(userId);
        if (userByUserId.isEmpty()) {
            throw new SQLException("No user found with the specified userId");
        }

        return searchRepository
                .findSearchEntitiesByUserAndCategoryOrderByLastModifiedDesc(userByUserId.get(), category)
                .map(searchEntities -> searchEntities.stream()
                                .limit(numOfSearches)
                                .map(SearchEntity::getQuery)
                                .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);
    }

    public List<String> getFrequentSearchesForUserAndCategory(Long userId, Category category, int numOfSearches)
        throws SQLException {

        Optional<UserEntity> userByUserId = userRepository.findUserByUserId(userId);
        if (userByUserId.isEmpty()) {
            throw new SQLException("No user found with the specified userId");
        }

        return searchRepository
                .findSearchEntitiesByUserAndCategoryOrderByNumOfSearchesDesc(userByUserId.get(), category)
                .map(searchEntities -> searchEntities.stream()
                        .limit(numOfSearches)
                        .map(SearchEntity::getQuery)
                        .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);

    }
}
