package cyk.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Dies ist eine Hilfsklasse, um in zufälliger Reihenfolge durch eine Liste zu
 * iterieren.
 * 
 * @author Stephan
 * 
 * @param <T>
 *          Die Klasse, welche zu Iterieren ist.
 */
public class RandomIterator<T> {
	private Random random = new Random();
	private List<T> list;

	public RandomIterator(List<T> list2) {
		this.list = new ArrayList<T>(list2);
	}

	public boolean hasNext() {
		return list.size() > 0;
	}

	public T next() {
		return list.remove(random.nextInt(list.size()));
	}
}
