package EMPTicketMgmt;

import GenCol.*;

public class ticketEntity extends entity{
  protected double processingTime;
  protected int priority;
  
  public ticketEntity(){
	  this("ticket", 10, 1);
  }
  
  public ticketEntity(String name, double _procTime,int _priority){
	  super(name);
	  processingTime = _procTime;
	  priority = _priority;
  }
	
  public double getProcessingTime(){
	  return processingTime;
  }
 
  
  public int getPriority(){
	  return priority;
  }
  
  public String toString(){
	  return name+" Processing_time_"+(double)((int)(processingTime*100))/100;
  }
		
}
