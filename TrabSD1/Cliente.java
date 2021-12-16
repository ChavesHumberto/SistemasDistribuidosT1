import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.Scanner;

public class Cliente implements IntCliente{
    private String nome;
    public Cliente(String nome){
        this.nome = nome;
    }

    public void setConteudo(String conteudo) {
        System.out.println("New : " + conteudo);
    }

    public String getNome() {
        return(this.nome);
    }


    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Nome: ");
        String nm = sc.nextLine();
        IntCliente c = new Cliente(nm);
        System.out.println("Escolha um game: ");
        System.out.println("1-LOL 2-VALORANT 3-CSGO");
        int opc = sc.nextInt();
        try{
            IntCliente stubCliente = (IntCliente)UnicastRemoteObject.exportObject(c, 0);
            Registry rg = LocateRegistry.getRegistry();
            rg.bind(c.getNome(), stubCliente);

            rg = LocateRegistry.getRegistry();
            IntServidor stub = (IntServidor) rg.lookup("Servidor");

            stub.subscribe(c,opc);
        } catch (Exception e){

        }
    }
}
