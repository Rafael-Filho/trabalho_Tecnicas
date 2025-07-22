import java.util.Vector;

public abstract class Item {
    protected String nome;
    protected String cor;
    protected String tamanho;
    protected String origem;
    protected String conservacao; //excelente, boa, regular, ruim
    protected String imgPath;
    //protected String misc / conteudo / caracteristicas (nao sei ainda) [camisa "do coringa"]

    public Item(String nome, String cor, String tamanho, String origem, String conservacao, String imgPath) {
        this.nome = nome;
        this.cor = cor;
        this.tamanho = tamanho;
        this.origem = origem;
        this.conservacao = conservacao;
        this.imgPath = imgPath;
    }


    //getters/setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getConservacao() { return conservacao; }
    public void setConservacao(String conservacao) { this.conservacao = conservacao; }

    public String getImgPath() { return imgPath; }
    public void setImgPath(String imgPath) { this.imgPath = imgPath; }
}