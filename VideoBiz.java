/*
CSE 17
Tyler Monaghan
tfm219
Program #5 DEADLINE: December 8, 2017
Program: WebRentz Movie Rental System
*/

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/** Represents the WebRentz business */
public class VideoBiz {
  ArrayList<Movie> movies;
  ArrayList<Customer> customers;
  
  /** Constructor, reads customers and movies files and enters the information from them */
  public VideoBiz() throws FileNotFoundException{
    customers = new ArrayList<>();
    movies = new ArrayList<>();
    try {
      File customerFile =  new File("customers.txt");
      Scanner input = new Scanner(customerFile);
      input.useDelimiter("[\t]|[\n\r\f]+");
      while (input.hasNext()) {
        customers.add(new Customer(input.nextInt(), input.next()));
      }
      File movieFile = new File("movies.txt");
      input = new Scanner(movieFile);
      input.useDelimiter("[\t]|[\n\r\f]+");
      while (input.hasNext()) {
        movies.add(new Movie(input.nextInt(), input.next(), input.nextInt()));
      }
    }catch (InputMismatchException ex) {
      System.out.println("ERROR: Incorrect input");
    }catch(NoSuchElementException ex) {
      System.out.println("ERROR: Incorrect input");
    }
  }
  
  /** Processes a request for a movie. Rents the movie out if available, puts customer on waitlist if it isn't */
  public void processNewRequest(NewRequest requestTrans) {
    if (requestTrans.movie.getNumAvailable() > 0) {
      requestTrans.movie.processRental(new Rental(requestTrans.customer, requestTrans.movie, requestTrans.date));
    }
    else {
      requestTrans.movie.addToWaitList(requestTrans.customer);
    }
  }
  
  /** Processes a return. Adds request for next customer on the waitlist. */
  public void processReturn(Return returnTrans) {
    returnTrans.movie.processReturn(returnTrans);
    processNewRequest(new NewRequest(returnTrans.movie.getNextCustomerFromWaitList(), returnTrans.movie, returnTrans.getDate()));
  }
  
  /** Processes the transaction file for New Requests and Returns */
  public void processTransactionFile(File transFile) throws FileNotFoundException {
     SimpleDateFormat stdDate = new SimpleDateFormat("dd-MMM-yyyy");
     try {
      Scanner input = new Scanner(transFile);
      while (input.hasNext()) {
        Date date = stdDate.parse(input.next());
        String transType = input.next();
        if (transType.equals("N")) {
          processNewRequest(new NewRequest(getCustomerById(input.nextInt()), getMovieById(input.nextInt()), date));
          //System.out.println("New " + stdDate.format(date) + " " +  input.nextInt() + " " + input.nextInt());
        }
        else if (transType.equals("R")) {
          processReturn(new Return(getCustomerById(input.nextInt()), getMovieById(input.nextInt()), date));
        //System.out.println("Return " + stdDate.format(date) + " " +  input.nextInt() + " " + input.nextInt());
        }
        else {
          input.nextLine();
        }
      }
     }catch(ParseException ex) {
       System.out.println("Parse");
     }catch (InputMismatchException ex) {
       System.out.println("Mismatch");
     }catch(NoSuchElementException ex) {
       System.out.println("No Such Element");
     }
  }
  
  /** Returns the customer based on the given customer id number. returns null if there is no customer with that id */
  public Customer getCustomerById(int id) {
    for(int i = 0; i < customers.size(); i++) {
      if(id == customers.get(i).getId()) {
        return customers.get(i);
      }
    }
    return null;
  }
  
  /** Returns movie based on given movie id number, Returns null if there is no movie with that id number */
  public Movie getMovieById(int id) {
     for(int i = 0; i < movies.size(); i++) {
      if(id == movies.get(i).getId()) {
        return movies.get(i);
      }
    }
     return null;
  }
  
  /** Prints the movie report. Prints all rentals and returns as well as the waitlist for each movie */
  public void printMovieReport() {
    for (int i = 0; i < movies.size(); i++) {
      movies.get(i).printMovieDetails();
    }
  }
  
  /** Creates new VideoBiz store. If there is one command line arg, it uses that as the transaction file, if not it
    * terminates. Also terminates if file is not found. If it doesn't terminate, it prints the Movie report */
  public static void main(String[] args) {
    if (args.length == 1) {
      File file = new File(args[0]);
      try {
        VideoBiz store = new VideoBiz();
        store.processTransactionFile(file);
        store.printMovieReport();
      }catch(FileNotFoundException ex) {
        System.out.println("ERROR: File not found");
      }
    }
    else {
      System.out.println("ERROR: Incorrect number of command line arguments");
    }
  }
}
  
    
    