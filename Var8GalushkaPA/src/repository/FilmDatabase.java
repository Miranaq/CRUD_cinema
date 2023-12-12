package repository;

import moduleFilm.Film;
import repository.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDatabase {

    private Connection connection;
    private List<Film> programs;

    public FilmDatabase() {
        try {
            connection = DatabaseConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static final String SELECT_ALL_PROGRAMS = "SELECT * FROM movies_db.public.programs";
    private static final String INSERT_PROGRAM = "INSERT INTO movies_db.public.programs (title, channel, genre, day_of_week, start_time) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PROGRAM = "UPDATE movies_db.public.programs SET title = ?, channel = ?, genre = ?, day_of_week = ?, start_time = ? WHERE id = ?";
    private static final String DELETE_PROGRAM = "DELETE FROM movies_db.public.programs WHERE id = ?";

    private Connection getConnection() throws SQLException {
        return DatabaseConnector.getConnection();
    }
    public List<Film> getAllFilm() {
        List<Film> programs = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PROGRAMS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String channel = resultSet.getString("channel");
                String genre = resultSet.getString("genre");
                String dayOfWeek = resultSet.getString("day_of_week");
                String startTime = resultSet.getString("start_time");

                Film film = new Film(id, title, channel, genre, dayOfWeek, startTime);
                programs.add(film);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return programs;
    }

    public void addFilm(Film program) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PROGRAM)) {

            statement.setString(1, program.getTitle());
            statement.setString(2, program.getChannel());
            statement.setString(3, program.getGenre());
            statement.setString(4, program.getDayOfWeek());
            statement.setString(5, program.getStartTime());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFilm(Film film) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PROGRAM)) {

            statement.setString(1, film.getTitle());
            statement.setString(2, film.getChannel());
            statement.setString(3, film.getGenre());
            statement.setString(4, film.getDayOfWeek());
            statement.setString(5, film.getStartTime());
            statement.setInt(6, film.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFilm(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PROGRAM)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Film getFilmById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM movies_db.public.programs WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Film film = new Film();
                    film.setId(resultSet.getInt("id"));
                    film.setTitle(resultSet.getString("title"));
                    film.setChannel(resultSet.getString("channel"));
                    film.setGenre(resultSet.getString("genre"));
                    film.setDayOfWeek(resultSet.getString("day_of_week"));
                    film.setStartTime(resultSet.getString("start_time"));
                    return film;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка исключения
        }
    }
}