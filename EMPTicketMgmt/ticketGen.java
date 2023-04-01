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
import util.*;
import statistics.*;

public class ticketGen extends ViewableAtomic{


  protected double int_gen_time;
  protected int count;
  protected rand r;

  public ticketGen() {this("ticketGen", 7);}

public ticketGen(String name,double period){
   super(name);
   addInport("in");
   addOutport("out");

   int_gen_time = period ;
}

public void initialize(){
   holdIn("active", int_gen_time);
   r = new rand(123987979);
   count = 0;
}


public void  deltext(double e,message x)
{
Continue(e);
   for (int i=0; i< x.getLength();i++){
     if (messageOnPort(x, "in", i)) { //the stop message from tranducer
       passivate();
     }
   }
}


public void  deltint( )
{

if(phaseIs("active")){
   count = count +1;
//   holdIn("active",int_gen_time);
   holdIn("active",6+r.uniform(int_gen_time));
}
else passivate();
}

public message  out( )
{
//System.out.println(name+" out count "+count);
   message  m = new message();
//   content con = makeContent("out", new entity("car" + count));
   content con = makeContent("out", new ticketEntity("ticket" + count, 5+r.uniform(20), 1));
   m.add(con);

  return m;
}


}
