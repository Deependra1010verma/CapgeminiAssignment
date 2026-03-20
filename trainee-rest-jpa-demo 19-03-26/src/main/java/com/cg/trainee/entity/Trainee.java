package com.cg.trainee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trainee")
public class Trainee {

	@Id
	@Column(name = "trainee_id")
	private Integer traineeId;

	@Column(name = "trainee_name", nullable = false, length = 100)
	private String traineeName;

	@Column(name = "trainee_domain", nullable = false, length = 100)
	private String traineeDomain;

	@Column(name = "trainee_location", nullable = false, length = 100)
	private String traineeLocation;

	public Trainee() {
	}

	public Trainee(Integer traineeId, String traineeName, String traineeDomain, String traineeLocation) {
		this.traineeId = traineeId;
		this.traineeName = traineeName;
		this.traineeDomain = traineeDomain;
		this.traineeLocation = traineeLocation;
	}

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	public String getTraineeName() {
		return traineeName;
	}

	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}

	public String getTraineeDomain() {
		return traineeDomain;
	}

	public void setTraineeDomain(String traineeDomain) {
		this.traineeDomain = traineeDomain;
	}

	public String getTraineeLocation() {
		return traineeLocation;
	}

	public void setTraineeLocation(String traineeLocation) {
		this.traineeLocation = traineeLocation;
	}
}
