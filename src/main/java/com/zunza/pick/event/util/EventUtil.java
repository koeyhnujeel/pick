package com.zunza.pick.event.util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.zunza.pick.commons.advice.exception.EventExpiredException;
import com.zunza.pick.commons.advice.exception.EventNotStartedException;

@Component
public class EventUtil {

	public void validateEventTime(LocalDateTime requestedTime, LocalDateTime startDt, LocalDateTime endDt) {
		if (requestedTime.isBefore(startDt)) {
			throw new EventNotStartedException();
		}

		if (requestedTime.isAfter(endDt)) {
			throw new EventExpiredException();
		}
	}
}
