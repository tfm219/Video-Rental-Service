/*
CSE 17
Tyler Monaghan
tfm219
Program #5 DEADLINE: December 8, 2017
Program: WebRentz Movie Rental System
*/

import java.util.*;

/**Class creates movie obejects for a video store. */
public class Movie {
  private int id;
  private String title;
  private int numCopies;
  private int numAvailable;
  private MyQueue<Customer> waitList;
  private MyLinkedList<Rental> currentRentals;
  private ArrayList<Transaction> rentalHistory;
  
  /**  The constructor. Initializes all fields, including setting numAvailable to the same value as numCopies. */
  public Movie(int id, String title, int numCopies) {
    this.id = id;
    this.title = title;
    this.numCopies = numCopies;
    numAvailable = numCopies;
    waitList = new MyQueue<>();
    currentRentals = new MyLinkedList<>();
    rentalHistory = new ArrayList<>();
  }
  
  /** Return the id of the movie */
  public int getId() {
    return id;
  }
  
  /** Return the title of the movie */
  public String getTitle() {
    return title;
  }
  
  /** Return the number of copies of the movie */
  public int getNumCopies() {
    return numCopies;
  }
  
  /** Return the number available of the movie */
  public int getNumAvailable() {
    return numAvailable;
  }
  
  /** Adds a customer to the waitlist Queue for the movie */
  public void addToWaitList(Customer customer) {
    waitList.enqueue(customer);
  }
  
  /** Process a rental transaction */
  public void processRental(Rental rentalTrans) {
    numAvailable--;
    rentalTrans.setCopiesRemaining(numAvailable);
    currentRentals.add(rentalTrans);
    rentalHistory.add(rentalTrans);
  }
  
  /** Process a return transaction */
  public void processReturn(Return returnTrans) {
    for (int i = 0; i < currentRentals.size; i++) {
      if (returnTrans.customer.equals(currentRentals.get(i).customer) && returnTrans.movie.equals(currentRentals.get(i).movie)){
        currentRentals.remove(i);
        numAvailable++;
        //rentalTrans.setCopiesRemaining(numAvailable);
        rentalHistory.add(returnTrans);
        break;
      }
    }
    /*if(currentRentals.contains(returnTrans)) {
     currentRentals.remove(indexOf(returnTrans));
     numAvailable++;
     rentalTrans.setCopiesRemaining(numAvailable);
     rentalHistory.add(returnTrans);              
     }*/
  }
  
  /** Removes next customer from waitlist and returns that customer. returns null if waitlist is empty */
  public Customer getNextCustomerFromWaitList() {
    if (waitList.getSize() > 0) {
      return waitList.dequeue();
    }
    else {
      return null;
    }
  }
  
  /** Prints a detailed status of the movie. Includes rental history and current waitlist */
  public void printMovieDetails() {
    System.out.println("ID: " + id + " \tMovie: " + title + " (" + numCopies + " total copies)");
    System.out.println("\tRental History\n\t---------------");
    if (rentalHistory.size() > 0) {
      for(int i = 0; i < rentalHistory.size(); i++) {
        System.out.print("\t");
        for (int j = 0; j < currentRentals.size; j++) {
          if (rentalHistory.get(i).customer.equals(currentRentals.get(j).customer) && rentalHistory.get(j).movie.equals(currentRentals.get(j).movie)) {
            System.out.print("* ");
            break;
          }
          else if (j == currentRentals.size - 1) {
            System.out.print("  ");
          }
        }
        System.out.println(rentalHistory.get(i).getMovieEventString());
      }
    }
    else {
      System.out.println("\tn/a");
    }
    System.out.println("\n\tWait List\n\t---------");
    if (waitList.getSize() > 0) {
      for(int i = 0; i < waitList.getSize(); i++) {
        System.out.println("\t#" + (i + 1) + ": " + waitList.peekIndex(i).getName());
      }
    }
    else {
      System.out.println("\tn/a");
    }
    System.out.println();
  }
  
  /** Overrides equals method. equal if they have the same id number */
  public boolean equals(Object o) {
    if (o instanceof Movie) {
      Movie other = (Movie)o;
      if (id == other.id) {
        return true;
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }
}