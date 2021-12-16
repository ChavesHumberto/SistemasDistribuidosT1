public class Celula {
    private int id;
    private String conteudo = "";

    public Celula(int id){
        this.id = id;
    }
    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
      }
      
    public String getConteudo(){
      return this.conteudo;
    }
}