package br.edu.ifpb.emailserverpod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FILIPE
 */
public class Conexao {
    private String driver, path, login, senha;
    //private Connection conexao;
    private static Conexao instancia;
    
    private Conexao() {
        this.driver = "org.postgresql.Driver";
        this.path = "jdbc:postgresql://localhost:5432/pod";
        this.login = "postgres";
        this.senha = "123456";
    }
    
    public static Conexao getInstance() {
        if (instancia == null) {
            return instancia = new Conexao();
        }
        return instancia;
    }

    public Connection createConnection() {
        try {
            Class.forName(this.driver);
            return DriverManager.getConnection(path, login, senha);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
