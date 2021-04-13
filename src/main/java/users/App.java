package users;

import javax.persistence.*;
import java.util.Scanner;

public class App {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static final EntityManager em = factory.createEntityManager();

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        UserRepository userRepository = new UserRepository(em);
        System.out.println("------------WELCOME---------------");
        System.out.println();
        System.out.println("This is the Users data base");
        System.out.println();
        System.out.println("If you want to create new user type 1 and press enter :");
        System.out.println();
        System.out.println("If you want to find user by ID type 2 and press enter :");
        System.out.println();
        System.out.println("If you want to update user data type 3 and press enter :");
        System.out.println();
        System.out.println("If you want to delete user type 4 and press enter :");
        System.out.println();
        System.out.println("If you want to see all users list type 5 and press enter :");
        System.out.println();
        System.out.println("If you want to quit type 0 and press enter :");
        System.out.println();
        String userChoice = userChoice();
        switch (userChoice) {
            case "1":
                userRepository.createNewUser();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                run();
                break;
            case "2":
                System.out.println("Type user ID that you want to print");
                System.out.println(userRepository.findByOptionalId(selectUserId()));
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                run();
                break;
            case "3":
                userRepository.updateUserData();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                run();
                break;
            case "4":
                userRepository.deleteUser();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                run();
                break;
            case "5":
                userRepository.showAllUsers();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                run();
                break;
            case "0":
                System.out.println("Thank you for using my program ");
                break;
            default:
                System.out.println("You chose the wrong number");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                run();
        }
    }

    public static String userChoice() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static long selectUserId() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLong();
    }

}


