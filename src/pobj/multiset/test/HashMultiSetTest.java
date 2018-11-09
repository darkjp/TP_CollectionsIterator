package pobj.multiset.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pobj.multiset.HashMultiSet;
import pobj.multiset.MultiSet;

public class HashMultiSetTest {

	@Test
	public void testAdd() {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.count("a"));
	}
	
	@Test
	public void testSize() {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.size());
	}
	
	@Test
	public void testToString() {
		MultiSet<String> m = new HashMultiSet<String>();
		m.add("a",2);
		m.add("b",4);
		m.add("c", 6);
		assertEquals("[c:6;b:4;a:2]",m.toString());
	}
	
	@Test
	public void testRemove() {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a", 5);
		m.add("b", 3);
		assertEquals(5, m.count("a"));
		assertEquals(3, m.count("b"));
		m.remove("a");
		assertEquals(3, m.size());
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void testRemoveIllegal() {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a", 5);
		m.add("a", -3);
	}
	
	@Test
	public void testRemove2() {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a", 5);
		m.add("b", 3);
		assertEquals(5, m.count("a"));
		assertEquals(3, m.count("b"));
		m.remove("a", 2);
		assertEquals(6, m.size());
	}
	
	@Test
	public void testClear() {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a", 5);
		m.clear();
		assertEquals(0, m.size());
	}
	
	@Test
	public void sequenceComplexe() {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a");
		m.add("b", 2);
		m.add("a", 5);
		m.add("c", 3);
		m.remove("a",1);
		assertEquals(10, m.size());
		m.remove("c");
		assertEquals(7, m.size());
		m.add("a", 1);
		assertEquals(8, m.size());
		
;
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeIllegal() {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a", 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeIllegal2() {
		MultiSet<String> m = new HashMultiSet<>();
		m.remove("a", 0);
	}
}
