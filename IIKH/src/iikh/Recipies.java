package iikh;

import java.util.Scanner;


public class Recipies {
	public static void recipies()
	{
		System.out.println("1 Add recipie:");
		System.out.println("2 View Recipies:");
		System.out.println("3 Delete Recipie:");
		System.out.println("4 Return");
		System.out.println("\n Enter your choice: ");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		Database db= new Database();
		
		switch (choice){
		case 1:
			String name;
			String ingr;
			System.out.println("Enter Name of the recipe : ");
			name=sc.next();
			System.out.println("\nEnter the Ingredients (sepereated by comma) :");
			ingr=sc.next();
			System.out.println(name+ingr);
			String[] ingredients = ingr.split(",");	
			db.addRecipe(name, ingredients);
			break;
		case 2:
			Database.viewRecipie();
			break;
		case 3:
			System.out.println("Enter the name of the recipe to delete: ");
			String name1 = sc.next();
			db.removeRecipe(name1);
			break;
		case 4:
			Welcome.welcome();
			break;
			
		}
		Welcome.welcome();
		sc.close();
	}
}
