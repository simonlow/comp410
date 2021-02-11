package assignment2_f20;

import static org.junit.Assert.assertNotNull;

//--------------------------------------------------------------

public class TreeMap_imp implements TreeMap { 
  TMCell root;
  // add fields as you need to in order to provide the required behavior
  // also you may add private methods to assist you as needed
  // in implementing
  
  //-------------------------------------------------------------

  TreeMap_imp () { 
    root = null; 
    // for added fields you can add appropriate initialization code here
  }

  //-------------------------------------------------------------

  // dont change, we need this for grading
  @Override
  public TMCell getRoot() { return this.root; }
  
  //-------------------------------------------------------------
  // "height" is a complete implementation 
  // of the public interface method height
  // it is here to illustrate fr you how the textbook sets up 
  // the method implementation as recursion
  // you may include this in your project directly

  public int height() { // public interface method signature
  // this method is the public interface method
  // it is not recursive, but it calls a recursive
  // private method and passes it access to the tree cells
    return height_r(this.getRoot());
  }
  private int height_r(TMCell c) { 
  // inner method with different signature
  // this helper method uses recursion to traverse
  // and process the recursive structure of the tree of cells
    if (c==null) return -1;
    int lht = height_r(c.getLeft());
    int rht = height_r(c.getRight());
    return Math.max(lht,rht) + 1;
  }
  
  public Value put( String k, Value v ) {
	  TMCell root = getRoot();
	  if (root == null) {
		  TMCell newRoot = new TMCell_imp(k, v);
		  this.root = newRoot;
		  return null;
	  }
	  else if (!hasKey(k)) {
		  putNew(getRoot(), k, v);
		  return null; 
	  }
	  else {
		  TMCell cell = getKeyCell(getRoot(), k);
		  Value old = cell.getValue();
		  cell.setValue(v);
		  return old;
	  }
  }
  
  public Value get(String k) {
	  if (!hasKey(k)) {
		  return null;
	  }
	  else {
		  TMCell cell = getKeyCell(getRoot(), k);
		  return cell.getValue();
	  }
  }

  public void remove (String k) {
	  if(hasKey(k)) {
		  if (getRoot().getKey().equals(k)) {
			 this.root = removeRoot(getRoot());
		  }
		  else {
			  removeHelp(getRoot(), k); 
		  }
	  }
  }

  public boolean hasKey(String k) {
	  return hasKeyHelp(getRoot(), k);
  }
  
  public int size() {
	  TMCell cell = getRoot();
	  if (cell != null) {
		  return getSize(cell, 1);
	  }
	  else 
		  return 0;
  }
  
  public String maxKey() {
	  if (getRoot() != null) {
		  return findMax(getRoot()).getKey();
	  }
	  return null;
  }
  
  public String minKey() {
	  if (getRoot() != null) {
		  return findMin(getRoot()).getKey();
	  }
	  return null;
  }
  
  public String[] getKeys() {
	  if (getRoot() == null) {
		  return null;
	  }
	  TMCell [] arr = new TMCell [size()];
	  makeArr(getRoot(), arr, 0);
	  String [] keys = new String[size()];
	  for(int i = 0; i < size(); i++) {
		  keys[i] = arr[i].getKey();
	  }
	  return keys;
  }
  
  //Helper Methods
  private static boolean hasKeyHelp(TMCell cell, String key ) {
	  if (cell == null) {
		  return false; 
	  }
	  else if (cell.getKey().equals(key)) {
		  return true;
	  }
	  else {
		  if (key.compareTo(cell.getKey()) < 0) {
			  return hasKeyHelp(cell.getLeft(), key);
		  }
		  else if (key.compareTo(cell.getKey()) > 0) {
			  return hasKeyHelp(cell.getRight(), key);
		  }
		  else {
			  return false;
		  }
	  }
  }
  
  //put helper method for !hasKey(k)
  private static void putNew(TMCell cell, String k, Value v) {
	  if (k.compareTo(cell.getKey()) < 0) {
		  if(cell.getLeft() == null) {
			  TMCell newPut = new TMCell_imp(k, v);
			  cell.setLeft(newPut);
		  }
		  else { 
			  putNew(cell.getLeft(), k, v);
		  }
	  }
	  else if (k.compareTo(cell.getKey()) > 0) {
		  if(cell.getRight() == null) {
			  TMCell newPut = new TMCell_imp(k, v);
			  cell.setRight(newPut);
		  }
		  else { 
			  putNew(cell.getRight(), k, v);
		  }
	  }  
  }
  
