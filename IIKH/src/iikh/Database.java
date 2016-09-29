package iikh;
import java.io.*;

public class Database {
	private String parsetoString(String recipeName, String[] indegrients){
        String out = recipeName + ",,";
        for(String x: indegrients)
            out += (x + "::");
        return out;
    }
	
	public void addRecipe(String recipeName, String[] indegrients){
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("recipeList.txt", true)))) {
            out.println(parsetoString(recipeName, indegrients));
            System.out.println("\nRecipe added\n");
            Welcome.welcome();
        }catch (IOException e) {
            e.printStackTrace();
            //exception handling left as an exercise for the reader
        }
	}
    public void removeRecipe(String RecipeName ){
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("recipeList.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            StringBuilder fileContent = new StringBuilder();
            while ((strLine = br.readLine()) != null) {

                String tokens[] = strLine.split(",,");
                if (tokens.length > 0) {
                    // Here tokens[0] will have value of ID
                    if (!tokens[0].equals(RecipeName)) {  
                        // update content as it is
                        fileContent.append(strLine);
                        fileContent.append("\n");
                    }
                }
            }
            System.out.println("\nRecipe Deleted\n");
            // Now fileContent will have updated content , which you can override into file
            FileWriter fstreamWrite = new FileWriter("recipeList.txt");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            br.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static void viewRecipie(){
    	try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("recipeList.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            //Read File Line By Line
            System.out.println("\n Recipes in the Database \n ");
            System.out.println(String.format("%-20s  %s" , "Recipe Name", "Ingredients" ));
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
              //  System.out.println(strLine);
                String tokens[] = strLine.split(",,");
                System.out.println(String.format("%-20s  %s" ,tokens[0], tokens[1]));
            }
            br.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
    }
}
}
