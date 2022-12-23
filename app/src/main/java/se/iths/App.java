package se.iths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import static se.iths.Constants.*;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws SQLException {
        App app = new App();
        app.load();
    }

    private void load() {
        Collection<Artist> artists = loadArtists();
        for(Artist artist : artists){
            System.out.println(artist);
        }
    }

    private Collection<Artist> loadArtists() {
        Collection<Artist> artists = new ArrayList<>();
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
        return artists;
    }
}
