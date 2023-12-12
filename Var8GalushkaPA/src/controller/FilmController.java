package controller;

import moduleFilm.Film;
import repository.FilmDatabase;

import java.util.List;
import java.util.Scanner;

public class FilmController {
    private FilmDatabase filmDatabase;

    public FilmController() {
        filmDatabase = new FilmDatabase();
    }

    public void showPrograms() {
        try {
            List<Film> programs = filmDatabase.getAllFilm();


            if (programs.isEmpty()) {
                System.out.println("Нет доступных фильмов");
            } else {
                System.out.println("Список фильмов:");
                for (Film program : programs) {
                    System.out.println(program);
                }
            }
        } finally {
            filmDatabase.closeConnection();
        }
    }

    public void addProgram() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите название фильма:");
            String title = scanner.nextLine();

            System.out.println("Введите канал:");
            String channel = scanner.nextLine();

            System.out.println("Введите жанр фильма:");
            String genre = scanner.nextLine();

            System.out.println("Введите день показа фильма:");
            String dayOfWeek = scanner.nextLine();

            System.out.println("Введите время начала фильма:");
            String startTime = scanner.nextLine();

            Film program = new Film();
            program.setTitle(title);
            program.setChannel(channel);
            program.setGenre(genre);
            program.setDayOfWeek(dayOfWeek);
            program.setStartTime(startTime);

            filmDatabase.addFilm(program);

            System.out.println("Фильм добавлен");
        } finally {
            filmDatabase.closeConnection();
        }
    }

    public void updateProgram() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите ID фильма, который вы хотите обновить:");
            int id = scanner.nextInt();
            scanner.nextLine();

            Film programToUpdate = filmDatabase.getFilmById(id);

            if (programToUpdate == null) {
                System.out.println("Фильм с указанным ID не найден");
                return;
            }

            System.out.println("Введите новое название фильма:");
            String title = scanner.nextLine();

            System.out.println("Введите новый канал:");
            String channel = scanner.nextLine();

            System.out.println("Введите новый жанр фильма:");
            String genre = scanner.nextLine();

            System.out.println("Введите новый день показа:");
            String dayOfWeek = scanner.nextLine();

            System.out.println("Введите новое время начала фильма:");
            String startTime = scanner.nextLine();

            programToUpdate.setTitle(title);
            programToUpdate.setChannel(channel);
            programToUpdate.setGenre(genre);
            programToUpdate.setDayOfWeek(dayOfWeek);
            programToUpdate.setStartTime(startTime);

            filmDatabase.updateFilm(programToUpdate);

            System.out.println("Фильм добавлен");
        } finally {
            filmDatabase.closeConnection();
        }
    }

    public void deleteProgram() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите ID фильма, который вы хотите удалить:");
            int id = scanner.nextInt();
            scanner.nextLine();

            Film programToDelete = filmDatabase.getFilmById(id);

            if (programToDelete == null) {
                System.out.println("Фильм с указанным ID не найден");
                return;
            }

            filmDatabase.deleteFilm(id);

            System.out.println("Фильм успешно удален");
        } finally {
            filmDatabase.closeConnection();
        }
    }

    public Film getProgramById(int id) {
        try {
            return filmDatabase.getFilmById(id);
        } finally {
            filmDatabase.closeConnection();
        }
    }
}