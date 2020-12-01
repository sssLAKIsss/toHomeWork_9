import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCtesting {
    private static Connection connection;
    private static Statement statement;


    private static PreparedStatement preparedStatement;


    public static void main(String[] args) {
        try {
            connect();
            statement.executeUpdate("Insert into students (name, score) values ('Andrey', 100);");//create
            statement.executeUpdate("Update students set  score = 80 where id = 1;");//update
            statement.executeUpdate("Delete from students where id  = 2;"); // delete
            statement.executeUpdate("Delete from students");// remove all strings in table
            statement.executeUpdate("Drop table students ;"); // delete our table


            try (ResultSet rs = statement.executeQuery("Select * from students")) {
                while (rs.next()) {
                    System.out.println(rs.getInt( 1) + " " + rs.getString("name") + " " + rs.getInt("score"));
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws SQLException {


        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main1.db");

            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement("Insert into students (name, score) values (?, ?0;");// вместо вопросов какие-то данные

        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Unable to connect");
        }
    }

    public static void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
