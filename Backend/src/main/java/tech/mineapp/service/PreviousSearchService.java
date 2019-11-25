package tech.mineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mineapp.constants.Category;
import tech.mineapp.entity.SearchEntity;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.repository.SearchRepository;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.search.SearchSnippet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PreviousSearchService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SearchRepository searchRepository;

    public List<SearchSnippet> getPreviousSearchesForUserAndCategory(Long userId, Category category, int numOfSearches)
            throws SQLException {
        Optional<UserEntity> userByUserId = userRepository.findUserByUserId(userId);
        if (userByUserId.isEmpty()) {
            throw new SQLException("No user found with the specified userId");
        }

        List<SearchEntity> previousSearches = searchRepository
                .findSearchEntitiesByUserAndCategory(userByUserId.get(), category)
                .get();

        return previousSearches.stream()
                .limit(numOfSearches)
                .map(searchEntity -> new SearchSnippet(searchEntity.getQuery()))
                .collect(Collectors.toList());
    }
}
