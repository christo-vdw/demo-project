package com.example.demo.mock;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.example.demo.model.MatchResult;
import com.example.demo.storage.MatchResultRepository;
import com.opencsv.CSVReader;

@Component
public class MockDataLoader {

	private final String uri = "classpath:data/mock_match_results.csv";
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private MatchResultRepository matchResultRepository;

	@PostConstruct
	public void load() throws IOException, URISyntaxException  {
		Resource resource = resourceLoader.getResource(uri);
		readAll( new InputStreamReader(resource.getInputStream()),(String[] data) -> matchResultRepository.save(new MatchResult(data[0].toUpperCase(), Integer.parseInt(data[1]), data[2].toUpperCase(), Integer.parseInt(data[3]))));
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
