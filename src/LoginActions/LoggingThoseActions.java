package LoginActions;

import MyFriends.Friend;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LoggingThoseActions {

    private ArrayList<Log> lines;
    private File file = new File("LoginActions//log.txt");

    public static void main(String[] args) {
        new LoggingThoseActions().run();
    }

    private void run() {
        boolean running;
        Menu menu = new Menu("MENU:", "Please choose option: ", new String[]{"1. Show log", "2. Enter new log", "3. Delete log", "9. Quit"});
        loadLog();
        running = true;
        while (running) {
            menu.printMenu();
            int userChoice = menu.readChoice();
            switch (userChoice) {
                case 1:
                    viewLog();
                    break;
                case 2:
                    addLog();
                    break;
                case 3:
                    deleteLog();
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("\nIllegal choice.");
            }
        }
    }

    private void deleteLog() {
        int logIndex;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDELETE LOG:");
        System.out.print("Enter index of log line to delete: ");
        logIndex = scanner.nextInt();
        System.out.println();

        lines.remove(lines.get(logIndex));
        saveLog();
        System.out.println("\nLog deleted.");
    }

    private void addLog() {
        String logLine;
        Log log;

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCREATE NEW LOG\n");
        System.out.print("Log: ");
        logLine = scanner.nextLine();

        log = new Log(logLine);

        lines.add(log);
        saveLog();
        System.out.println("\nLog added.");
    }

    private void viewLog() {
        lines.add(new Log("view log"));
        System.out.println("\nLOG LIST:");
        for (int i = 0; i < lines.size(); i++) System.out.println(lines.get(i));
    }

    private void loadLog() {
        lines.add(new Log("load log"));
        try {
            Scanner fileInput = new Scanner(file);
            while (fileInput.hasNextLine()) {
                System.out.println(fileInput.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveLog() {
        lines.add(new Log("save log"));
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            PrintStream ps = new PrintStream(fos);
            for (Log log : lines) {
                ps.println(log);
            }
            ps.close();
            fos.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    LoggingThoseActions() {
        lines = new ArrayList<Log>();

    }

}
