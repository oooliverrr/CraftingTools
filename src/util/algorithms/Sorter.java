package util.algorithms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * 
 * @author Oliver
 *
 */
public class Sorter implements Serializable {

	public static final long serialVersionUID = 1L;

	/**
	 * Sort a Hashmap by ascending key.
	 * 
	 * @param map
	 *            HashMap to sort
	 * @return Sorted LinkedHashMap
	 */
	public static <K extends Comparable<? super K>, V> LinkedHashMap<K, V> sortByAscendingKey(HashMap<K, V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort((o1, o2) -> o1.getKey().compareTo(o2.getKey()));

		LinkedHashMap<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

	/**
	 * Sort a Hashmap by descending key.
	 * 
	 * @param map
	 *            HashMap to sort
	 * @return Sorted LinkedHashMap
	 */
	public static <K extends Comparable<? super K>, V> LinkedHashMap<K, V> sortByDescendingKey(HashMap<K, V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort((o1, o2) -> o2.getKey().compareTo(o1.getKey()));

		LinkedHashMap<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

	/**
	 * Sort a Hashmap by ascending value.
	 * 
	 * @param map
	 *            HashMap to sort
	 * @return Sorted LinkedHashMap
	 */
	public static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortByAscendingValue(HashMap<K, V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));

		LinkedHashMap<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

	/**
	 * Sort a Hashmap by descending value
	 * 
	 * @param map
	 *            HashMap to sort
	 * @return Sorted LinkedHashMap
	 */
	public static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortByDescendingValue(HashMap<K, V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

		LinkedHashMap<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}
}
