package com.tinder.minitindermms.entities;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Getter
@Setter
@Data
public class MatchEntity {

    @PrimaryKey
    private UUID matchId;
    private Long userId1;
    private Long userId2;
    private boolean pending;
    @Nullable
    private LocalDateTime matchedAt;

    public MatchEntity(Long userId1, Long userId2, boolean pending)
    {
        matchId = Uuids.timeBased();
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.pending = pending;
        matchedAt = LocalDateTime.now();
    }
}

