package coreservlets.client;

/** Some helper utilities for making Strings describing customers. */

public class ResultUtils {
  /** makeBulletedList returns an HTML ul list describing the Customer,
   *  suitable for inserting into the page. 
   */
  
  public static String makeBulletedList(Customer c) {
    String list =
      "<ul>" +
      makeBullet("First name", c.getFirstName()) +
      makeBullet("Last name", c.getLastName()) +
      makeBullet("Balance", c.getFormattedBalance()) +
      "</ul>";
    return(list);
  }
  
  private static String makeBullet(String prompt, 
                                   String value) {
    return("<li>" + prompt + ": " + value + "</li>");
  }
}
