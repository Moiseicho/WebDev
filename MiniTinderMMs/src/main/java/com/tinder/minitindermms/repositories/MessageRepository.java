package com.tinder.minitindermms.repositories;

import com.tinder.minitindermms.entities.MessageEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends CassandraRepository<MessageEntity, Long> {
    @AllowFiltering
    List<MessageEntity> findBySenderIdAndRecipientId(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);

    @AllowFiltering
    List<MessageEntity> findBySenderId(@Param("senderId") Long senderId);

    @AllowFiltering
    List<MessageEntity> findByRecipientId(@Param("recipientId") Long recipientId);
}
/*
@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findBySenderIdAndRecipientId(Long senderId, Long recipientId);
    List<MessageEntity> findBySenderId(Long senderId);
    List<MessageEntity> findByRecipientId(Long recipientId);
}
*/