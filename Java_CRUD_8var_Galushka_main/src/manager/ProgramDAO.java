package manager;

import domain.Program;
import repository.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAO {
    private static final String SELECT_ALL_PROGRAMS = "SELECT * FROM movies_db.public.programs";
    private static final String INSERT_PROGRAM = "INSERT INTO movies_db.public.programs (title, channel, genre, day_of_week, start_time) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PROGRAM = "UPDATE movies_db.public.programs SET title = ?, channel = ?, genre = ?, day_of_week = ?, start_time = ? WHERE id = ?";
    private static final String DELETE_PROGRAM = "DELETE FROM movies_db.public.programs WHERE id = ?";

    public List<Program> getAllPrograms() {
        List<Program> programs = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PROGRAMS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Program program = new Program();
                program.setId(resultSet.getInt("id"));
                program.setTitle(resultSet.getString("title"));
                program.setChannel(resultSet.getString("channel"));
                program.setGenre(resultSet.getString("genre"));
                program.setDayOfWeek(resultSet.getString("day_of_week"));
                program.setStartTime(resultSet.getString("start_time"));

                programs.add(program);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return programs;
    }

    public void addProgram(Program program) {
        try (Connection connection = DatabaseConnector.getConnection();
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

    public void updateProgram(Program program) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PROGRAM)) {

            statement.setString(1, program.getTitle());
            statement.setString(2, program.getChannel());
            statement.setString(3, program.getGenre());
            statement.setString(4, program.getDayOfWeek());
            statement.setString(5, program.getStartTime());
            statement.setInt(6, program.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProgram(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PROGRAM)) {

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Другие методы для обновления, удаления и редактирования программ
}