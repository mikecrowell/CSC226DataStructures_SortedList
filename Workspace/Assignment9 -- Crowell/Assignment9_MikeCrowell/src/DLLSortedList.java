import java.util.*;


public class DLLSortedList<T> implements Iterator<T>{

	private DLLNode<T> head, tail, currentNode;
	private int numItems;
	
	
	public DLLSortedList(int initCap) {
		this.numItems = 0;
		head = tail = currentNode = null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void add(T item){
		DLLNode<T> newNode = new DLLNode<T>(item);
		
		//When list is empty
		if(head == null){
			head = tail = newNode;
			numItems++;
			return;
		}
		
		//Insert at front
		if( ((Comparable<T>) head.getInfo()).compareTo(item) >= 0 ){
			newNode.setForward(head);
			head.setBack(newNode);
			head = newNode;
			numItems++;
			return;
		}
		
		
		//general case
		DLLNode<T> cursor = head;
		
		while(cursor != null){
			if( ((Comparable<T>) cursor.getInfo()).compareTo(item) < 0 ){
				cursor = cursor.getForward();
			}else{
				break;
			}
		}
		if(cursor != null){			
			newNode.setForward(cursor);
			newNode.setBack(cursor.getBack());
			cursor.getBack().setForward(newNode);
			cursor.setBack(newNode);
		}
		else{
			newNode.setBack(tail);
			tail.setForward(newNode);
			tail = newNode;
		}
		numItems++;	

	}	
	
	
	public boolean contains(T item){
		DLLNode<T> cursor = head;
		
		while(cursor != null){
			if(cursor.getInfo().equals(item) ){
				return true;
			}else{
				cursor = cursor.getForward();
			}
		}	
		return false;
	}
	
	
	public void remove(T item){
		if(contains(item)){
			DLLNode<T> cursor = head, precursor = null;
			//remove first item
			if(head.getInfo().equals(item) ){
				head = head.getForward();
				numItems--;
				return;
			}
			
			//remove item other than first
			while(cursor != null){
				if(!cursor.getInfo().equals(item) ){
					precursor = cursor;
					cursor = cursor.getForward();
				}else{
					break;
				}
			}	
			precursor.setForward(cursor.getForward());
			if(cursor.getForward() != null){
				cursor.getForward().setBack(precursor);
			}
			else{
				tail = precursor;
			}
			numItems--;
		}
	}
	
	
	
	public String toString(){
		String str = "List is: \n";
		DLLNode<T> cursor = head;
		while(cursor != null){
			str += cursor.getInfo().toString() + " ";
			cursor = cursor.getForward();
		}	
		return str;		
	}


	public Iterator<T> getIterator(){
		currentNode = head;
		return this;
	}

	@Override
	public boolean hasNext() {
		return currentNode != null;
	}



	@Override
	public T next() {
		T temp = currentNode.getInfo();
		currentNode = currentNode.getForward();
		return temp;
	}



	@Override
	public void remove() {
		remove(currentNode.getInfo());
		
	}
	

	public int removeAll(T item){

		boolean foundFirst = false, removedHead = false;
		int count = 0;
		
		if(contains(item)){
			DLLNode<T> cursor = head, precursor = null;
			
			if(head.getInfo().equals(item) ){
				removedHead = true;
				foundFirst = true;
				count++;
				precursor = head;
				cursor = cursor.getForward();
			}
			
			while(cursor != null){
				if(!cursor.getInfo().equals(item) ){
					if(!foundFirst){
						precursor = cursor;
						cursor = cursor.getForward();
					}else{					
						break;
					}
					
				}else{
					if(!foundFirst){
						foundFirst = true;
					}
					cursor = cursor.getForward();
					count++;
				}
			}	
			//cursor = cursor.getBack();
			if(foundFirst){
				precursor.setForward(cursor);
				if(cursor != null){
					cursor.setBack(precursor);
				}
				else{
					tail = precursor;
				}
				if(removedHead){
					head = cursor;
				}
			}

			numItems -= count;
			
		}		
		return count;
	}
	
	
	@SuppressWarnings("unchecked")
	public void trim(T lowerBnd, T upperBnd){
		boolean isDone = false;
		while(!isDone){
			if( ((Comparable<T>) head.getInfo()).compareTo(lowerBnd) < 0){
				head = head.getForward();
				if(head != null){
					head.setBack(null);
				}else{
					isDone = true;
				}
				numItems--;				
			}else{
				isDone = true;
			}
		}
		
		isDone = false;
		
		while(!isDone){
			if( ((Comparable<T>) tail.getInfo()).compareTo(upperBnd) > 0){
				tail = tail.getBack();
				if(tail != null){
					tail.setForward(null);
				}else{
					isDone = true;
				}
				numItems--;				
			}else{
				isDone = true;
			}
		}		
	}
	
	public int getNumItems() {
		return numItems;
	}	
	
}


class DLLNode<T>{
	private DLLNode<T> forward, back;
	private T info;
	
	
	public DLLNode(T info) {
		forward = back = null;
		this.info = info;
	}
	
	
	public DLLNode<T> getForward() {
		return forward;
	}
	public void setForward(DLLNode<T> forward) {
		this.forward = forward;
	}
	public DLLNode<T> getBack() {
		return back;
	}
	public void setBack(DLLNode<T> back) {
		this.back = back;
	}
	public T getInfo() {
		return info;
	}
	public void setInfo(T info) {
		this.info = info;
	}
	
	
	
	
}