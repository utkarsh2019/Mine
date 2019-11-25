package tech.mineapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.mineapp.constants.Category;
import tech.mineapp.entity.SearchEntity;
import tech.mineapp.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * Repository for interacting with persistent storage
 * for SearchEntities
 *
 */
public interface SearchRepository extends JpaRepository<SearchEntity, Long> {
    Optional<List<SearchEntity>> findSearchEntitiesByUserAndCategory(UserEntity user, Category category);
}
