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

public class ticketCenter extends ViewableAtomic{
double lowPServingTime = 3;
double highPServingTime =5;
entity ticket,currentJob = null;
protected DEVSQueue q;
public ticketCenter() {this("Ticker Center");}
public ticketCenter(String name){
    super(name);
    addInport("lowPIn");
    addInport("highPIn");
    addOutport("out");
    addTestInput("lowPIn",new entity("testlowP"));
    addTestInput("highPIn",new entity("testhighP"),5);}
public void initialize(){
     q = new DEVSQueue();
     passivate();}
public void  deltext(double e,message x)
{Continue(e);
if(phaseIs("passive")){
	for (int i = 0; i < x.getLength(); i++) {
		if (messageOnPort(x, "lowPIn", i)) {
			ticket = x.getValOnPort("lowPIn", i);
			currentJob= ticket;
			holdIn("active", lowPServingTime);}}
	for (int i = 0; i < x.getLength(); i++) {
		if(messageOnPort(x, "highPIn", i)) {
			ticket = x.getValOnPort("highPIn", i);
			currentJob=ticket;
			holdIn("active", highPServingTime);}}}
else if(phaseIs("active")){
    for (int i=0; i< x.getLength();i++){
      if (messageOnPort(x, "lowPIn", i)) {
        ticket = x.getValOnPort("lowPIn", i);
        q.addLast(ticket);}
      if (messageOnPort(x, "highPIn", i)) {
          ticket = x.getValOnPort("highPIn", i);
          q.addFirst(ticket);}}}}
public void  deltint( )
{if(phaseIs("active")){
   if(!q.isEmpty()) {
     currentJob = (entity)q.first();   
     holdIn("active",((ticketEntity)currentJob).getProcessingTime());
     q.remove();}
   else passivate();}}
public message  out( )
{message  m = new message();
   content con = makeContent("out",
            new entity(currentJob.getName()));
   m.add(con);
  return m;

}

public String getTooltipText(){
	if(currentJob!=null)
	return super.getTooltipText()+"\n number of tickets in queue:"+q.size()+
	"\n my current job is:" + currentJob.toString();
	else return "initial value";
}



}

