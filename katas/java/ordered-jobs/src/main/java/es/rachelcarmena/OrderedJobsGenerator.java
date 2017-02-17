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
		addNewJobsWithExistingDependencies(orderedJobs);
		return convertToString(orderedJobs);
	}

	private void addNewJobsWithExistingDependencies(List<Character> orderedJobs) {
		boolean isListCompleted = orderedJobs.size() == dependencies.size();
		if (isListCompleted)
			return;

		List<Character> jobs = dependencies.extractNewJobsWithExistingDependencies(orderedJobs);
		orderedJobs.addAll(jobs);
		addNewJobsWithExistingDependencies(orderedJobs);
	}

	private String convertToString(List<Character> orderedJobs) {
		return orderedJobs.stream().map(c -> c.toString()).collect(Collectors.joining());
	}
}