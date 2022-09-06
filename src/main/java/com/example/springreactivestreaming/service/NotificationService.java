package com.example.springreactivestreaming.service;

import com.example.springreactivestreaming.domain.dto.NotificationDto;
import com.example.springreactivestreaming.domain.entity.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificationService {
    Mono<Notification> createNotification(NotificationDto notificationDto);
    Flux<Notification> streamNotifications();
}
