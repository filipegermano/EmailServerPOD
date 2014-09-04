package br.edu.ifpb.emailserverpod;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author FILIPE
 */
public class App {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        
        Registry registry = LocateRegistry.createRegistry(9999);
        registry.bind("Fachada", new FachadaImpl());
        
        FachadaImpl impl = new FachadaImpl();
//        Email email = new Email();
//        EmailDAO emailDAO = new EmailDAO();
//        email.setAssunto("teste4");
//        email.setDestinatarios("filipegermano89@gmail.com");
//        email.setRemetente("Filipe Teste");
//        email.setMensagem("testando o email carai");
        
//        emailDAO.salvar(email);
        
        impl.agendadorEmail();
        
        System.out.println("Servidor criado!!!");
    }
}