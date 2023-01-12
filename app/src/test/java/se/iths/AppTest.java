package se.iths;

import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppTest {
    private static final String JDBC_CONNECTION = "jdbc:mysql://localhost:3306/iths";
    private static final String JDBC_USER = "iths";
    private static final String JDBC_PASSWORD = "iths";
    private static final String TEST_USER = "Nisse Karlsson";
    private static final String TEST_ROLE = "Admin";
    private static final String TEST_NEWROLE = "User";
    private static long actualId;
    public static Connection con = null;

    @BeforeAll
    public static void setUp() throws Exception {
        con = DriverManager.getConnection(JDBC_CONNECTION, JDBC_USER, JDBC_PASSWORD);
        con.createStatement().execute("DROP TABLE IF EXISTS User");
        con.createStatement().execute("CREATE TABLE User (Id INT NOT NULL AUTO_INCREMENT, Name VARCHAR(255) NOT NULL, ROLE VARCHAR(255) NOT NULL, CONSTRAINT PRIMARY KEY (Id))");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        con.close();
    }

    @Order(1)
    @Test
    void createRowInDatabase() throws Exception {
        PreparedStatement stat = con.prepareStatement("INSERT INTO User (Name, Role) VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
        stat.setString(1, TEST_USER);
        stat.setString(2, TEST_ROLE);
        stat.execute();
        ResultSet rs = stat.getGeneratedKeys();
        assertTrue(rs.next(), "Should have a row with generated id!");
        final long expectedId = 1L;
        actualId = rs.getLong(1);
        assertEquals(expectedId, actualId, "Should have correct id after insert!");
    }

    @Order(2)
    @Test
    void findRowInDatabase() throws Exception {
        PreparedStatement stat = con.prepareStatement("SELECT Id, Name, Role FROM User WHERE Id = ?");
        stat.setLong(1, actualId);
        ResultSet rs = stat.executeQuery();
        assertTrue(rs.next(), "Should find one row!");
        assertEquals(actualId, rs.getLong("Id"), "Selected id should match!");
        assertTrue(TEST_USER.equalsIgnoreCase(rs.getString("Name")), "Selected user should match!");
        assertTrue(TEST_ROLE.equalsIgnoreCase(rs.getString("Role")), "Selected role should match!");
        rs.close();
        stat.close();
    }

    @Order(3)
    @Test
    void updateRowInDataBase() throws Exception {
        PreparedStatement stat = con.prepareStatement("UPDATE User SET ROLE = ? WHERE ID = ?");
        stat.setString(1, TEST_NEWROLE);
        stat.setLong(2, actualId);
        stat.execute();

        stat = con.prepareStatement("SELECT Role FROM User WHERE ID = ?");
        stat.setLong(1, actualId);
        ResultSet rs = stat.executeQuery();
        assertTrue(rs.next(), "Should find one row!");
        assertTrue(TEST_NEWROLE.equalsIgnoreCase(rs.getString("Role")), "Update role should match!");
    }

    @Order(4)
    @Test
    void deleteRowInDatabase() throws Exception {
        con.createStatement().execute("DELETE FROM User");
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT count(*) FROM User");
        assertTrue(rs.next(), "Should find one row with count!");
        assertEquals(0, rs.getInt(1), "Table should be empty!");
    }
}
