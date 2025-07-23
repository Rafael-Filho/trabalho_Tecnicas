public abstract class Roupas_nao_intimas extends Roupas implements IEmprestavel {
    private boolean emprestado;
    private String dataEmprestimo;
    private String pessoaEmprestimo;

    public Roupas_nao_intimas(String nome, String cor, String tamanho, String origem, String conservacao,String imgPath) {
        super(nome, cor, tamanho, origem, conservacao, imgPath);
        this.vezesLavadas = vezesLavadas;
        this.estado = estado;
        this.emprestado = emprestado;
        this.dataEmprestimo = dataEmprestimo;
    }

    public void registrarEmprestimo(String pessoaEmprestimo, String data) {
        this.emprestado = true;
        this.pessoaEmprestimo = pessoaEmprestimo;
        this.dataEmprestimo = data;
        this.setStatus("Emprestada");
    }

    public String getPessoaEmprestimo() { return pessoaEmprestimo; }
    public void setPessoaEmprestimo(String pessoaEmprestimo) { this.pessoaEmprestimo = pessoaEmprestimo; }

    @Override
    public int quantidadeDeDiasDesdeOEmprestimo() {
        if (!emprestado) return 0;
        return this.vezesLavadas;
    }

    @Override
    public void registrarDevolucao() {
        this.emprestado = false;
        this.dataEmprestimo = null;
        this.pessoaEmprestimo = null;
        this.setStatus("Suja");
    }
}
