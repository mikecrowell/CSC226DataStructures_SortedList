import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


public class DLLSortedListTest {

	@Test
	public void testRemoveAll() {
		DLLSortedList<String> lst = new DLLSortedList<String>(10);
		lst.add("c");
		lst.add("a");
		lst.add("a");
		lst.add("b");
		lst.add("f");
		lst.add("b");
		assertTrue(lst.removeAll("b") == 2);
		assertTrue(lst.removeAll("f") == 1);
		Iterator<String> it = lst.getIterator();
		assertTrue(it.next().equals("a"));
		assertTrue(it.next().equals("a"));
		assertTrue(it.next().equals("c"));
		assertTrue(lst.removeAll("a") == 2);
		it = lst.getIterator();
		assertTrue(it.next().equals("c"));
	}

	@Test
	public void testTrim() {
		DLLSortedList<String> lst = new DLLSortedList<String>(10);
		lst.add("c");
		lst.add("a");
		lst.add("a");
		lst.add("b");
		lst.add("f");
		lst.add("b");
		lst.trim("a", "f");
		assertTrue(lst.getNumItems() == 6);
		lst.trim("b", "c");
		assertTrue(lst.getNumItems() == 3);
		lst.trim("a", "f");
		assertTrue(lst.getNumItems() == 3);		
		lst.trim("b", "b");
		assertTrue(lst.getNumItems() == 2);		
		lst.trim("c", "c");
		assertTrue(lst.getNumItems() == 0);		
	}

}
