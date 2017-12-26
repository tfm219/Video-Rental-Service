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

/** Class represents returns for movies for a video rental company */
public class Return extends Transaction {
  
  /** Constructor */
  public Return(Customer customer, Movie movie, Date date) {
    super(customer, movie, date);
  }
  
  /** Returns message for returned movie */
  public String getMovieEventString() {
    SimpleDateFormat stdDate = new SimpleDateFormat("dd-MMM-yyyy");
    return stdDate.format(date) + " Returned by " + customer.getName();
  }
  
  /** Returns true if customer movie and date are the same for two return objects */
  public boolean equals(Object o) {
    if (o instanceof Return) {
      Return other = (Return)o;
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