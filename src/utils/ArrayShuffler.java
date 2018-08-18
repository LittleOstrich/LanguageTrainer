package utils;

import java.util.Random;

public class ArrayShuffler<T> {

	public T[] shuffle(T[] t) {
		int len = t.length;
		Random r = new Random();
		for (int i = 0; i < len; i++) {
			int j = r.nextInt(len);
			swap(t, i, j);
		}
		return t;
	}

	private void swap(T[] t, int i, int j) {
		T temp = t[i];
		t[i] = t[j];
		t[j] = temp;
	}
}
