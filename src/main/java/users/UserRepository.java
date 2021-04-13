package users;

import javax.persistence.*;
import java.util.Optional;
import java.util.Scanner;

public class UserRepository {
    EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public void createNewUser() throws NullPointerException {
        System.out.println("Welcome to new user creator ");
        System.out.println();
        System.out.println("Type name and press enter ");
        String name = userChoice();
        System.out.println();
        System.out.println("Type surname and press enter ");
        String surname = userChoice();
        System.out.println();
        System.out.println("Type nationality and press enter ");
        String nationality = userChoice();
        System.out.println();
        System.out.println("Type age and press enter ");
        String age = userChoice();
        User newUser = new User(name, surname, nationality, age);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newUser);
        transaction.commit();
        System.out.println();
        System.out.println("New user created and added to data base ");
        printUser(newUser);
    }

    public void updateUserData() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        System.out.println("Select a user to update using an ID number and press enter ");
        long id = selectUserId();
        System.out.println();
        System.out.println("Select which value you want to update");
        System.out.println();
        System.out.println("If you want to update name type 1 and press enter ");
        System.out.println();
        System.out.println("If you want to update surname type 2 and press enter ");
        System.out.println();
        System.out.println("If you want to update nationality type 3 and press enter ");
        System.out.println();
        System.out.println("If you want to update age type 4 and press enter ");
        System.out.println();
        System.out.println("If you want to quit type 0 and press enter ");
        String userChoice = userChoice();
        String newData;
        switch (userChoice) {
            case "1":
                System.out.println("Enter updated name ");
                newData = userChoice();
                findById(id).setName(newData);
                transaction.commit();
                System.out.println("The data update was successful");
                System.out.println("Thank You for using my program ");
                break;
            case "2":
                System.out.println("Enter updated surname ");
                newData = userChoice();
                findById(id).setSurname(newData);
                transaction.commit();
                System.out.println("The data update was successful");
                System.out.println("Thank You for using my program ");
                break;
            case "3":
                System.out.println("Enter updated nationality ");
                newData = userChoice();
                findById(id).setNationality(newData);
                transaction.commit();
                System.out.println("The data update was successful");
                System.out.println("Thank You for using my program ");
                break;
            case "4":
                System.out.println("Enter updated age ");
                newData = userChoice();
                findById(id).setAge(newData);
                transaction.commit();
                System.out.println("The data update was successful");
                System.out.println("Thank You for using my program ");
                break;
            case "0":
                System.out.println("Thank You for using my program ");
                transaction.commit();
                break;
            default:
                System.out.println("Wrong number ,try again");
                transaction.commit();
                updateUserData();
        }
    }

    public void deleteUser() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        System.out.println("Using ID select user to remove from database ");
        long id = selectUserId();
        em.remove(findById(id));
        transaction.commit();
        System.out.println("User deleted ");
    }

    public User findById(long id) {
        return em.find(User.class, id);
    }

    public String findByOptionalId(long id) {
        User user = findById(id);
        String result;
        Optional<User> optionalUser = Optional.ofNullable(user);
        if (optionalUser.isEmpty()) {
            result = "No such ID in the database, please select again";
        } else {
            result = optionalUser.get().getName() + " " + optionalUser.get().getSurname() +
                    " " + optionalUser.get().getNationality() + " " + optionalUser.get().getAge();
        }
        return result;
    }

    public void showAllUsers() {
        long id = 1;
        Optional<User> optionalUser;
        do {
            User user = findById(id);
            optionalUser = Optional.ofNullable(user);
            optionalUser.stream()
                    .sorted()
                    .forEach(System.out::println);
            id++;
        } while (optionalUser.isPresent());
    }

    public static String userChoice() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static long selectUserId() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLong();
    }

    public static void printUser(User user) {
        System.out.println(user);
    }
}
