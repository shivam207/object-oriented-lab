package iikh;

import java.util.Scanner;
import java.io.*;

public class PlanManager {
	public static void manager(){
		System.out.println("1 Add Plan ");
		System.out.println("2 Remove Plan");
		System.out.println("3 View ");
		System.out.println("4 Return ");
		
		PlanManager mng = new PlanManager();
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		switch (choice){
		case 1:
			String name;
			String meals;
			System.out.println("Enter Name of the Plan : ");
			name=sc.next();
			
			MealManager.viewMeal();
			System.out.println("\nEnter the Meals (sepereated by comma format(Breakfast,Lunch,Dinner), from above list) :");
			meals=sc.next();
			String[] mealList = meals.split(",");	
			mng.addPlan(name, mealList);
			System.out.println("\nStatus : Plan Added\n");
			break;
		case 2:
			System.out.println("Enter the name of Plan : ");
			String name1=sc.next();
			mng.removePlan(name1);
			System.out.println("Status : "+name1+" plan removed.\n");
			break;
		case 3:
			PlanManager.viewPlan();
			break;
		case 4:
			Welcome.welcome();
			break;
			
		}
		Welcome.welcome();
		sc.close();
	}
	
	private String parsetoString(String planName, String[] meals){
        String out = planName + ",,";
        for(String x: meals)
            out += (x + "::");
      //  System.out.print( out + "vsd");
        return out;
    }
    public void addPlan(String planName, String[] meals){
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planList.txt", true)))) {
            out.println(parsetoString(planName, meals));  
        }catch (IOException e) {
            e.printStackTrace();
            //exception handling left as an exercise for the reader
        }
    }
    public void removePlan(String planName ){
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("planList.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            StringBuilder fileContent = new StringBuilder();
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
              //  System.out.println(strLine);
                String tokens[] = strLine.split(",,");
                if (tokens.length > 0) {
                    // Here tokens[0] will have value of ID
                    if (!tokens[0].equals(planName)) {  
                        // update content as it is
                        fileContent.append(strLine);
                        fileContent.append("\n");
                    }
                }
            }
            // Now fileContent will have updated content , which you can override into file
            FileWriter fstreamWrite = new FileWriter("planList.txt");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            br.close();
            //Close the input stream
           // in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static void viewPlan(){
    	try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("planList.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            //Read File Line By Line
            System.out.println("\n Plans in the Database \n ");
            System.out.println(String.format("%-20s  %-10s %-10s %-10s\n" , "Plan Name", "Breakfast", "Lunch", "Dinner"));
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
              //  System.out.println(strLine);
                String tokens[] = strLine.split(",,");
                String meals[] = tokens[1].split("::");
                System.out.println(String.format("%-20s  %-10s %-10s %-10s" , tokens[0], meals[0], meals[1], meals[2]));
                
            }
            br.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}

