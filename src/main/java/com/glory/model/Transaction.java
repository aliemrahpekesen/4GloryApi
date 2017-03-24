package com.glory.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.glory.model.enums.Status;

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;

	public Transaction() {
	}

	public Transaction(Long id, Long ruleId, LocalDateTime startTime, LocalDateTime endTime, Long points, Status status,
			Long memberId) {
		super();
		this.id = id;
		this.ruleId = ruleId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.points = points;
		this.status = status;
		this.memberId = memberId;
	}

	private Long ruleId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long points;
	private Status status;
	private Long memberId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

}
