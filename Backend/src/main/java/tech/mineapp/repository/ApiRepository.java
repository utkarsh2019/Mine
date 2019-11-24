package tech.mineapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.mineapp.entity.ApiEntity;
import tech.mineapp.entity.UserEntity;

/**
 * @author utkarsh
 *
 */
@Repository
public interface ApiRepository  extends JpaRepository<ApiEntity, String> {

	ApiEntity findByUser(UserEntity user);
	
    void deleteByUser(UserEntity user);
}
