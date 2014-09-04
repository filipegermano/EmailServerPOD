package br.edu.ifpb.emailserverpod;

import br.edu.ifpb.emailsharedpod.Email;
import java.rmi.RemoteException;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FILIPE
 */
public class Agendador extends TimerTask {

    @Override
    public void run() {
        GerenciaEmail gerenciaEmail = new GerenciaEmail();
        EmailDAO emailDAO = new EmailDAO();
        List<Email> emails = emailDAO.emailsNaoEnviados();

        if (emails != null) {
            for (Email email1 : emails) {
                try {
                    String enviado = gerenciaEmail.enviaEmail(email1);
                    if (enviado == "ok") {
                        emailDAO.setStatus(email1);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Agendador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
