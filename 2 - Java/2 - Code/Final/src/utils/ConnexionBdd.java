package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

public class ConnexionBdd {

    private Connection connexion;
    private GlobalVar globalVar = new GlobalVar();

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

    public Connection getConnection() {
        return this.connexion;
    }
}
