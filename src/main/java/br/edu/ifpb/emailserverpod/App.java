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
        
        impl.agendadorEmail();
        
        System.out.println("Servidor criado!!!");
    }
}