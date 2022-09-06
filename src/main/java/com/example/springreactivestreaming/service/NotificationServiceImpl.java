package com.example.springreactivestreaming.service;

import com.example.springreactivestreaming.domain.dto.NotificationDto;
import com.example.springreactivestreaming.domain.entity.Notification;
import com.example.springreactivestreaming.processor.SinkProcessor;
import com.example.springreactivestreaming.repository.NotificationRepository;
import com.example.springreactivestreaming.utils.NotificationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    private static final SinkProcessor<Notification> notificationSinkProcessor = new SinkProcessor<>();

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Mono<Notification> createNotification(NotificationDto notificationDto) {
        NotificationHelper.validateNotificationRequest(notificationDto);
        return Mono.just(NotificationHelper.notificationDtoToNotificationEntity(notificationDto)) //object mapping from notificationDto to the notification entity
                .flatMap(this.notificationRepository::save) //saves the notification entity into the database
                .doOnNext(notificationSinkProcessor::add)
                .doOnError(throwable -> log.error("Error on saving notification, ", throwable));
    }

    @Override
    public Flux<Notification> streamNotifications() {
        //ead the existing data from the database and merge with the processor
        return this.notificationRepository.findAll()
                .mergeWith(notificationSinkProcessor.flux());
    }
}