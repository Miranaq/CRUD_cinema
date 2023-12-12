package interfaceFilm;

import controller.FilmController;
import moduleFilm.Film;

import java.util.Scanner;

public class interfaceFilm {
    private FilmController filmController;
    private Scanner scanner;

    public interfaceFilm() {
        filmController = new FilmController();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.print("Введите команду (ДобавитьФильм, ОбновитьФильм, УдалитьФильм, ВсеФильмы, выход): ");
            String command = scanner.nextLine();

            if (command.equals("ВсеФильмы")) {
                filmController.showPrograms();
            } else if (command.equals("ДобавитьФильм")) {
                Film program = new Film();
                filmController.addProgram();
            } else if (command.equals("ОбновитьФильм")) {
                Film program = new Film();
                filmController.updateProgram();
            } else if (command.equals("УдалитьФильм")) {
                filmController.deleteProgram();
            } else if (command.equals("выход")) {
                break;
            } else {
                System.out.println("Неверная команда. Пожалуйста, попробуйте снова.");
            }
        }
    }
}