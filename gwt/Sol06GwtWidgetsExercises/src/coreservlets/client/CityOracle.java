package coreservlets.client;

import com.google.gwt.user.client.ui.*;

public class CityOracle extends MultiWordSuggestOracle {
  // City names from
  // http://www.usatoday.com/weather/resources/climate/wusaclim.htm
  private static final String cityString =
    "Aberdeen,Abilene,Adak,Akron,Alamogordo,Alamosa,Albany,Albuquerque,Alexandria,Allentown," +
    "Bakersfield,Baltimore,Banner Elk,Barbers Point,Bar Harbor,Barre,Barrow,Baton Rouge";
  private static final String[] cityArray = cityString.split(",");
  
  public CityOracle() {
    for(String cityName: cityArray) {
      add(cityName);
    }
  }
}
