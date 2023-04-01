/*      Copyright 2002 Arizona Board of regents on behalf of
 *                  The University of Arizona
 *                     All Rights Reserved
 *         (USE & RESTRICTION - Please read COPYRIGHT file)
 *
 *  Version    : DEVSJAVA 2.7
 *  Date       : 08-15-02
 */


package EMPTicketMgmt;

import simView.*;


import java.lang.*;
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;

public class transducer extends  ViewableAtomic{
 protected double  arrived, solved;
 protected double observation_time;
 protected int numOfarrivinglowptickets = 0;
 protected int numOfFinishedtickets = 0;
 protected int numOfarrivinghighptickets = 0;
 public transducer(String  name,double Observation_time){
  super(name);
   addInport("in");
   addOutport("out");
  addInport("ariv");
  addInport("solved");
  observation_time = Observation_time;
  addTestInput("ariv",new entity("val"));
  addTestInput("solved",new entity("val"));
  initialize();
 }
 public transducer() {this("Ticket Progress Tracker", 100);}
 public void initialize(){
  phase = "active";
  sigma = observation_time;
  super.initialize();
 }
 public void  deltext(double e,message  x){
  Continue(e);
  entity  val;
  for(int i=0; i< x.size();i++){
    if(messageOnPort(x,"ariv",i)){
    	val = x.getValOnPort("ariv",i);
    	if(val.getName().startsWith("Low_Priority_T")) {
    		System.out.println(val.getName()+" arrived at time:"+this.getSimulationTime());
    		numOfarrivinglowptickets++;}
    	if(val.getName().startsWith("High_Priority_T")) {
    		System.out.println(val.getName()+" arrived at time:"+this.getSimulationTime());
        	numOfarrivinghighptickets++;}}
    if(messageOnPort(x,"solved",i)){
       val = x.getValOnPort("solved",i);
       if(val.getName().startsWith("Low_Priority_T")) {
   		System.out.println(val.getName()+" finished at time:"+this.getSimulationTime());}
	   if(val.getName().startsWith("High_Priority_T")) {
	   		System.out.println(val.getName()+" finished at time:"+this.getSimulationTime());}
	   numOfFinishedtickets++;}}}
 public void  deltint(){
  System.out.println("arriving tickets: "+(numOfarrivinglowptickets+numOfarrivinghighptickets)+"  finished tickets:"+numOfFinishedtickets);
  passivate();
 }
 public  message    out( ){
  message  m = new message();
  content  con = makeContent("out",new entity("stop"));
  m.add(con);
  return m;
 }

 public String getTooltipText(){
		return super.getTooltipText()+"\n number of low priority tickets arrived:"+numOfarrivinglowptickets+
				"\n number of high priority tickets arrived:"+numOfarrivinghighptickets
				+"\n number of tickets resolved:" + numOfFinishedtickets;
	} 
}
