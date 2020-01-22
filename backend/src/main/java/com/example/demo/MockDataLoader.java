package com.example.demo;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

@Component
public class MockDataLoader {

	private final String uri = "data/mock_match_results.csv";
	
	@Autowired
	private MatchResultRepository matchResultRepository;

	@PostConstruct
	public void load() throws IOException, URISyntaxException  {
		Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(uri).toURI()));
		readAll(reader,(String[] data) -> matchResultRepository.save(new MatchResult(data[0].toUpperCase(), Integer.parseInt(data[1]), data[2].toUpperCase(), Integer.parseInt(data[3]))));
	}

	private List<String[]> readAll(Reader reader, Consumer<String[]> consumer) throws IOException  {
		CSVReader csvReader = new CSVReader(reader);
		List<String[]> list = new ArrayList<>();
		csvReader.iterator().forEachRemaining(l -> consumer.accept(l));
		reader.close();
		csvReader.close();
		return list;
	}
}
