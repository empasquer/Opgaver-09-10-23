package MyFriends;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private ArrayList<Friend> friends;
    private File file = new File("MyFriends//friendslist.txt");

    Main() {
        friends = new ArrayList<Friend>();
        saveFriendList();
        loadFriendList();
    }

    public static void main(String[] args) {
        new Main().run();
    }

    void run() {
        boolean running;
        Menu menu = new Menu("MENU:", "Please choose option: ", new String[]{"1. Show list of friends", "2. Enter new friend", "3. Delete friend", "4. Save list", "5. Load list", "9. Quit"});
        loadFriendList();
        running = true;
        while (running) {
            menu.printMenu();
            int userChoice = menu.readChoice();
            switch (userChoice) {
                case 1:
                    viewFriendList();
                    break;
                case 2:
                    createNewFriend();
                    break;
                case 3:
                    deleteFriend();
                    break;
                case 4:
                    saveFriendList();
                    break;
                case 5:
                    loadFriendList();
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("\nIllegal choice.");
            }
        }
    }

    private void loadFriendList() {
        try {
            Scanner fileInput = new Scanner(file);

            while(fileInput.hasNextLine()){
                System.out.println(fileInput.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveFriendList() {
        try {
            PrintStream ps = new PrintStream(file);

            for (Friend friend : friends) {
                ps.println(friend.getName());
                ps.println(friend.getPhoneNumber());
                ps.println(friend.getEmail());
            }

            ps.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFriend() {

        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDELETE FRIEND:");
        System.out.print("Enter name of friend to delete: ");
        name = scanner.nextLine();
        Friend friendDel = null;
        for (Friend friend : friends) {
            if (friend.getName().equalsIgnoreCase(name)) {
                friendDel = friend;
            }
        }
        friends.remove(friendDel);
        saveFriendList();
        System.out.println("\nFriend deleted.");
    }

    private void createNewFriend() {
        String name;
        String phoneNumber;
        String email;
        Friend friend;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCREATE NEW FRIEND\n");
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.print("Phone number: ");
        phoneNumber = scanner.nextLine();
        System.out.print("Email: ");
        email = scanner.nextLine();
        friend = new Friend(name, phoneNumber, email);
        friends.add(friend);
        saveFriendList();
        System.out.println("\nFriend added.");
    }

    private void viewFriendList() {
        System.out.println("\nFRIEND LIST:");
        for (int i = 0; i < friends.size(); i++) System.out.println("#" + i + ": " + friends.get(i));
    }
}
