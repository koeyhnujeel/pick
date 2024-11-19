package com.zunza.pick.event.entity;

import java.time.LocalDateTime;

import com.zunza.pick.commons.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseEntity {

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private LocalDateTime startDt;

	@Column(nullable = false)
	private LocalDateTime endDt;

	public Event(String title, LocalDateTime startDt, LocalDateTime endDt) {
		this.title = title;
		this.startDt = startDt;
		this.endDt = endDt;
	}
}
