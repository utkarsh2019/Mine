package tech.mineapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.mineapp.entity.UserEntity;

/**
 * Repository used for interacting with persisten storage
 * for UserEntities
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    UserEntity findUserByEmailId(String email);
    UserEntity findUserByUserId(String userId);
}
