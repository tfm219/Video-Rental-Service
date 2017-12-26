/*
CSE 17
Tyler Monaghan
tfm219
Program #5 DEADLINE: December 8, 2017
Program: WebRentz Movie Rental System
*/

import java.util.Date;
import java.text.SimpleDateFormat;

/** Abstract class representing a transaction (Request, Rental, or Return) */
public abstract class Transaction {
  protected Customer customer;
  protected Movie movie;
  protected Date date;
  SimpleDateFormat stdDate = new SimpleDateFormat("dd/MMM/yyyy");
  
  /** Constructor */
  public Transaction(Customer customer, Movie movie, Date date) {
    this.customer = customer;
    this.movie = movie;
    this.date = date;
  }
  
  /** Getter for customer */
  public Customer getCustomer() {
    return customer;
  }
  
  /** Getter for movie */
  public Movie getMovie() {
    return movie;
  }
  
  /* Getter for date */
  public Date getDate() {
    return date;
  }
  
  /** Abstract method for getting a string */
  public abstract String getMovieEventString();
}