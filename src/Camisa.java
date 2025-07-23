public class Camisa extends Roupas_nao_intimas implements IEmprestavel, ILavavel {
    // Atributos específicos de Camisa
    private String tipoGola; // Ex: "V", "Redonda", "Polo"
    private boolean emprestado;
    private String dataEmprestimo;
    //private String pessoaEmprestimo;

    // Construtor
    public Camisa(String nome, String cor, String tamanho, String origem, String conservacao,String imgPath) {
        super(nome, cor, tamanho, origem, conservacao, imgPath);
        this.tipoGola = tipoGola;
        this.emprestado = false;
        this.vezesLavadas = 0;
    }

    // Métodos específicos de Camisa
    public String getTipoGola() {
        return tipoGola;
    }

    // Métodos da interface iEmprestavel
    @Override
    public void registrarEmprestimo(String pessoaEmprestimo, String data) {
        this.emprestado = true;
        //this.pessoaEmprestimo = pessoaEmprestimo;
        this.dataEmprestimo = data;
    }

    @Override
    public int quantidadeDeDiasDesdeOEmprestimo() {
        if (!emprestado) return 0;
        return this.vezesLavadas;
    }

    @Override
    public void registrarDevolucao() {
        this.emprestado = false;
        this.dataEmprestimo = null;
    }

    // Métodos da interface ILavavel
    @Override
    public void registrarLavagem() {
        this.vezesLavadas++;
    }

    @Override
    public int getVezesLavadas() {
        return vezesLavadas;
    }

    public boolean getEstado(){
        return this.estado;
    }
}