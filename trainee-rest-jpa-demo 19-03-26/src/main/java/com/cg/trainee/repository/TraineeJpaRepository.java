package com.cg.trainee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.trainee.entity.Trainee;

public interface TraineeJpaRepository extends JpaRepository<Trainee, Integer> {

	List<Trainee> findByTraineeNameContainingIgnoreCase(String traineeName);
}
