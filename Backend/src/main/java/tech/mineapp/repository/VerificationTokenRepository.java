package tech.mineapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.entity.VerificationTokenEntity;

/**
 * @author utkarsh
 *
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
 
    VerificationTokenEntity findByToken(String token);
 
    VerificationTokenEntity findByUser(UserEntity user);
}