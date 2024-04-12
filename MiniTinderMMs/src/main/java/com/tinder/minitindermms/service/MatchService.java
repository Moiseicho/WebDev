package com.tinder.minitindermms.service;

import com.tinder.minitindermms.entities.MatchEntity;
import com.tinder.minitindermms.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Long> getConfirmedMatches(Long userId) {
        List<Long> matches = matchRepository.findUserId1ByUserId2AndPending(userId, false);
        matches.addAll(matchRepository.findUserId2ByUserId1AndPending(userId, false));

        return matches;
    }

    public List<Long> getPendingSentMatches(Long userId) {
        return matchRepository.findUserId2ByUserId1AndPending(userId, true);
    }

    public List<Long> getPendingReceivedMatches(Long userId) {
        return matchRepository.findUserId1ByUserId2AndPending(userId, true);
    }

    public MatchEntity createMatch(Long userId, Long otherUserId) {
        return matchRepository.save(new MatchEntity(userId, otherUserId, true));
    }
}
