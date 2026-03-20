package com.cg.trainee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.trainee.dto.TraineeDTO;
import com.cg.trainee.entity.Trainee;
import com.cg.trainee.exception.TraineeAlreadyExistsException;
import com.cg.trainee.exception.TraineeNotFoundException;
import com.cg.trainee.repository.TraineeJpaRepository;

@Service
public class TraineeService implements ITraineeService {

	private final TraineeJpaRepository traineeRepo;

	public TraineeService(TraineeJpaRepository traineeRepo) {
		this.traineeRepo = traineeRepo;
	}

	@Override
	public List<TraineeDTO> getAllTrainees() {
		return traineeRepo.findAll()
				.stream()
				.map(this::toDto)
				.toList();
	}

	@Override
	public TraineeDTO getTraineeById(int traineeId) {
		return toDto(findExistingTrainee(traineeId));
	}

	@Override
	public List<TraineeDTO> getTraineesByName(String traineeName) {
		return traineeRepo.findByTraineeNameContainingIgnoreCase(traineeName)
				.stream()
				.map(this::toDto)
				.toList();
	}

	@Override
	public TraineeDTO insertTrainee(TraineeDTO traineeDTO) {
		if (traineeRepo.existsById(traineeDTO.getTraineeId())) {
			throw new TraineeAlreadyExistsException(
					"Trainee with id " + traineeDTO.getTraineeId() + " already exists");
		}
		Trainee savedTrainee = traineeRepo.save(toEntity(traineeDTO));
		return toDto(savedTrainee);
	}

	@Override
	public TraineeDTO updateTrainee(int traineeId, TraineeDTO traineeDTO) {
		Trainee existingTrainee = findExistingTrainee(traineeId);
		existingTrainee.setTraineeName(traineeDTO.getTraineeName());
		existingTrainee.setTraineeDomain(traineeDTO.getTraineeDomain());
		existingTrainee.setTraineeLocation(traineeDTO.getTraineeLocation());
		return toDto(traineeRepo.save(existingTrainee));
	}

	@Override
	public String deleteTrainee(int traineeId) {
		Trainee trainee = findExistingTrainee(traineeId);
		traineeRepo.delete(trainee);
		return "Trainee with id " + traineeId + " deleted successfully";
	}

	private Trainee findExistingTrainee(int traineeId) {
		return traineeRepo.findById(traineeId)
				.orElseThrow(() -> new TraineeNotFoundException("Trainee with id " + traineeId + " not found"));
	}

	private TraineeDTO toDto(Trainee trainee) {
		return new TraineeDTO(
				trainee.getTraineeId(),
				trainee.getTraineeName(),
				trainee.getTraineeDomain(),
				trainee.getTraineeLocation());
	}

	private Trainee toEntity(TraineeDTO traineeDTO) {
		return new Trainee(
				traineeDTO.getTraineeId(),
				traineeDTO.getTraineeName(),
				traineeDTO.getTraineeDomain(),
				traineeDTO.getTraineeLocation());
	}
}
