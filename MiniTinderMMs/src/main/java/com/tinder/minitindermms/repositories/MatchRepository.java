package com.tinder.minitindermms.repositories;

import com.tinder.minitindermms.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    List<Long> findUserId1ByUserId2AndPending(Long userId2, boolean pending);
    List<Long> findUserId2ByUserId1AndPending(Long userId1, boolean pending);

}
