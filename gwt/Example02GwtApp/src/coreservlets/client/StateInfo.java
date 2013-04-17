package coreservlets.client;

public class StateInfo {
  private String stateName;
  private String[] cities;

  public StateInfo(String stateName, String[] cities) {
    this.stateName = stateName;
    this.cities = cities;
  }
  
  public String getStateName() {
    return(stateName);
  }
  
  public String[] getCities() {
    return(cities);
  }
  
  private static String[] mdCities =
    {"Baltimore", "Frederick","Gaithersburg","Rockville"};
  private static String[] vaCities =
    {"Virginia Beach","Norfolk","Chesapeake","Arlington"};
  private static String[] paCities =
    {"Philadelphia","Pittsburgh","Allentown","Erie"};
  private static String[] njCities =
    {"Newark", "Jersey City","Paterson","Elizabeth"};
  private static String[] nyCities =
    {"New York", "Buffalo","Rochester","Yonkers"};
  private static StateInfo[] nearbyStates = 
    { new StateInfo("Maryland", mdCities),
      new StateInfo("Virginia", vaCities),
      new StateInfo("Pennsylvania", paCities),
      new StateInfo("New Jersey", njCities),
      new StateInfo("New York", nyCities) 
    };

  public static StateInfo[] getNearbyStates() {
    return(nearbyStates);
  }
  
  public static String[] findCities(StateInfo[] states, 
                                    String stateName) {
    for(StateInfo state: states) {
      if(state.getStateName().equals(stateName)) {
        return(state.getCities());
      }
    }
    String[] unknown = {"Unknown state"};
    return(unknown);
  }
}
