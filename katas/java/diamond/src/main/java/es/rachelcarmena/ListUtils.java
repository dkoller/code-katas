package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
	
	public static List<String> addReverse(List<String> initialList) {
		List<String> newList = new ArrayList<>(initialList);
		for (int i = initialList.size() - 2; i >= 0; i--) {
			newList.add(initialList.get(i));
		}
		return newList;
	}
}
