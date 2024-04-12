package com.tinder.minitindermms.controllers;

import com.tinder.minitindermms.entities.MatchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tinder.minitindermms.service.MatchService;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/confirmed")
    public List<Long> getConfirmedMatches(@RequestBody Long userId) {
        return matchService.getConfirmedMatches(userId);
    }

    @GetMapping("/pending/sent")
    public List<Long> getPendingSentMatches(@RequestBody Long userId) {
        return matchService.getPendingSentMatches(userId);
    }

    @GetMapping("/pending/received")
    public List<Long> getPendingReceivedMatches(@RequestBody Long userId) {
        return matchService.getPendingReceivedMatches(userId);
    }

    @PostMapping("/{otherUserId}")
    public MatchEntity createMatch(@RequestBody Long userId, @PathVariable Long otherUserId) {
        return matchService.createMatch(userId, otherUserId);
    }


}
