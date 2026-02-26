package com.deepu;
import java.util.List;
import java.util.Scanner;

public class DigitalcomProcess {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        User user = new User();
        GameService service = new GameService();

        int choice;
        do {
            System.out.println("\n===== DigitalCom Customer Portal =====");
            System.out.println("1) RegisterUser");
            System.out.println("2) Login");
            System.out.println("3) ViewGames");
            System.out.println("4) searchByName");
            System.out.println("5) Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
            case 1:
                System.out.print("Enter User ID: ");
                String userid = sc.nextLine();

                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                user.addUser(userid, password);
                break;

            case 2:
                List<Game> games = service.viewAll();
                System.out.println("\nAvailable Games:");
                for (Game g : games) {
                    System.out.println(g);
                }
                break;

            case 3:
                System.out.print("Enter Author Name to Search: ");
                String author = sc.nextLine();

                String result = service.authorSearch(author);
                System.out.println("Result: " + result);
                break;

            case 4:
                System.out.println("Exiting... Thank You!");
                break;

            default:
                System.out.println("Invalid Choice! Please try again.");
        }

    } while (choice != 4);

    sc.close();
    }
}