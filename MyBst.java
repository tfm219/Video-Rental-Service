public class MyBst { 
  TreeNode<E> root;
  
  public MyBst() {
    root = null;
  }
  
  public MyBst(E[] objects) {
    for (int i=0; i < objects.length; i++)
      insert(objects[i]);
  }
 
  
  /** Insert an element into the appropriate available place in
    * the tree. */
  public boolean insert(E element) {
    // if tree is empty, new element is the root
    while (root != null) {
      if (element > current) {
        
      }
    }
  }
  
  /** Delete an element e from the tree. Ensures that the
    * tree is still a binary search tree afterwards. */
  public boolean delete(E e) {
    TreeNode<E> parent = null;      // the node above current
    TreeNode<E> current = root;
    
    // move current to reference the node with the selected element
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      }
      else
        break;      // element is in the tree, and referenced by current
    }
    
    if (current == null)
      return false;          // element not in tree
    
    // case 1: selected element has no left child
    if (current.left == null) {
      if (parent == null) {            // if the deleted node was the root...
        root = current.right;
      }
      else {
        if (e.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    }
    
    // case 2: selected element has a left child
    else {
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;
      
      // search for the rightmost descendant in the left subtree
      // this has the largest element in the subtree!!!
      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right;
      }
      
      // copy this node's element to current
      // this preserves the ordering, because it is larger than any of current's other children
      current.element = rightMost.element;
      
      /* rightmost node has no right child, so when it moves, we can simply have
       its parent point to its left child */
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else               // if the rightmost child is current.left... 
        parentOfRightMost.left = rightMost.left;
    }
    return true;
  }

  /** Perform an in order traversal of the tree and print out its
    * elements. */
  public void inorder() {
    inorder(root);
    System.out.println();
  }
  
  /** A recursive helper function for inorder(). */
  protected void inorder(TreeNode<E> root) {
    if (root == null) 
      return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  /** Perform a preorder traversal of the tree and print out its
    * elements in the form node [left-subtree right-subtree] */
  public void preorder() {
    preorder(root);
    System.out.println();
  }
  
  /** A recursive helper function for preorder(). */
  public void preorder(TreeNode<E> root) {
    if (root == null) {
      System.out.print(".");    // use "." as placeholder for empty
      return;
    }
    System.out.print(root.element + " [");
    preorder(root.left);
    System.out.print(" ");
    preorder(root.right);
    System.out.print("] ");
  }

  /** Returns true if element is in the tree, else false. In the
    * worst-case, only needs to traverse a number of nodes equal to
    * the height of the tree. */
  public boolean search(E element) {
    
    TreeNode<E> current = root;
    while (current != null) {
      if (element.compareTo(current.element) == 0)
        return true;
      if (element.compareTo(current.element) < 0)
        current = current.left;
      else
        current = current.right;
    }
    return false;
  }

  /* Return an iterator the retrieves the elements in order. */
  public Iterator<E> inorderIterator() {
    return new InorderIterator();
  }

  /** An inner class that defines the operations for an inorder iterator. 
    * Since it is an inner class, it can access the instance variables of
    * binary search tree. */
  class InorderIterator implements Iterator<E> {
    
    /** The elements in order. */
    private ArrayList<E> list = new ArrayList<E>();
    /** Index of the next element in the iterator. */
    private int current = 0;
    
    /** Create a new InorderIterator. Performs an inorder traversal
      * and stores the results in list. */
    public InorderIterator() {
      inorder();
    }
    
    /** Perform a inorder traversal of the tree and stores the results in list. */
    private void inorder() {
      inorder(root);
    }
    
    /** A recursive helper method for inorder() */
    private void inorder(TreeNode<E> root) {
      if (root == null) 
        return;
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }
    
    /** Return true if the iterator has a next element, else false. */
    public boolean hasNext() {
      if (current < list.size())
        return true;
      else
        return false;
    }
    
    /** Return the next element in the iterator. After retrieving the
      * element, increment current to point to the element after it. */
    public E next() {
      return list.get(current++);
    }
    
    /** Remove the current element from the iterator. Not all
      * iterators support this operation, but they must provide something
      * in order to implement the interface. In this case, we remove
      * the element and rebuild the underlying ArrayList. */
    public void remove() {
      delete(list.get(current));
      list.clear();
      inorder();
    }
  }

  /** Test the binary search tree. */
  public static void main(String[] args) {
    
    // Create a tree
    BinarySearchTree<String> bst = new BinarySearchTree<String>();
    bst.insert("H");
    bst.insert("E");
    bst.insert("Q");
    bst.insert("B");
    bst.insert("G");
    bst.insert("L");
    bst.insert("V");

    // do an inorder traversal
    System.out.println("In order traversal of first tree.");
    bst.inorder();
    
    // do a preorder traveral
    System.out.println("Pre order traversal of first tree.");
    bst.preorder();
 
    // create a second tree with same elements, but inserted in a different order
    BinarySearchTree<String> bst2 = new BinarySearchTree<String>();
    bst2.insert("G");
    bst2.insert("H");
    bst2.insert("V");
    bst2.insert("B");
    bst2.insert("E");
    bst2.insert("Q");
    bst2.insert("L");

    // do the two traversals
    System.out.println();
    System.out.println("In order traversal of second tree.");
    bst2.inorder();
    System.out.println("Pre order traversal of second tree.");
    bst2.preorder();

    // search for two elements in the tree
    System.out.println();
    System.out.println("Search for elements in first tree....");
    System.out.println("Contains G? " + bst.search("G"));
    System.out.println("Contains F? " + bst.search("F"));
    
    // use the inorder iterator to traverse the tree and print in a different form
    System.out.println();
    System.out.println("Iterating over first tree...");
    Iterator it = bst.inorderIterator();
    while (it.hasNext()) 
      System.out.print(it.next() + " - ");
    System.out.println();
  }

  /** An inner class that defines a node in a tree.  */
  public static class TreeNode<E> {
    
    /* all of the instance variables have package private access, which makes it
     * possible for BinarySearchTree to access the values directly. */
    E element;
    TreeNode<E> left = null;
    TreeNode<E> right = null;
    
    public TreeNode(E e) {
      element = e;
    }
  }

}