public interface IEmprestavel {
    void registrarEmprestimo(String pessoaEmprestimo, String data);
    int quantidadeDeDiasDesdeOEmprestimo();
    void registrarDevolucao();
}
