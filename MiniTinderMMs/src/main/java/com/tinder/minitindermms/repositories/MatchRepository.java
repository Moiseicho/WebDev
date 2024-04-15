package com.tinder.minitindermms.repositories;

import com.tinder.minitindermms.entities.MatchEntity;
import com.tinder.minitindermms.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    List<MatchEntity> findByUserId2AndPending(Long userId2, boolean pending);
    List<MatchEntity> findByUserId1AndPending(Long userId1, boolean pending);
    void deleteByUserId1AndUserId2(Long userId1, Long userId2);

}
