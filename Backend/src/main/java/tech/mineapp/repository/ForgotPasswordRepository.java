package tech.mineapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.mineapp.entity.ForgotPasswordEntity;
import tech.mineapp.entity.UserEntity;

/**
 * @author utkarsh
 *
 */
public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordEntity, Long> {
 
    ForgotPasswordEntity findByToken(String token);
 
    ForgotPasswordEntity findByUser(UserEntity user);
    
    void deleteByUser(UserEntity user);
}