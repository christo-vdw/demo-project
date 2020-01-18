package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class MatchController {
	@Autowired
	private MatchResultRepository matchRepository;
	
	@ApiOperation(value = "View a list of available matches", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/matches")
	public List<Match> getAllMatchs() {
		return matchRepository.findAll();
	}
	
	@ApiOperation(value = "Get an match by Id")
	@GetMapping("/matches/{id}")
	public ResponseEntity<Match> getMatchById(@PathVariable(value = "id") Long id) throws RecordNotFoundException {
		Match Match = matchRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Match not found for this id :: " + id));
		return ResponseEntity.ok().body(Match);
	}

	@ApiOperation(value = "Add a match")
	@PostMapping("/matches")
	public Match createMatch(@Valid @RequestBody Match record) {
		return matchRepository.save(record);
	}

	@ApiOperation(value = "Update a match")
	@PutMapping("/matches/{id}")
	public ResponseEntity<Match> updateMatch(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Match recordDetails) throws RecordNotFoundException {
		Match record = matchRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Match not found for this id :: " + id));
		record.setVal(recordDetails.getVal());
		final Match updatedMatch = matchRepository.save(record);
		return ResponseEntity.ok(updatedMatch);
	}

	@ApiOperation(value = "Delete a match")
	@DeleteMapping("/matches/{id}")
	public Map<String, Boolean> deleteMatch(@PathVariable(value = "id") Long id) throws RecordNotFoundException {
		Match record = matchRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Match not found for this id :: " + id));
		matchRepository.delete(record);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}