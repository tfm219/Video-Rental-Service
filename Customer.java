/*
CSE 17
Tyler Monaghan
tfm219
Program #5 DEADLINE: December 8, 2017
Program: WebRentz Movie Rental System
*/

/** This class creates customer objects for a video store */
public class Customer {
  private String name;
  private int id;
  
  /** Constructor sets id and name */
  public Customer(int id, String name) {
    this.id = id;
    this.name = name;
  }
  
  /** Getter for id */
  public int getId() {
    return id;
  }
  
  /** Getter for name */
  public String getName() {
    return name;
  }
  
  /** Returns true if two customer objects have same id number */
  public boolean equals(Object o) {
    if (o instanceof Customer) {
      Customer other = (Customer)o;
      if (id == other.id) {
        return true;
      }
      else {
        return false;
      }
    }
    else 
      return false;
  }
}