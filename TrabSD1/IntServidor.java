
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IntServidor extends Remote{
  public void publish(int chave , String cl)throws RemoteException;//publicar uma noticia
  public void subscribe(IntCliente c , int chave ) throws RemoteException;//se increver em um jogo
}
