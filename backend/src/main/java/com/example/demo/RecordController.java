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
public class RecordController {
	@Autowired
	private RecordRepository recordRepository;
	
	@ApiOperation(value = "View a list of available records", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/records")
	public List<Record> getAllRecords() {
		return recordRepository.findAll();
	}
	
	@ApiOperation(value = "Get an record by Id")
	@GetMapping("/records/{id}")
	public ResponseEntity<Record> getRecordById(@PathVariable(value = "id") Long id) throws RecordNotFoundException {
		Record Record = recordRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Record not found for this id :: " + id));
		return ResponseEntity.ok().body(Record);
	}

	@ApiOperation(value = "Add a record")
	@PostMapping("/records")
	public Record createRecord(@Valid @RequestBody Record record) {
		return recordRepository.save(record);
	}

	@ApiOperation(value = "Update a record")
	@PutMapping("/records/{id}")
	public ResponseEntity<Record> updateRecord(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Record recordDetails) throws RecordNotFoundException {
		Record record = recordRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Record not found for this id :: " + id));
		record.setVal(recordDetails.getVal());
		final Record updatedRecord = recordRepository.save(record);
		return ResponseEntity.ok(updatedRecord);
	}

	@ApiOperation(value = "Delete a record")
	@DeleteMapping("/records/{id}")
	public Map<String, Boolean> deleteRecord(@PathVariable(value = "id") Long id) throws RecordNotFoundException {
		Record record = recordRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Record not found for this id :: " + id));
		recordRepository.delete(record);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}