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

import java.awt.*;
import java.io.*;
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;


public class ticketMgmtSys extends ViewableDigraph{
public ticketMgmtSys(){
    this("Employee Ticket Management System");
}
public ticketMgmtSys(String nm){
    super(nm);
    ticketMgmtSysConstruct();
}
public void ticketMgmtSysConstruct(){
    this.addOutport("out");
    ViewableAtomic lowp_genr = new lowPriorityGen("Low Priority Ticket Generator",10);
    ViewableAtomic highp_genr = new highPriorityGen("High Priority Ticket Generator",21);
    ViewableAtomic ticket_center = new ticketCenter("Ticker Center");
    ViewableAtomic trand = new transducer ();
     add(lowp_genr);
     add(ticket_center);
     add(highp_genr);
     add(trand);
     addCoupling(lowp_genr,"out",ticket_center,"lowPIn");
     addCoupling(highp_genr,"out",ticket_center,"highPIn");
     addCoupling(ticket_center,"out",this,"out");
     addCoupling(lowp_genr,"out",trand,"ariv");
     addCoupling(highp_genr,"out",trand,"ariv");
     addCoupling(ticket_center,"out",trand,"solved");
}
    public void layoutForSimView()
    {
        preferredSize = new Dimension(925, 521);
        if((ViewableComponent)withName("High Priority Ticket Generator")!=null)
             ((ViewableComponent)withName("High Priority Ticket Generator")).setPreferredLocation(new Point(72, 108));
        if((ViewableComponent)withName("Ticker Center")!=null)
             ((ViewableComponent)withName("Ticker Center")).setPreferredLocation(new Point(426, 280));
        if((ViewableComponent)withName("Low Priority Ticket Generator")!=null)
             ((ViewableComponent)withName("Low Priority Ticket Generator")).setPreferredLocation(new Point(70, 292));
        if((ViewableComponent)withName("Ticket Progress Tracker")!=null)
             ((ViewableComponent)withName("Ticket Progress Tracker")).setPreferredLocation(new Point(382, 125));
    }
}
