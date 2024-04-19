package com.tinder.minitindermms.repositories;

import com.tinder.minitindermms.entities.MatchEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends CassandraRepository<MatchEntity, Long> {
    @AllowFiltering
    List<MatchEntity> findByUserId2AndPending(@Param("userId2") Long userId2, @Param("pending") boolean pending);
    @AllowFiltering
    List<MatchEntity> findByUserId1AndPending(@Param("userId1") Long userId1, @Param("pending") boolean pending);
    @AllowFiltering
    @Query("DELETE FROM matchentity WHERE matchId = :matchId")
    void deleteByMatchId(@Param("matchId") UUID matchId);
}

/*
@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    List<MatchEntity> findByUserId2AndPending(Long userId2, boolean pending);
    List<MatchEntity> findByUserId1AndPending(Long userId1, boolean pending);
    void deleteByUserId1AndUserId2(Long userId1, Long userId2);

}
*/