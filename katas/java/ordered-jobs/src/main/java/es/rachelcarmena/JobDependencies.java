package es.rachelcarmena;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JobDependencies {

	Map<Character, Character> dependencies;

	public JobDependencies(String[] jobStructure) {
		fillDependencies(jobStructure);
	}

	public int size() {
		return dependencies.size();
	}

	public List<Character> extractJobsWithoutDependencies() {
		return extractJobs(hasNoDependency());
	}

	public List<Character> extractJobsWithExistingDependencies(List<Character> jobs) {
		return extractJobs(hasExistingDependency(jobs));
	}

	private void fillDependencies(String[] jobStructure) {
		dependencies = new HashMap<>();
		if (jobStructure.length == 1 && jobStructure[0].equals(""))
			return;

		for (String line : jobStructure) {
			String[] relation = line.split(" => ");
			Character childJob = relation[0].charAt(0);
			Character parentJob = (relation.length == 1) ? null : relation[1].charAt(0);
			if (childJob.equals(parentJob))
				throw new IllegalArgumentException("Self referencing not allowed");
			dependencies.put(childJob, parentJob);
		}

		validateNotCircularDependencies();
	}

	private void validateNotCircularDependencies() {
		for (Entry<Character, Character> relation : dependencies.entrySet()) {
			Set<Character> visitedJobs = new HashSet<>();
			Character childJob = relation.getKey();
			visitedJobs.add(childJob);
			Character parentJob = relation.getValue();
			boolean existsDependency = parentJob != null;
			while (existsDependency) {
				if (!visitedJobs.add(parentJob))
					throw new IllegalArgumentException("Circular dependency not allowed");
				parentJob = dependencies.get(parentJob);
				existsDependency = parentJob != null;
			}
		}
	}

	private Predicate<? super Entry<Character, Character>> hasNoDependency() {
		return e -> e.getValue() == null;
	}

	private Predicate<? super Entry<Character, Character>> hasExistingDependency(List<Character> jobs) {
		return e -> e.getValue() != null && !jobs.contains(e.getKey()) && jobs.contains(e.getValue());
	}

	private List<Character> extractJobs(Predicate<? super Entry<Character, Character>> condition) {
		return dependencies.entrySet().stream().filter(condition).map(Entry::getKey).collect(Collectors.toList());
	}
}
