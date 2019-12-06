package tech.mineapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.mineapp.entity.ForgotPasswordEntity;
import tech.mineapp.entity.UserEntity;

/**
 * @author utkarsh
 *
 */
@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordEntity, Long> {
 
    ForgotPasswordEntity findByToken(String token); 
    ForgotPasswordEntity findByUser(UserEntity user);
    
    Boolean existsByToken(String token);
    
    void deleteByUser(UserEntity user);
    void deleteByToken(String token);
}