import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Scanner;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;

public class Servidor implements IntServidor {

    private HashMap<Integer,ArrayList<IntCliente >> assinaturas = new HashMap<Integer,ArrayList<IntCliente>>();
    private HashMap<Integer,Celula> jogos  = new HashMap<Integer,Celula>();

    public void publish(int chave , String msg){
        Celula jg = jogos.get(chave);
        jg.setConteudo(msg);
        ArrayList<IntCliente> asts = assinaturas.get(chave);
        for(IntCliente ct : asts){
            try{
                Registry registry = LocateRegistry.getRegistry();
                IntCliente stub = (IntCliente) registry.lookup(ct.getNome());
                stub.setConteudo(jg.getConteudo());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void subscribe(IntCliente c , int chave ){

        ArrayList<IntCliente> aux = assinaturas.get(chave);
        if(aux == null){
            aux = new ArrayList<IntCliente>();
        }
        aux.add(c);
        assinaturas.put(chave,aux);
    }

    public void addJogo(int id){
        Celula c = new Celula(id);
        jogos.put(id,c);
        assinaturas.put(id,null);
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Servidor srv = new Servidor();
        try{
            IntServidor stub = (IntServidor)UnicastRemoteObject.exportObject(srv, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Servidor", stub);

            System.err.println("Server ready.");
        } catch(Exception e){
            System.err.println("Server exception: " + e.toString());
        }

        srv.addJogo(1);//LOL
        srv.addJogo(2);//VALORANT
        srv.addJogo(3);//CSGO
        int opc = 0;
        do{
            System.out.println("1 - add noticia");
            System.out.println("2 - SAIR");
            opc = sc.nextInt();
            switch(opc){
                case 1:
                System.out.println("1-LOL 2-VALORANT 3-CSGO");
                int jg = sc.nextInt();
                sc.nextLine();
                String nv = sc.nextLine();
                srv.publish(jg, nv);
            }
        }while(opc != 2);

    }
}
