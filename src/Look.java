import java.util.List;

public class Look {
    private String nome;
    private List<Roupas_nao_intimas> roupasNaoIntimas;
    private RoupaIntima roupaIntima;
    private List<Acessorios> acessorios;
    private int vezesUsado = 0;

    public Look(String nome, List<Roupas_nao_intimas> roupasNaoIntimas, RoupaIntima roupaIntima, List<Acessorios> acessorios) {
        this.nome = nome;
        this.roupasNaoIntimas = roupasNaoIntimas;
        this.roupaIntima = roupaIntima;
        this.acessorios = acessorios;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Roupas_nao_intimas> getRoupasNaoIntimas() { return roupasNaoIntimas; }
    public void setRoupasNaoIntimas(List<Roupas_nao_intimas> roupasNaoIntimas) { this.roupasNaoIntimas = roupasNaoIntimas; }

    public RoupaIntima getRoupaIntima() { return roupaIntima; }
    public void setRoupaIntima(RoupaIntima roupaIntima) { this.roupaIntima = roupaIntima; }

    public List<Acessorios> getAcessorios() { return acessorios; }
    public void setAcessorios(List<Acessorios> acessorios) { this.acessorios = acessorios; }

    public int getVezesUsado() { return vezesUsado; }
    public void setVezesUsado(int vezesUsado) { this.vezesUsado = vezesUsado; }
    public void incrementarUso() { this.vezesUsado++; }
} 