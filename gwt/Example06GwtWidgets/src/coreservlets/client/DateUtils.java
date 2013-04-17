package coreservlets.client;

import java.util.*;

public class DateUtils {
  @SuppressWarnings("deprecation") // Cannot avoid this in GWT since 
                                   // Calendar is not supported
  public static Date datePlusWeek(Date oldDate) {
    Date newDate = 
      new Date(oldDate.getYear(), 
               oldDate.getMonth(),
               oldDate.getDate() + 7,
               oldDate.getHours(),
               oldDate.getMinutes(),
               oldDate.getSeconds());
    return(newDate);
  }
  
  private DateUtils() {} // Uninstantiable class

}
