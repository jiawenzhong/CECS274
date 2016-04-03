/**
 * The class create a list of question objects
 * @author jiawen
 *
 */
public class LinkedList {
	/**
	 * the first element in Node
	 */
	private Node first;
	/**
	 * last element in Node
	 */
	private Node last;

	/**
	 * LinkedList constructor
	 */
	public LinkedList () {
		first = null;
		last = null;
	}

	/**
	 * check if the list is empty
	 * @return	true if is the first element is null
	 */
	public boolean isEmpty () {
		return first == null;
	}

	/**
	 * Find the size of the circle list
	 * @return the number of circle added in the list
	 */
	public int size () {
		int count = 0;
		Node p = first;
		while (p != null) {
			count ++;
			p = p.getNext();
		}
		return count;
	}

	/**
	 * accessor - get the element at a specific position in the list
	 * @param i	the position that want to return
	 * @return	the object in position i
	 */
	public Node get (int i) {
		Node prev = first;
		for (int j = 1; j <= i; j ++) {
			prev = prev.getNext();
		}
		return prev;
	}

	/**
	 * Override toString to display the numbers in the question object
	 */
	@Override
	public String toString () {
		String str = "";
		Node n = first;
		while (n != null) {
			str = str + n.getValue();
			n = n.getNext();
		}
		return str;
	}

	/**
	 * add a question in the linkedlist class
	 * @param q the new question object
	 */
	public void addNode (Question q) {
		if (isEmpty()) {
			first = new Node(q);
			last = first;
		} else {
			Node n = new Node(q);
			last.setNext(n);
			last = n;
		}
	}

	/**
	 * remove the Node from the linkedlist
	 * @param i	the Node that needs to be removed
	 * @return	the removed Node
	 */
	public Node remove (int i) {
		Node n = null;
		if (i < 0 || i >= size()) {
			System.out.println("Index out of bounds.");
		}else {
			if (i == 0) {
				n = first;
				first = first.getNext();
				if (first == null) {
					last = null;
				}
			} else {
				Node prev = get(i - 1);
				n = prev.getNext();
				prev.setNext(prev.getNext().getNext());
				if (prev.getNext() == null) {
					last = prev;
				}
			}
		}
		return n;
	}

	/**
	 * facade function for add at an index
	 * @param s the new element
	 * @param i the element that should be in (Index)
	 */
	public void add (Question q, int i) {
		first = add (q, i, first);
	}

	/**
	 * add a new element to the list at index i
	 * @param s the new element
	 * @param i the index that the element should be in
	 * @param list the first element of the list
	 * @return the added element
	 */
	public Node add (Question q, int i, Node list) {
		if (i < 0 || i > size()) {
			System.out.println("Index out of bound.");
		} else if (i == 0){
			return new Node (q, list);	
		} else {
			//Node n = get (i - 1);
			list.setNext(add(q, i - 1, list.getNext()));	
		}
		return list;
	}
}
