package EMPTicketMgmt;


import GenCol.*;
import genDevs.modeling.*;
import genDevs.simulation.*;
import genDevs.simulation.realTime.*;


public class test{

protected static digraph testDig;

  public test(){}

  public static void main(String[ ] args)
  {
	  testDig = new ticketMgmtSys();
      genDevs.simulation.coordinator cs = new genDevs.simulation.coordinator(testDig);
      cs.initialize();
      cs.simulate(50000);
  }
}
