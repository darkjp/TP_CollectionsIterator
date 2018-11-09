package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	private HashMap<T, Integer> hm;

	// Constructors
	public HashMultiSet() {
		this.hm = new HashMap<T, Integer>();
	}

	public HashMultiSet(Collection<T> coll) {
		this();
		for (T t : coll) {
			this.add(t);
		}
	}

	public HashMultiSetIterator iterator() {
		return new HashMultiSetIterator(this);
	}

	public Integer getHm(T o) {
		return hm.get(o);
	}

	public boolean add(T e, int count) {
		if (count <= 0)
			throw new IllegalArgumentException("La valeur d'occurence ne peut être négative !");
		if (this.hm.containsKey(e)) {
			hm.put(e, hm.get(e) + count);
		} else {
			hm.put(e, count);
			return true;
		}
		return false;
	}

	@Override
	public boolean add(T e) {
		if (this.hm.containsKey(e)) {
			this.hm.put(e, hm.size() + 1);
			return true;
		} else {
			hm.put(e, 1);
			return true;
		}
	}

	public boolean contains(Object o) {
		if (hm.containsValue(o)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object e) {
		hm.remove(e);
		return true;
	}

	@Override
	public boolean remove(Object e, int count) {
		if (count <= 0)
			throw new IllegalArgumentException("La valeur d'occurence ne peut être négative !");
		else if (count == 1)
			if (hm.get(e) == 1) {
				hm.remove(e);
				return true;
			} else {
				hm.put((T) e, hm.get(e) - count);
				return true;
			}
		else {
			hm.put((T) e, hm.get(e) - count);
			return true;
		}
	}

	public int count(T o) {
		return this.hm.get(o);
	}

	@Override
	public void clear() {
		hm.clear();
	}

	@Override
	public int size() {
		int sizeHm = 0;
		for (Integer element : this.hm.values()) {
			sizeHm += element;
		}
		return sizeHm;
	}

	@Override
	public List<T> elements() {
		List<T> elements = new ArrayList<T>();
		elements = sortByValue(this.hm, elements);
		return elements;

	}

	public HashMap<T, Integer> getHash() {
		return hm;
	}

	public Integer getOccu(T e) {
		return this.hm.get(e);
	}
	
	public String toString() {
		String result = "[";
		List<T> elements = this.elements();
		for (int i = 0; i < hm.size(); i++) {
			String key = String.valueOf(elements.get(i));
			String value = String.valueOf(getOccu(elements.get(i)));
			if(i < elements.size()-1) 
				result += key + ":" + value + ";";
			else
				result += key + ":" + value + "]";
		}
		return result;
	}

	public boolean isConsistent() {
		boolean res = true;
		int size = 0;
		for (Integer value : this.hm.values()) {
			if (value < 0)
				res = false;
			size += value;
		}
		if (size != this.size())
			res = false;
		return res;
	}

	private static <K, V extends Comparable<? super V>> List<K> sortByValue(Map<K, V> map, List<K> liste) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort(Entry.comparingByValue());

		Map<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		for (K element : result.keySet()) {
			liste.add(element);
		}
		Collections.reverse(liste);
		return liste;
	}

	// ********************************************************
	// classe interne
	// ********************************************************

	public class HashMultiSetIterator implements Iterator<T> {
		private int index = 0;
		private int occu = 0;
		private T nextKey;
		private ArrayList<T> listeCle = new ArrayList<>();

		// Pourquoi un problème de type ??
		public HashMultiSetIterator(HashMultiSet<T> hmIterated) {

			for (Map.Entry<T, Integer> entry : ((Map<T, Integer>) hm).entrySet()) {
				listeCle.add(entry.getKey());
			}
		}

		/*
		 * retourne "vrai" s'il y a un autre "élément" après dans la liste
		 */
		@Override
		public boolean hasNext() {
			if (index < hm.size()) {
				return occu < hm.get(listeCle.get(index));
			} else {
				return false;
			}
		}

		/*
		 * Retourne le prochain élément dans la collection
		 */
		@Override
		public T next() {
				nextKey = listeCle.get(index);
				occu++;
				if (occu == hm.get(listeCle.get(index))) {
					index++;
					occu = 0;
				}
				return nextKey;
		}
	}
}