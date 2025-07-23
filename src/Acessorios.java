import java.io.Serializable;

public class Acessorios extends Item implements IEmprestavel{
    private boolean emprestado;
    private String dataEmprestimo;
    private String pessoaEmprestimo;

    public Acessorios(String nome, String cor, String tamanho, String origem, String conservacao,String imgPath) {
        super(nome, cor, tamanho, origem, conservacao, imgPath);
    }

    public void registrarEmprestimo(String pessoaEmprestimo, String data) {
        this.emprestado = true;
        this.pessoaEmprestimo = pessoaEmprestimo;
        this.dataEmprestimo = data;
    }

    public String getPessoaEmprestimo() { return pessoaEmprestimo; }
    public void setPessoaEmprestimo(String pessoaEmprestimo) { this.pessoaEmprestimo = pessoaEmprestimo; }

    @Override
    public int quantidadeDeDiasDesdeOEmprestimo() {
        if (!emprestado) return 0;
        return 1; //this.dataEmprestimo;
    }

    @Override
    public void registrarDevolucao() {
        this.emprestado = false;
        this.dataEmprestimo = null;
        this.pessoaEmprestimo = null;
    }
}
