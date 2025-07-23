public class Roupas extends Item implements ILavavel {
    protected int vezesLavadas;
    protected boolean estado;
    private String status = "Limpa";

    // Construtor
    public Roupas(String nome, String cor, String tamanho, String origem, String conservacao,String imgPath) {
        super(nome, cor, tamanho, origem, conservacao, imgPath); //construtor da classe abst Item
        this.vezesLavadas = 0;
        this.estado = true;
        this.status = "Limpa";
    }
    public void registrarLavagem() {
        this.vezesLavadas++;
        this.setStatus("Limpa");
    }

    public int getVezesLavadas() {
        return vezesLavadas;
    }

    public boolean getEstado(){
        return this.estado;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