  //called after hasKey(k) is true
  private static TMCell getKeyCell(TMCell cell, String k) {
	  if (k.compareTo(cell.getKey()) == 0) {
		  return cell; 
	  }
	  else if (k.compareTo(cell.getKey()) > 0) {
		  return getKeyCell(cell.getRight(), k);
	  }
	  else {
		  return getKeyCell(cell.getLeft(), k);
	  }
  }
  
  //remove helper function if hasKey(k) 
  private static void removeHelp(TMCell cell, String k) {
	
	  TMCell left = cell.getLeft();
	  if (left != null) {
		  if (left.getKey().equals(k)) {
			  if (left.getLeft() != null)
				  cell.setLeft(left.getLeft());
			  if (left.getRight() != null) 
				  cell.setRight(left.getRight());
			  left.setLeft(null);
			  left.setRight(null);
		  }
		  else {
			  removeHelp(left, k);
		  }
	  }
	  
	  TMCell right = cell.getRight();
	  if (right != null) {
		  if (right.getKey().equals(k)) {
			  if (right.getLeft() != null) 
				  cell.setLeft(right.getLeft());
			  if (right.getRight() != null) 
				  cell.setRight(right.getRight());
			  right.setLeft(null);
			  right.setRight(null);
		  }
		  else {
			  removeHelp(right, k);
		  }
	  }  
  }
  
  private static int getSize(TMCell cell, int increment) {
	  if (cell.getLeft() != null) {
		  increment++;
		  increment+= getSize(cell.getLeft(), 0);
	  }
	  if (cell.getRight() != null) {
		  increment++;
		  increment+= getSize(cell.getRight(), 0);
	  }
	  if (cell.getRight() == null && cell.getLeft() == null) {
		  return increment;
	  }
	  return increment;
  }
  private static void makeArray(TMCell cell, TMCell [] a, int incr) {
	  if (cell.getLeft() != null) {
		  makeArray(cell.getLeft(), a, incr);  
	  }
	  a[incr++]= cell;
	  if (cell.getRight() != null) {
		  if (cell.getRight().getLeft() != null) {
			  makeArray(cell.getRight().getLeft(), a, incr);
		  }
		  a[incr++] = cell.getRight();
	  }
	  
  }
  private static void makeArr(TMCell cell, TMCell [] arra, int i) {
	  if (cell.getLeft() != null) {
		 makeArr(cell.getLeft(), arra, i);
	  }
	  arra[i++] = cell;
	  if (cell.getRight() != null) {
		  makeArr(cell.getRight(), arra, i);  
		  arra[i++]=cell.getRight();
	  } 
	  
  }
  private static TMCell findMin (TMCell cell) {
	  if (cell.getLeft() != null) {
		  return findMin(cell.getLeft());
	  }
	  return cell;
  }
  private static TMCell findMax (TMCell cell) {
	  if (cell.getRight() != null) {
		  return findMax(cell.getRight());
	  }
	  return cell;
  }

  private static TMCell removeRoot(TMCell cell) {
	  if (cell.getLeft() != null) {
		 TMCell newRoot = cell.getLeft();
		 while (newRoot.getRight() != null) {
			 newRoot = newRoot.getRight();
		 }
		 if (cell.getRight() != null) 
			 newRoot.setRight(cell.getRight());
		 if (cell.getLeft() != newRoot) 
			 newRoot.setLeft(cell.getLeft());
		 return cell.getLeft();
	  }
	  else if (cell.getRight() != null) {
		  TMCell newRoot = cell.getRight();
		  while (newRoot.getLeft() != null) {
			 newRoot = newRoot.getLeft();
		  }
		  if (cell.getRight() != newRoot) 
			  newRoot.setRight(cell.getRight());
		  if (cell.getLeft() != null) 
			  newRoot.setLeft(cell.getLeft());
		  return cell.getRight();
	  }
	  else {
		  cell.setKey(null);
		  cell.setValue(null);
		  return null;
	  }
  }
  private static void newRoot(TMCell root) {
	  if (root.getLeft() == null && root.getRight() == null) {
		  root = null;
	  }
	  else if (root.getLeft() != null) {
		  newRoot(root.getLeft());
	  }
	  else if (root.getRight() != null) {
		 // newRoot(root.getRight();)
	  }
  }
 }