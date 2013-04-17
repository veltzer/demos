package coreservlets.client;

import com.google.gwt.core.client.JavaScriptObject;

public class JsCircle extends JavaScriptObject implements Circle {
  // Overlay types must have protected, zero-arg constructors
  protected JsCircle() {}
  
  public final native double getRadius() /*-{ 
    return this.radius; 
  }-*/;
  
  public final native void setRadius(double r) /*-{ 
    this.radius = r; 
  }-*/;
  
  public final double getArea() {
    return(Math.PI * Math.pow(getRadius(), 2));
  }
  
  // GWT 2.0 supports many Java 6 constructs,
  // but not String.format. So, using explicit
  // String concatenation below.
  
  public final String getInfo() {
    String info =
      "Radius=" + getRadius() + ", " +
      "Area=" + getArea();
    return(info);
  }
}
