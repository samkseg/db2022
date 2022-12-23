package se.iths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class App {

    private static final Collection<Artist> artists = new ArrayList<>();
    private static final String JDBC_CONNECTION = "jdbc:mysql://localhost:3306/Chinook";
    private static final String JDBC_USER = "iths";
    private static final String JDBC_PASSWORD = "iths";
    private static final String SQL_SELECT_ALL_ARTISTS = "SELECT ArtistId, Name FROM Artist";
    private static final String SQL_COL_ARTIST_ID = "ArtistId";
    private static final String SQL_COL_ARTIST_NAME = "Name";
    public static void main(String[] args) throws SQLException {
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(JDBC_CONNECTION, JDBC_USER, JDBC_PASSWORD);
            rs = con.createStatement().executeQuery(SQL_SELECT_ALL_ARTISTS);
            while (rs.next()) {
                long id = rs.getLong(SQL_COL_ARTIST_ID);
                String name = rs.getString(SQL_COL_ARTIST_NAME);
                artists.add(new Artist(id, name));
            }
        } catch (SQLException e) {
            System.err.println(String.format("Error reading database %s", e.toString()));
        } finally {
            try {
                rs.close();
            } catch (Exception ignore) {}
            try {
                con.close();
            } catch (Exception ignore) {}
        }
        for(Artist artist : artists) {
            System.out.println(artist);
        }
    }
}
