package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBdd {

    private Connection connexion;

    public ConnexionBdd() {
        try {
            this.connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sae", "root", "#dElOpA_83/");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connexion;
    }
}
