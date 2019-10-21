package tech.mineapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.mineapp.entity.UserEntity;

import java.util.Optional;

import javax.transaction.Transactional;

/**
 * Repository used for interacting with persistent storage
 * for UserEntities
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity> findUserByEmail(String email);
	Optional<UserEntity> findUserByUserId(Long userId);
    
    Boolean existsByEmail(String email);
    Boolean existsByUserId(Long id);

    @Transactional
    void deleteByUserId(Long userId);
}
