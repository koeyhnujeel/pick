package com.zunza.pick.event.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zunza.pick.commons.advice.exception.EventNotFoundException;
import com.zunza.pick.event.entity.Event;
import com.zunza.pick.event.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventCacheService {

	private final EventRepository eventRepository;

	@Cacheable(cacheNames = "eventCache", key = "#eventId")
	public Event getEvent(Long eventId) {
		return eventRepository.findById(eventId)
			.orElseThrow(EventNotFoundException::new);
	}
}
