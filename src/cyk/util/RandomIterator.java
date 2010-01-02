package cyk.util;

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
	private final Random rand = new Random();
	private List<T> list;

	private final boolean[] served;
	private int servedCount = 0;

	private final int LIST_SIZE;

	private int lower;
	private int upper;

	public RandomIterator(List<T> list) {
		this.list = list;

		LIST_SIZE = list.size();
		served = new boolean[LIST_SIZE];
		lower = 0;
		upper = LIST_SIZE - 1;
	}

	public boolean hasNext() {
		return servedCount < LIST_SIZE;
	}

	private int index, range;

	public T next() {
		range = upper - lower + 1;

		do {
			index = lower + rand.nextInt(range);
		} while (served[index]);

		if (index == lower) {
			lower++;
		} else if (index == upper) {
			upper = upper - 1;
		}

		served[index] = true;
		servedCount++;

		return list.get(index);
	}
}
