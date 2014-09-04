package br.edu.ifpb.emailserverpod;

import br.edu.ifpb.emailsharedpod.Email;
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
public class EmailDAO {

    private Conexao conexao;
    private Connection connection;

    public EmailDAO() {
        conexao = Conexao.getInstance();
    }

    public void salvar(Email email) {
        try {
            String sql = "insert into email (remetente, destinatarios, assunto, mensagem) "
                    + "values (?, ?, ?, ?)";

            connection = conexao.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, email.getRemetente());
            pst.setString(2, email.getDestinatarios());
            pst.setString(3, email.getAssunto());
            pst.setString(4, email.getMensagem());
            pst.execute();
            pst.close();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Email> emailsNaoEnviados() {
        try {
            List<Email> emails = new ArrayList<>();
            String sql = "select id, remetente, destinatarios, assunto, mensagem "
                    + "from email where status = false";
            connection = conexao.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
                Email email = new Email();
                email.setId(rs.getInt("id"));
                email.setRemetente(rs.getString("remetente"));
                email.setDestinatarios(rs.getString("destinatarios"));
                email.setAssunto(rs.getString("assunto"));
                email.setMensagem(rs.getString("mensagem"));
                
                emails.add(email);
            }
            rs.close();
            connection.close();
            pst.close();
            return emails;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void setStatus(Email email) {
        try {
            String sql = "update email set status = true where id = ? ";

            connection = conexao.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, email.getId());
            pst.executeUpdate();
            
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
