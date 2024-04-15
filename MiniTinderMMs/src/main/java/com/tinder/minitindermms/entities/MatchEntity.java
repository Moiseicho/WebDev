package com.tinder.minitindermms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.management.ConstructorParameters;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId1;
    private Long userId2;
    private boolean pending;

    public MatchEntity(Long userId1, Long userId2, boolean pending) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.pending = pending;
    }

    public MatchEntity() {
    }

}
