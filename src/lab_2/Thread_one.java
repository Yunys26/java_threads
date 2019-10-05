package lab_2;
import java.util.Random;
import java.util.Scanner;

public class Thread_one {
    public static void main(String args[]) {

        System.out.println("Основной поток запущен\n");

        createThreads();
        userChoice();

        System.out.println("Основной поток завершен");

    }

    public static void createThreads() {

        ThreadControl threadOne = new ThreadControl( "1");
        threadOne.start();

        ThreadControl threadTwo = new ThreadControl( "2");
        threadTwo.start();

        ThreadControl threadThree = new ThreadControl( "3");
        threadThree.start();

        ThreadControl threadFour = new ThreadControl( "4");
        threadFour.start();

        ThreadControl threadFive = new ThreadControl( "5");
        threadFive.start();

        System.out.println("\n");

        try {
            threadOne.thread.join();
            threadTwo.thread.join();
            threadThree.thread.join();
            threadFour.thread.join();
            threadFive.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();

    }

    public static void userChoice() {

        Scanner input = new Scanner(System.in);
        System.out.println("Повтороить - 1; Выйти - 2");
        int choice = input.nextInt();

        if (choice == 1) {
            createThreads();
            userChoice();
        } else if (choice == 2) {
            createThreads();
        } else {
            System.out.println("Неккоректный ввод. Повторите.\n");
            userChoice();
        }

    }

}

class ThreadControl implements Runnable {

    public Thread thread;
    private String threadName;

    ThreadControl(String name) {
        threadName = name;
    }

    @Override
    public void run() {

        Random rand = new Random();
        int randomNum = rand.nextInt((10000 - 1000) + 1) + 1000;
        try {
            Thread.sleep(randomNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Поток " + threadName + " остановлен");
    }

    public void start() {
        System.out.println("Поток " + threadName + " запущен");
        thread = new Thread(this, threadName);
        thread.start();
    }
}
