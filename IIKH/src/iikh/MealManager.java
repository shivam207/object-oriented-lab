package iikh;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Scanner;

public class MealManager {
	
	public static void manager(){
		System.out.println("1 Add Meal ");
		System.out.println("2 Remove Meal");
		System.out.println("3 View ");
		System.out.println("4 Return ");
		
		MealManager mng = new MealManager();
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		switch (choice){
		case 1:
			String name;
			String recipes;
			System.out.println("Enter Name of the meal : ");
			name=sc.next();
			
			Database.viewRecipie();
			System.out.println("\nEnter the Recipes (sepereated by comma, from above list) :");
			recipes=sc.next();
			String[] recipesList = recipes.split(",");	
			mng.addMeal(name, recipesList);
			System.out.println("\nStatus : Meal Added\n");
			break;
		case 2:
			System.out.println("Enter the name of meal : ");
			String name1=sc.next();
			mng.removeMeal(name1);
			System.out.println("Status : "+name1+" meal removed.\n");
			break;
		case 3:
			MealManager.viewMeal();
			break;
		case 4:
			Welcome.welcome();
			break;
			
		}
		Welcome.welcome();
		sc.close();
	}
	
	
	private String parsetoString(String mealName, String[] recipes){
        String out = mealName + ",,";
        for(String x: recipes)
            out += (x + "::");
      //  System.out.print( out + "vsd");
        return out;
    }
    public void addMeal(String mealName, String[] recipes){
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mealList.txt", true)))) {
            out.println(parsetoString(mealName, recipes));  
            }catch (IOException e) {
            e.printStackTrace();
            //exception handling left as an exercise for the reader
        }
     //   System.out.print("ksadlvbw");
    }  
    public void removeMeal(String mealName ){
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("mealList.txt");
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
                    if (!tokens[0].equals(mealName)) {  
                        // update content as it is
                        fileContent.append(strLine);
                        fileContent.append("\n");
                    }
                }
            }
            // Now fileContent will have updated content , which you can override into file
            FileWriter fstreamWrite = new FileWriter("mealList.txt");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            //Close the input stream
            br.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static void viewMeal(){
    	try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("mealList.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            System.out.println("\n Meals in the Database \n ");
            System.out.println(String.format("%-20s  %s" , "Meal Name", "Recipes" ));
            //Read File Line By Line
            
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
              //  System.out.println(strLine);
                String tokens[] = strLine.split(",,");
                System.out.println(String.format("%-20s  %s" , tokens[0], tokens[1] ));
                
            }
            br.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
