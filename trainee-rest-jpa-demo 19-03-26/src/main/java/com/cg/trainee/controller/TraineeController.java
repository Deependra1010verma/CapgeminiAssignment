package com.cg.trainee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.trainee.dto.TraineeDTO;
import com.cg.trainee.service.ITraineeService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/trainees")
public class TraineeController {

	private final ITraineeService traineeService;

	public TraineeController(ITraineeService traineeService) {
		this.traineeService = traineeService;
	}

	@GetMapping
	public ResponseEntity<List<TraineeDTO>> getAllTrainees(
			@RequestParam(required = false) String traineeName) {
		if (traineeName != null && !traineeName.isBlank()) {
			return ResponseEntity.ok(traineeService.getTraineesByName(traineeName));
		}
		return ResponseEntity.ok(traineeService.getAllTrainees());
	}

	@GetMapping("/name/{traineeName}")
	public ResponseEntity<List<TraineeDTO>> getTraineesByName(@PathVariable String traineeName) {
		return ResponseEntity.ok(traineeService.getTraineesByName(traineeName));
	}

	@GetMapping("/{traineeId}")
	public ResponseEntity<TraineeDTO> getTraineeById(@PathVariable int traineeId) {
		return ResponseEntity.ok(traineeService.getTraineeById(traineeId));
	}

	@PostMapping
	public ResponseEntity<TraineeDTO> insertTrainee(@Valid @RequestBody TraineeDTO traineeDTO) {
		return new ResponseEntity<>(traineeService.insertTrainee(traineeDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{traineeId}")
	public ResponseEntity<TraineeDTO> updateTrainee(
			@PathVariable int traineeId,
			@Valid @RequestBody TraineeDTO traineeDTO) {
		return ResponseEntity.ok(traineeService.updateTrainee(traineeId, traineeDTO));
	}

	@DeleteMapping("/{traineeId}")
	public ResponseEntity<String> deleteTrainee(@PathVariable int traineeId) {
		return ResponseEntity.ok(traineeService.deleteTrainee(traineeId));
	}
}
