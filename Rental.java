/*
CSE 17
Tyler Monaghan
tfm219
Program #5 DEADLINE: December 8, 2017
Program: WebRentz Movie Rental System
*/

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Represents a rental of an available movie to a customer. Subclass of transaction */
public class Rental extends Transaction {
  private int copiesRemaining;
  
  /** Constructor */
  public Rental(Customer customer, Movie movie, Date date) {
    super(customer, movie, date);
  }
  
  /** Setter for copiesRemaining */
  public void setCopiesRemaining(int copiesRemaining) {
    this.copiesRemaining = copiesRemaining;
  }
  
  /** Returns a message for a rental of a movie */
  public String getMovieEventString() {
    SimpleDateFormat stdDate = new SimpleDateFormat("dd-MMM-yyyy");
    return stdDate.format(date) + " Rented by " + customer.getName() + " (" + copiesRemaining + " copies remaining)";
  }
  
  /** Returns true if customer and movie are the same for two rental objects*/
  public boolean equals(Object o) {
    if (o instanceof Rental) {
      Rental other = (Rental)o;
      if (customer.equals(other.customer) && movie.equals(other.movie) && date.equals(other.date)) {
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