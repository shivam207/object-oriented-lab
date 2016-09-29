package iikh;
import java.util.*;

public class Welcome {
	public static void welcome(){
		System.out.println("Welcome to IIKH");
		System.out.println("1 Meal Manager ");
		System.out.println("2 Plan Manager");
		System.out.println("3 Recipies ");
		System.out.println("4 Exit");
		
		//read choice of the user
		System.out.println("\nEnter your choice : ");
		int y;
		Scanner sc = new Scanner(System.in);
		y = sc.nextInt();
		
		switch (y){
			case 1:
				MealManager.manager();
				break;
			case 2:
				PlanManager.manager();
			case 3:
				Recipies.recipies();
				break;
			case 4:
				System.exit(0);
		}
		sc.close();
		
	}
	
}
