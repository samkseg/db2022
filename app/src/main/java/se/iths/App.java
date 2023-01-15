package se.iths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import static se.iths.Constants.*;


public class App {
    public static void main(String[] args) throws SQLException {
        App app = new App();
        try {
            app.load();
        } catch (SQLException e) {
            System.err.println(String.format("Error reading database %s", e.toString()));
        }
    }

    private void load() throws SQLException {
        Collection<Artist> artists = loadArtists();
        for(Artist artist : artists){
            System.out.println(artist);
        }
    }

    private Collection<Artist> loadArtists() throws SQLException {
        Collection<Artist> artists = new ArrayList<>();
        Connection con = DriverManager.getConnection(JDBC_CONNECTION, JDBC_USER, JDBC_PASSWORD);
        ResultSet rs = con.createStatement().executeQuery(SQL_SELECT_ALL_ARTISTS);
        while (rs.next()) {
            long id = rs.getLong(SQL_COL_ARTIST_ID);
            String name = rs.getString(SQL_COL_ARTIST_NAME);
            Artist artist = new Artist(id, name);
            artists.add(artist);
            Collection<Album> albums = loadAlbums(artist.getId());
            for(Album album : albums) {
                artist.addAlbum(album);
            }
        }
        rs.close();
        con.close();
        return artists;
    }

    private Collection<Album> loadAlbums(long artistId) throws SQLException {
        Collection<Album> albums = new ArrayList<>();
        Connection con = DriverManager.getConnection(JDBC_CONNECTION, JDBC_USER, JDBC_PASSWORD);
        PreparedStatement pStat = con.prepareStatement(SQL_SELECT_ALBUM_FOR_ARTISTS);
        pStat.setLong(1, artistId);
        ResultSet rs = pStat.executeQuery();
        while (rs.next()) {
            long albumId = rs.getLong(SQL_COL_ALBUM_ID);
            String albumTitle = rs.getString(SQL_COL_ALBUM_TITLE);
            Album album = new Album(albumId, albumTitle);
            albums.add(album);
        }
        pStat.close();
        rs.close();
        con.close();
        return albums;
    }
}
