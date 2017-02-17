package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderedJobsGenerator {

	private JobDependencies dependencies;

	public OrderedJobsGenerator(String[] jobStructure) {
		this.dependencies = new JobDependencies(jobStructure);
	}

	public String createSequence() {
		List<Character> jobsWithoutDependencies = dependencies.extractJobsWithoutDependencies();
		List<Character> orderedJobs = new ArrayList<>();
		orderedJobs.addAll(jobsWithoutDependencies);
		addJobsWithExistingDependencies(orderedJobs);
		return convertToString(orderedJobs);
	}

	private void addJobsWithExistingDependencies(List<Character> orderedJobs) {
		boolean isListCompleted = orderedJobs.size() == dependencies.size();
		if (isListCompleted)
			return;

		List<Character> jobsWithExistingDependencies = dependencies.extractJobsWithExistingDependencies(orderedJobs);
		orderedJobs.addAll(jobsWithExistingDependencies);
		addJobsWithExistingDependencies(orderedJobs);
	}

	private String convertToString(List<Character> orderedJobs) {
		return orderedJobs.stream().map(c -> c.toString()).collect(Collectors.joining());
	}
}