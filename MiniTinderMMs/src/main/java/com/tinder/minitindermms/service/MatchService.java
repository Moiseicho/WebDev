package com.tinder.minitindermms.service;

import com.tinder.minitindermms.entities.MatchEntity;
import com.tinder.minitindermms.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Long> getConfirmedMatches(Long userId) {
        List<Long> matches = matchRepository.findByUserId2AndPending(userId, false)
                .stream()
                .map(MatchEntity::getUserId1)
                .collect(Collectors.toList());
        matches.addAll(matchRepository.findByUserId1AndPending(userId, false)
                .stream()
                .map(MatchEntity::getUserId2)
                .toList());

        return matches;
    }

    public List<Long> getPendingSentMatches(Long userId) {
        return matchRepository.findByUserId1AndPending(userId, true)
                .stream()
                .map(MatchEntity::getUserId2)
                .collect(Collectors.toList());
    }

    public List<Long> getPendingReceivedMatches(Long userId) {
        return matchRepository.findByUserId2AndPending(userId, true)
                .stream()
                .map(MatchEntity::getUserId1)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> createMatch(Long userId, Long otherUserId) {
        if(userId.equals(otherUserId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't match a user with himself!");
        List<Long> matches = matchRepository.findByUserId2AndPending(userId, true)
                .stream()
                .map(MatchEntity::getUserId1)
                .collect(Collectors.toList());
        if(matches.contains(otherUserId)) {
            UUID matchId = matchRepository.findByUserId1AndPending(otherUserId, true).get(0).getMatchId();
            matchRepository.deleteByMatchId(matchId);
            matchRepository.save(new MatchEntity(otherUserId, userId, false));
            return ResponseEntity.status(HttpStatus.OK).body("Match accepted");
        }
        matches = matchRepository.findByUserId1AndPending(userId, true)
                .stream()
                .map(MatchEntity::getUserId2)
                .collect(Collectors.toList());
        if(matches.contains(otherUserId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Match already exists");
        }
        matchRepository.save(new MatchEntity(userId, otherUserId, true));
        return ResponseEntity.status(HttpStatus.OK).body("Match requested");
    }
}
