public class Roupas extends Item implements ILavavel {
    protected int vezesLavadas;
    protected boolean estado;

    // Construtor
    public Roupas(String nome, String cor, String tamanho, String origem, String conservacao,String imgPath) {
        super(nome, cor, tamanho, origem, conservacao, imgPath); //construtor da classe abst Item
        this.vezesLavadas = 0;
        this.estado = true;
    }
    public void registrarLavagem() {
        this.vezesLavadas++;
    }

    public int getVezesLavadas() {
        return vezesLavadas;
    }

    public boolean getEstado(){
        return this.estado;
    }
}
