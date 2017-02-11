package es.rachelcarmena.element;

import java.util.HashSet;
import java.util.Set;

public abstract class SContainer extends SResource {

	private Set<SResource> resources;

	public SContainer(String name) {
		super(name);
		this.resources = new HashSet<SResource>();
	}

	public boolean addResource(SResource resource) {
		return resources.add(resource);
	}

	public Set<SResource> getResources() {
		return resources;
	}

	// In order not to lose abstraction in SResource
	private SResource getAnyResource(String name) {
		return new SStandardFile(name, -1);
	}

	public boolean deleteResource(String resourceName) {
		return resources.remove(getAnyResource(resourceName));
	}

}
