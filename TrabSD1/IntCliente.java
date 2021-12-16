import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntCliente extends Remote {
    public void setConteudo(String conteudo) throws RemoteException;//imprime um conteudo na tela
    public String getNome() throws RemoteException;//recupera nome do cliente
}
