package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class MatchResultController {
	@Autowired
	private MatchResultRepository matchResultRepository;
	
	@ApiOperation(value = "View a list of available match results", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping("/match_results")
	public List<MatchResult> getAllMatchs() {
		return matchResultRepository.findAll();
	}
	
	@ApiOperation(value = "Get an match result by Id")
	@GetMapping("/match_results/{id}")
	public ResponseEntity<MatchResult> getMatchById(@PathVariable(value = "id") Long id) throws RecordNotFoundException {
		MatchResult Match = matchResultRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Match result not found for this id :: " + id));
		return ResponseEntity.ok().body(Match);
	}

	@ApiOperation(value = "Add a match result")
	@PostMapping("/match_results")
	public MatchResult createMatch(@Valid @RequestBody MatchResult record) {
		return matchResultRepository.save(record);
	}

	@ApiOperation(value = "Update a match result")
	@PutMapping("/match_results/{id}")
	public ResponseEntity<MatchResult> updateMatchResult(@PathVariable(value = "id") Long id,
			@Valid @RequestBody MatchResult recordDetails) throws RecordNotFoundException {
		MatchResult record = matchResultRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Match result not found for this id :: " + id));
		
		record.setTeamA(recordDetails.getTeamA());
		record.setTeamB(recordDetails.getTeamB());
		record.setTeamAGoals(recordDetails.getTeamAGoals());
		record.setTeamBGoals(recordDetails.getTeamBGoals());
		
		final MatchResult updatedMatch = matchResultRepository.save(record);
		return ResponseEntity.ok(updatedMatch);
	}

	@ApiOperation(value = "Delete a match result")
	@DeleteMapping("/match_results/{id}")
	public Map<String, Boolean> deleteMatch(@PathVariable(value = "id") Long id) throws RecordNotFoundException {
		MatchResult record = matchResultRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Match result not found for this id :: " + id));
		matchResultRepository.delete(record);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}