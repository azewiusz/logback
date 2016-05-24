
package handlers;

import org.slf4j.Logger;

public abstract class Loggable
  {
    public abstract  Logger getLOG() ;
    public abstract Logger setLOG(Logger log);
  }
