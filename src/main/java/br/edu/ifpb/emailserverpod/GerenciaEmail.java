package br.edu.ifpb.emailserverpod;

import br.edu.ifpb.emailsharedpod.Email;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author FILIPE
 */
public class GerenciaEmail {
    
    private String remetente;
    private String senha;
    
    public GerenciaEmail() {
        remetente = "ifpbpod@gmail.com";
        senha = "rmi12345";
    }
    
    public String enviaEmail(Email email) throws RemoteException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(remetente, senha);
                    }
                });

        try {

            Message message = new MimeMessage(session);

            // Remetente
            message.setFrom(new InternetAddress(remetente, email.getRemetente()));

            // Destinatário(s) pode separar emails por virgula
            Address[] toUser = InternetAddress.parse(email
                    .getDestinatarios());

            message.setRecipients(Message.RecipientType.TO, toUser);

            // Assunto
            message.setSubject(email.getAssunto());

            message.setText(email.getMensagem());

            // Método para enviar a mensagem criada
            Transport.send(message);

            return "ok";
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return "error";
        }
    }
    
}
