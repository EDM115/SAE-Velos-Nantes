package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

/**
 * ConnexionBdd, allows to connect to the database
 */
public class ConnexionBdd {

    /**
     * Connection to the database
     */
    private Connection connexion;

    /**
     * GlobalVar, allows to know if the user is an admin or not
     */
    private GlobalVar globalVar = new GlobalVar();

    /**
     * ConnexionBdd constructor
     */
    public ConnexionBdd() {
        try {
            if (globalVar.isAdmin()) {
                this.connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_velos_nantes", "admin", "mdp_admin");
            } else {
                this.connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_velos_nantes", "user", "mdp_user");
            }
        } catch (SQLSyntaxErrorException e) {
            System.out.println("\u001B[31mERREUR\u001B[0m");
        } catch (SQLException e) {
            System.out.println("\u001B[31mREQUETE IMPOSSIBLE\u001B[0m");
        } 
    }

    /**
     * Get the connection to the database
     * @return Connection
     */
    public Connection getConnection() {
        return this.connexion;
    }

}
