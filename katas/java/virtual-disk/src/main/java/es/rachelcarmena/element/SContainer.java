package es.rachelcarmena.element;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class SContainer extends SResource {

	private List<SResource> resources;

	public SContainer(String name) {
		super(name);
		this.resources = new ArrayList<>();
	}

	public void addResource(SResource resource) {
		resources.add(resource);
	}

	public List<SResource> getResources() {
		return resources;
	}

	public boolean deleteResource(String resourceName) {
		for (ListIterator<SResource> iterator = resources.listIterator(); iterator.hasNext();) {
			SResource resource = (SResource) iterator.next();
			if (resource.getName().equals(resourceName)) {
				resources.remove(iterator.previousIndex());
				return true;
			}
		}
		return false;
	}

}
