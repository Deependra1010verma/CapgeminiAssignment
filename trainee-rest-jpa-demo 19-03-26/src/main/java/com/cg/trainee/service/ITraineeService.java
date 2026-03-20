package com.cg.trainee.service;

import java.util.List;

import com.cg.trainee.dto.TraineeDTO;

public interface ITraineeService {

	List<TraineeDTO> getAllTrainees();

	TraineeDTO getTraineeById(int traineeId);

	List<TraineeDTO> getTraineesByName(String traineeName);

	TraineeDTO insertTrainee(TraineeDTO traineeDTO);

	TraineeDTO updateTrainee(int traineeId, TraineeDTO traineeDTO);

	String deleteTrainee(int traineeId);
}
