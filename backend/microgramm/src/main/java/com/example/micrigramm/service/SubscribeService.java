package com.example.micrigramm.service;

import com.example.micrigramm.entity.Subscribe;
import com.example.micrigramm.entity.User;
import com.example.micrigramm.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubscribeService {
    private final SubscriptionRepository subscribeRepository;

    public SubscribeService(SubscriptionRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }

    public void addSubscribe (Integer id, User subscriber, User subscription){
        Subscribe s = new Subscribe(subscriber,subscription, LocalDateTime.now());
    }
}
