import java.util.*;


public class DLLTest {

	public static void main(String[] args) {

		DLLSortedList<String> lst = new DLLSortedList<String>(10);
		
		lst.add("c");
		lst.add("a");
		lst.add("a");
		lst.add("b");
		lst.add("f");
		lst.add("b");
		
		System.out.println("Original List");
		System.out.println(lst);
		System.out.println("");
		
		lst.trim("a", "f");	
		System.out.println("Trim A and F");
		System.out.println(lst);
		System.out.println(lst.getNumItems());
		System.out.println("");
		
		System.out.println("Trim B and C");
		lst.trim("b", "c");		
		System.out.println(lst);
		System.out.println(lst.getNumItems());		
		System.out.println("");
		
		System.out.println("Trim A and F");
		lst.trim("a", "f");		
		System.out.println(lst);
		System.out.println(lst.getNumItems());	
		System.out.println("");
		
		System.out.println("Trim B and B");
		lst.trim("b", "b");		
		System.out.println(lst);
		System.out.println(lst.getNumItems());	
		System.out.println("");
		
		System.out.println("Trim C and C");
		lst.trim("c", "c");		
		System.out.println(lst);
		System.out.println(lst.getNumItems());	
		System.out.println("");		
		
//		Iterator<String> it = lst.getIterator();		
//		System.out.println(it.next());
		
		
	}

}


