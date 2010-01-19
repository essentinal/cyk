package cyk.util;

import java.util.Iterator;
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
public class RandomIterator<T> implements Iterator<T> {
	private final Random rand = new Random();
	private List<T> list;

	private final boolean[] served;
	private int servedCount = 0;

	private final int LIST_SIZE;

	private int index;
	private int range;
	private int lower;
	private int upper;

	/**
	 * Erzeugt einen neuen Zufallsiterator für eine Liste.
	 * 
	 * @param list Liste, über die Iteriert werden soll.
	 */
	public RandomIterator(List<T> list) {
		this.list = list;

		LIST_SIZE = list.size();
		served = new boolean[LIST_SIZE];
		lower = 0;
		upper = LIST_SIZE - 1;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return servedCount < LIST_SIZE;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
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

	/**
	 * Entfernen wird nicht unterstützt.
	 */
	@Override
	@Deprecated
	public void remove() {
		
	}
}
