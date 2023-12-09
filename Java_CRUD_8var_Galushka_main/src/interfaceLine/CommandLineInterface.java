package interfaceLine;

import domain.Program;
import manager.ProgramDAO;

import java.util.Scanner;

public class CommandLineInterface {
    private ProgramDAO programDAO;
    private Scanner scanner;

    public CommandLineInterface() {
        programDAO = new ProgramDAO();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("ВсеФильмы")) {
                System.out.println(programDAO.getAllPrograms());
            } else if (command.equals("ДобавитьФильм")) {
                System.out.print("Введите название фильма: ");
                String title = scanner.nextLine();
                System.out.print("Введите название канала: ");
                String channel = scanner.nextLine();
                System.out.print("Введите жанр фильма: ");
                String genre = scanner.nextLine();
                System.out.print("Введите день недели показа: ");
                String dayOfWeek = scanner.nextLine();
                System.out.print("Время начала показа: ");
                String startTime = scanner.nextLine();

                Program program = new Program(title, channel, genre, dayOfWeek, startTime);
                programDAO.addProgram(program);
                System.out.println("Фильм добавлен");
            } else if (command.equals("РедактироватьФильм")) {
                System.out.print("Введите id фильма: ");
                int id = Integer.parseInt(scanner.nextLine());


                System.out.print("Введите новое название фильма: ");
                String title = scanner.nextLine();
                System.out.print("Введите новый канал: ");
                String channel = scanner.nextLine();
                System.out.print("Введите новый жанр: ");
                String genre = scanner.nextLine();
                System.out.print("Введите новый день недели показа: ");
                String dayOfWeek = scanner.nextLine();
                System.out.print("Введите новое время показа: ");
                String startTime = scanner.nextLine();

                Program updatedProgram = new Program(title, channel, genre, dayOfWeek, startTime);
                updatedProgram.setId(id);
                programDAO.updateProgram(updatedProgram);
                System.out.println("Фильм добавлен");
            } else if (command.equals("УдалитьФильм")) {
                System.out.print("Введите id фильма, который вы ходите удалить: ");
                int id = Integer.parseInt(scanner.nextLine());


                programDAO.deleteProgram(id);
                System.out.println("Фильм удален");
            } else if (command.equals("Выход")) {
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
    }
}