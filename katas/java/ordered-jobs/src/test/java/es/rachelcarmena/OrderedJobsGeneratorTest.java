package es.rachelcarmena;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderedJobsGeneratorTest {

	@Test
	public void no_jobs() {
		String[] jobStructure = new String[1];
		jobStructure[0] = "";
		OrderedJobsGenerator orderedJobs = new OrderedJobsGenerator(jobStructure);
		assertEquals("", orderedJobs.createSequence());
	}

	@Test
	public void single_job() {
		String[] jobStructure = new String[1];
		jobStructure[0] = "a => ";
		OrderedJobsGenerator orderedJobs = new OrderedJobsGenerator(jobStructure);
		assertEquals("a", orderedJobs.createSequence());
	}

	@Test
	public void multiple_simple_jobs() {
		String[] jobStructure = new String[3];
		jobStructure[0] = "a => ";
		jobStructure[1] = "b => ";
		jobStructure[2] = "c => ";
		OrderedJobsGenerator orderedJobs = new OrderedJobsGenerator(jobStructure);
		assertEquals("abc", orderedJobs.createSequence());
	}

	@Test
	public void multiple_jobs_with_single_dependency() {
		String[] jobStructure = new String[3];
		jobStructure[0] = "a => ";
		jobStructure[1] = "b => c";
		jobStructure[2] = "c => ";
		OrderedJobsGenerator orderedJobs = new OrderedJobsGenerator(jobStructure);
		assertEquals("acb", orderedJobs.createSequence());
	}

	@Test
	public void multiple_jobs_with_multiple_dependencies() {
		String[] jobStructure = new String[6];
		jobStructure[0] = "a => ";
		jobStructure[1] = "b => c";
		jobStructure[2] = "c => f";
		jobStructure[3] = "d => a";
		jobStructure[4] = "e => b";
		jobStructure[5] = "f => ";
		OrderedJobsGenerator orderedJobs = new OrderedJobsGenerator(jobStructure);
		assertEquals("afcdbe", orderedJobs.createSequence());
	}

	@Test(expected = IllegalArgumentException.class)
	public void multiple_jobs_self_referencing_dependency() {
		String[] jobStructure = new String[3];
		jobStructure[0] = "a => ";
		jobStructure[1] = "b => ";
		jobStructure[2] = "c => c";
		OrderedJobsGenerator orderedJobs = new OrderedJobsGenerator(jobStructure);
		assertEquals("acb", orderedJobs.createSequence());
	}

	@Test(expected = IllegalArgumentException.class)
	public void multiple_jobs_circular_dependency_chain() {
		String[] jobStructure = new String[6];
		jobStructure[0] = "a => ";
		jobStructure[1] = "b => c";
		jobStructure[2] = "c => f";
		jobStructure[3] = "d => a";
		jobStructure[4] = "e => ";
		jobStructure[5] = "f => b";
		OrderedJobsGenerator orderedJobs = new OrderedJobsGenerator(jobStructure);
		assertEquals("afcdbe", orderedJobs.createSequence());
	}
}
