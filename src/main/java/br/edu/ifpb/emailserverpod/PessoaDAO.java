package br.edu.ifpb.emailserverpod;

import br.edu.ifpb.emailsharedpod.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FILIPE
 */
public class PessoaDAO {

    private Conexao conexao;
    private Connection connection;

    public PessoaDAO() {
        conexao = Conexao.getInstance();
    }

    public void salvar(Pessoa pessoa) {
        try {
            String sql = "insert into pessoa (nome, email) values (?, ?)";

            connection = conexao.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getEmail());

            pst.execute();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Pessoa> listaPessoas() {
        try {
            List<Pessoa> pessoas = new ArrayList<>();
            String sql = "select * from pessoa";
            connection = conexao.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                
                pessoas.add(pessoa);
            }
            rs.close();
            pst.close();
            connection.close();
            return pessoas;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
