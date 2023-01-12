package se.iths;

import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppTest {
    private static final String JDBC_CONNECTION = "jdbc:mysql://localhost:3306/iths";
    private static final String JDBC_USER = "iths";
    private static final String JDBC_PASSWORD = "iths";
    public static Connection con = null;

    @BeforeAll
    public static void setUp() throws Exception {
        con = DriverManager.getConnection(JDBC_CONNECTION, JDBC_USER, JDBC_PASSWORD);
        con.createStatement().execute("DROP TABLE IF EXISTS User");
        con.createStatement().execute("CREATE TABLE User (Id INT NOT NULL AUTO_INCREMENT, Name VARCHAR(255) NOT NULL), ROLE VARCHAR(255) NOT NULL, CONSTRAINT PRIMARY KEY (Id))");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        con.close();
    }
}
