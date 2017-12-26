/*
CSE 17
Tyler Monaghan
tfm219
Program #5 DEADLINE: December 8, 2017
Program: WebRentz Movie Rental System
*/

import java.util.Date;
import java.text.SimpleDateFormat;


/** Represents a customer's request for a movie. subclass of Transaction */
public class NewRequest extends Transaction {
  
  /** Constructor */
  public NewRequest(Customer customer, Movie movie, Date date) {
    super(customer, movie, date);
  }
  
  /** Returns message for a movie request */
  public String getMovieEventString() {
    SimpleDateFormat stdDate = new SimpleDateFormat("dd-MMM-yyyy");
    return stdDate.format(date) + " Requested by " + customer.getName();
  }
  
  /** Returns true if they have the same customer movie and date for two request objects */
  public boolean equals(Object o) {
    if (o instanceof NewRequest) {
      NewRequest other = (NewRequest) o;
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
