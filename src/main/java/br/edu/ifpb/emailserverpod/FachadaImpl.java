package br.edu.ifpb.emailserverpod;

import br.edu.ifpb.emailsharedpod.Email;
import br.edu.ifpb.emailsharedpod.Fachada;
import br.edu.ifpb.emailsharedpod.Pessoa;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Timer;

/**
 *
 * @author FILIPE
 */
public class FachadaImpl extends UnicastRemoteObject implements Fachada {

    public FachadaImpl() throws RemoteException {
        super();
    }

    @Override
    public String enviaEmail(Email email) throws RemoteException {
        EmailDAO emailDAO = new EmailDAO();
        emailDAO.salvar(email);
        agendadorEmail();
        return null;
    }

    public void agendadorEmail() throws RemoteException {
        Agendador agendador = new Agendador();
        Timer timer = new Timer();
        timer.schedule(agendador, 0, 3000 * 60);
    }

    @Override
    public void salvar(Pessoa pessoa) throws RemoteException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.salvar(pessoa);
    }

    @Override
    public List<Pessoa> listaPessoas() throws RemoteException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.listaPessoas();
    }

    @Override
    public Long latencia(byte[] array) throws RemoteException {
        long tempo1 = System.currentTimeMillis();
        System.out.println(array);
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            return null;
        }
        long tempo2 = System.currentTimeMillis();
        return tempo2 - tempo1;
    }
}
