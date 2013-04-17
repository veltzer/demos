package coreservlets.client;

import java.io.*;

public class RandomNumber implements Serializable {
  private double range = 0;
  private double value;
  private boolean isRangeLegal;

  public RandomNumber(String rangeString) {
    try {
      range = Double.parseDouble(rangeString);
      isRangeLegal = true;
    } catch(NumberFormatException nfe) {}
    if (range <= 0) {
      range = 1.0;
      isRangeLegal = false;
    }
    value = Math.random()*range;
  }
  
  //Need zero-arg constructor for IsSerializable
  
  public RandomNumber() {  
    this("-1");
  }
  
  public double getRange() {
    return (range);
  }
  
  public double getValue() {
    return (value);
  }

  public boolean isRangeLegal() {
    return (isRangeLegal);
  }
}
