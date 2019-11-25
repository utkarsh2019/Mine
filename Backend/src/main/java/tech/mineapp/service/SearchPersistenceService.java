package tech.mineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import tech.mineapp.constants.Category;
import tech.mineapp.entity.SearchEntity;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.repository.SearchRepository;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.search.SearchSnippet;
import tech.mineapp.util.RandomLongGeneratorUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SearchRepository searchRepository;

    public void persistSearchDetails(Long userId, Category category, String query) throws SQLException {
        Optional<UserEntity> userByUserId = userRepository.findUserByUserId(userId);
        if (userByUserId.isEmpty()) {
            throw new SQLException("No user found with the specified userId");
        }

        SearchEntity searchEntity = new SearchEntity();

        searchEntity.setSearchId(RandomLongGeneratorUtil.generateRandomLong());
        searchEntity.setCategory(category);
        searchEntity.setQuery(query);
        searchEntity.setUser(userByUserId.get());

        searchRepository.save(searchEntity);
    }

}
