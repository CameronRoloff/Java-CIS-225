package com.company;

import com.company.object.Client;
import com.company.object.CookieCutter;
import com.company.object.Order;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ArrayList<CookieCutter> cookieCutAL = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();

        //declare the cookieCutter information object and put it inside arraylist.
        //these are set and given values before the program starts, so it's fine for them to be hardcoded.
        cookieCutAL.add(new CookieCutter("Nature",300,new int[]{10,15,20,25,30}));
        cookieCutAL.add(new CookieCutter("Tech",350,new int[]{20,30,40,50,60}));
        cookieCutAL.add(new CookieCutter("Business",375,new int[]{30,40,50,60,70}));
        cookieCutAL.add(new CookieCutter("Music",400,new int[]{85,95,110,130,210}));
        cookieCutAL.add(new CookieCutter("Naughty",500,new int[]{100,200,300,400,500}));

        String response = "y";
        int total;
        while(response.contains("y")){
            total = 0;
            response = "";

            //welcome the user
            System.out.println("Welcome to the Cookie Cutter program.");
            //System.out.println("If you're a new customer type the word 'new' if your user already exists, type the Customer ID");

            //leave this for later. This is useful for querying the saved data.
            /*if(sc.hasNextInt()){
                clientId = sc.nextInt();
                for(Client c : clients){
                    if(c.getClientId() == clientId;
                }
            }
            else{
                response = sc.nextLine();
            }*/


            System.out.println("What design would you like to set your Cookie Cutter as?");
            CookieCutter ccSelection = null;
            while(ccSelection == null) {

                //ask for what design of cookie cutter the person wants.
                String design = sc.nextLine();


                //finds the cookie cutter.
                boolean found = false;
                for (CookieCutter ccVal : cookieCutAL) {
                    if (ccVal.getDesign().equals(design)) {
                        ccSelection = ccVal;
                        found = true;
                        break;
                    }
                }
                if(found) break;
                else 
                    System.out.println("Please input a valid Cookie cutter design.");

                System.out.println();
            }

            //shows current price.
            System.out.println();
            total += ccSelection.getBasePrice();
            System.out.println("Current Cost: " + total);
            System.out.println();
            System.out.println();



            //get all features
            int[] featureCosts = ccSelection.getFeaturePrices();
            int line = 1;
            for(int cost: featureCosts)
            {
                System.out.printf("%d. Feature %d Price: %d$%n",line, line , cost);
                line += 1;
            }
            System.out.println();

            //shows features on screen
            System.out.printf("What feature would you like to choose for the cookie cutter? (1-%d)%n",featureCosts.length);
            int feature  = 0;
            //in the case this had names we probably would use a map.
            ArrayList<Integer> featureList = new ArrayList<>();

            boolean isNumber = false;
            //validates that the option is chosen for features
            while(feature == 0) {

                try{
                    feature = Integer.parseInt(sc.nextLine());
                    isNumber = true;
                }
                catch(Exception e){
                    isNumber = false;
                }
                //checks if number is a number
                if(!isNumber) {
                    System.out.println("Please input a number");
                    feature = 0;
                }
                else {
                    //if it's not in the range ask for another number in the range.
                  if( feature < 1 || feature > line) {
                      System.out.println("Please select a valid option.");
                      feature = 0;
                  } else {
                      //add price to features
                  featureList.add(feature);

                    //check if user wants to continue adding features.

                      System.out.println("Would you like to add another feature? (y/n)");
                      while(!(response.contains("y")  || response.contains("n"))){
                          response = sc.nextLine();
                          if (response.contains("y")) {

                              //get all features from list and prints
                              featureCosts = ccSelection.getFeaturePrices();
                              line = 1;
                              for(int cost: featureCosts)
                              {
                                  System.out.printf("%d. Feature %d Price: %d$%n",line, line , cost);
                                  line += 1;
                              }
                              System.out.println();

                              System.out.println("Please input another value from the list");
                              //repeats loop for features.
                              feature = 0;
                              //avoids future problems.
                              response = "";
                              break;
                          } else if (response.contains("n")) {
                              break;
                          }
                          else{
                              System.out.println("Would you like to add another feature? (y/n)");
                          }
                      }
                    }
                }
                System.out.println();
            }

            //shows current price.
            for(int featureNumber : featureList) {
                total += ccSelection.getFeaturePrice(featureNumber - 1);
            }
            System.out.println("Current Cost: " + total);
            System.out.println();
            System.out.println();

            //find the userid
            int maxId = 0;
            if(clients != null) {
                for (Client c : clients) {
                    int id = c.getClientId();
                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }
            //creates a client with max id + 1
            Client newClient = new Client(maxId + 1);
            //pops out the form for the new client
            newClient.getClientData();
            //adds the client to the list of current clients.
            clients.add(newClient);

            //find the userid
            maxId = 0;

            if(orders != null) {
                for(Order o: orders){
                    int id = o.getOrderId();
                    if(id > maxId){
                        maxId = id;
                    }
                }
            }
            //creates an order Id;
            Order newOrder = new Order(maxId + 1);
            newOrder.setCookieCutter(ccSelection);
            newOrder.setFeatures(featureList);
            //adds the order to the list of current orders.
            orders.add(newOrder);

            //print the total bill
            System.out.printf("Final Order Bill - Client ID: %d - Order Id: %d%n",newClient.getClientId(), newOrder.getOrderId());
            System.out.println("--------------------------------------------------");
            System.out.printf("Design %s - %d%n",newOrder.getCc().getDesign() ,newOrder.getCc().getBasePrice());
            for(int featureNumber : featureList) {
                System.out.printf("Feature %d - %d$%n",featureNumber,newOrder.getCc().getFeaturePrice(featureNumber));
            }
            //System.out.println("         "+total+"$");

            System.out.println("        -----------");
            System.out.println("           "+newOrder.calculateTotal()+"$");
            System.out.println("-------------------");

            System.out.println("Would you like to order again?");
            response = "";
            while(!(response.contains("y")  || response.contains("n"))){
                response = sc.nextLine();
                if(!(response.contains("y")  || response.contains("n")))
                    System.out.println("y/n ? ");
            }
            System.out.println();
            System.out.println();
            System.out.println();

        }
    }
}
