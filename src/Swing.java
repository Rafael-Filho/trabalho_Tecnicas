import javax.swing.*;
import java.awt.*;

public class Swing {
    private Vestuario gvp;
    private JFrame janela;
    private JPanel itensListPanel;
    private JTextField buscaField;
    private JButton buscarBtn;
    private JComboBox<String> filtroCombo;

    public Swing() {
        gvp = new Vestuario();
        criarGUI();
    }

    private void criarGUI() {
        janela = new JFrame("Gestor de Vestuário Pessoal");
        janela.setSize(800, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //comando pra parar de rodar o programa quando fechar a aba
        janela.setLocationRelativeTo(null); //tela aparece no meio

        JPanel itensPanel = new JPanel(new BorderLayout());     //painel dos itens
        JButton bAdicionarItem = new JButton("Adicionar Item");
        JButton bRemoverItem = new JButton("Remover Item");
        JPanel botoesItensPanel = new JPanel();
        botoesItensPanel.add(bAdicionarItem);
        botoesItensPanel.add(bRemoverItem);

        // Substituir JTextArea por JPanel para exibir itens com imagem
        itensListPanel = new JPanel();
        itensListPanel.setLayout(new BoxLayout(itensListPanel, BoxLayout.Y_AXIS));
        JScrollPane itensScrollPane = new JScrollPane(itensListPanel);
        itensScrollPane.setPreferredSize(new Dimension(0, 450)); // Limita altura máxima

        // Barra de busca
        JPanel buscaPanel = new JPanel(new BorderLayout());
        buscaField = new JTextField();
        buscarBtn = new JButton("Buscar");
        // Adiciona o combo de filtros
        String[] opcoesFiltro = {"Nome", "Cor", "Tamanho", "Loja", "Conservação"};
        filtroCombo = new JComboBox<>(opcoesFiltro);
        JPanel centroBusca = new JPanel(new BorderLayout());
        centroBusca.add(buscaField, BorderLayout.CENTER);
        centroBusca.add(filtroCombo, BorderLayout.EAST);
        buscaPanel.add(new JLabel("Buscar: "), BorderLayout.WEST);
        buscaPanel.add(centroBusca, BorderLayout.CENTER);
        buscaPanel.add(buscarBtn, BorderLayout.EAST);
        itensPanel.add(buscaPanel, BorderLayout.NORTH);
        itensPanel.add(botoesItensPanel, BorderLayout.CENTER); // será ajustado abaixo
        itensPanel.add(itensScrollPane, BorderLayout.SOUTH);   // será ajustado abaixo

        //painel de looks
        JPanel looksPanel = new JPanel(new BorderLayout());
        JButton bAdicionarLook = new JButton("Adicionar Look");
        JButton bRemoverLook = new JButton("Remover Look");
        JButton bListarLooks = new JButton("Listar Looks");
        JButton usarLookBtn = new JButton("Usar Look");

        JPanel botoesLooksPanel = new JPanel();
        botoesLooksPanel.add(bAdicionarLook);
        botoesLooksPanel.add(bRemoverLook);
        botoesLooksPanel.add(bListarLooks);
        botoesLooksPanel.add(usarLookBtn);

        JTextArea looksTextArea = new JTextArea();
        looksTextArea.setEditable(false);
        JScrollPane looksScrollPane = new JScrollPane(looksTextArea);

        looksPanel.add(botoesLooksPanel, BorderLayout.NORTH);
        looksPanel.add(looksScrollPane, BorderLayout.CENTER);

        //painel de estatisticas
        JPanel statsPanel = new JPanel(new BorderLayout());
        JButton bItensMaisUsados = new JButton("Itens Mais Usados");
        JButton bItensEmprestados = new JButton("Itens Emprestados");
        JButton bLooksMaisUsados = new JButton("Looks Mais Usados");

        JPanel botoesStatsPanel = new JPanel();
        botoesStatsPanel.add(bItensMaisUsados);
        botoesStatsPanel.add(bItensEmprestados);
        botoesStatsPanel.add(bLooksMaisUsados);

        JTextArea statsTextArea = new JTextArea();
        statsTextArea.setEditable(false);
        JScrollPane statsScrollPane = new JScrollPane(statsTextArea);

        statsPanel.add(botoesStatsPanel, BorderLayout.NORTH);
        statsPanel.add(statsScrollPane, BorderLayout.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Itens", itensPanel);
        tabbedPane.addTab("Looks", looksPanel);
        tabbedPane.addTab("Estatísticas", statsPanel);

        bAdicionarItem.addActionListener(e -> {
            String[] tipos = {"Camisa", "Calça", "Saia", "Casaco", "Roupa Íntima", "Acessório", "Item Emprestável"};

            String tipo = (String) JOptionPane.showInputDialog(janela, "Selecione o tipo:",
                    "Adicionar Item", JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);

            if (tipo != null) {
                JPanel panel = new JPanel(new GridLayout(0, 2));
                JTextField nomeField = new JTextField();
                JTextField corField = new JTextField();
                JTextField tamanhoField = new JTextField();
                JTextField lojaField = new JTextField();
                JTextField conservacaoField = new JTextField();
                JTextField imgPathField = new JTextField();

                panel.add(new JLabel("Nome:"));
                panel.add(nomeField);
                panel.add(new JLabel("Cor:"));
                panel.add(corField);
                panel.add(new JLabel("Tamanho:"));
                panel.add(tamanhoField);
                panel.add(new JLabel("Loja:"));
                panel.add(lojaField);
                panel.add(new JLabel("Conservação:"));
                panel.add(conservacaoField);
                panel.add(new JLabel("Img Path:"));
                panel.add(imgPathField);

                int result = JOptionPane.showConfirmDialog(janela, panel, "Adicionar Item",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    Item novoItem = null;
                    switch (tipo) {
                        case "Camisa":
                        case "Calça":
                        case "Saia":
                        case "Casaco":
                            novoItem = new Roupas(nomeField.getText(), corField.getText(),
                                    tamanhoField.getText(), lojaField.getText(), conservacaoField.getText(), imgPathField.getText());
                            break;
                        case "Acessório":
                            novoItem = new Acessorios(nomeField.getText(), corField.getText(),
                                    tamanhoField.getText(), lojaField.getText(), conservacaoField.getText(), imgPathField.getText());
                            break;
                    }
                    if (novoItem != null) {
                        gvp.addItem(novoItem);
                        JOptionPane.showMessageDialog(janela, "Item adicionado com sucesso!");
                        atualizarLista(); // Atualiza a lista automaticamente
                    }
                }
            }
        });

        bRemoverItem.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog(janela, "Digite o nome do item a remover:");
            if (nome != null && !nome.isEmpty()) {
                boolean removido = false;
                for (Item item : new java.util.ArrayList<>(gvp.getItens())) {
                    if (item.getNome().equalsIgnoreCase(nome)) {
                        gvp.getItens().remove(item);
                        removido = true;
                        break;
                    }
                }
                if (removido) {
                    JOptionPane.showMessageDialog(janela, "Item removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(janela, "Item não encontrado.");
                }
                // Atualiza a listagem
                atualizarLista();
            }
        });

        // Botão buscar
        buscarBtn.addActionListener(e -> atualizarLista());
        // Enter no campo de busca
        buscaField.addActionListener(e -> atualizarLista());

        // Atualizar lista ao listar itens
        // Remover listener do botão Listar Itens

        janela.add(tabbedPane);
        janela.setVisible(true);
    }

    // Função para atualizar a lista de itens com filtro
    // Agora como método da classe
    private void atualizarLista() {
        String filtro = buscaField.getText().trim().toLowerCase();
        String campo = (String) filtroCombo.getSelectedItem();
        itensListPanel.removeAll();
        for (Item item : gvp.getItens()) {
            boolean exibir = false;
            switch (campo) {
                case "Nome":
                    exibir = filtro.isEmpty() || item.getNome().toLowerCase().contains(filtro);
                    break;
                case "Cor":
                    exibir = filtro.isEmpty() || item.getCor().toLowerCase().contains(filtro);
                    break;
                case "Tamanho":
                    exibir = filtro.isEmpty() || item.getTamanho().replaceAll("\\s+", "").equalsIgnoreCase(filtro.replaceAll("\\s+", ""));
                    break;
                case "Loja":
                    exibir = filtro.isEmpty() || item.getOrigem().toLowerCase().contains(filtro);
                    break;
                case "Conservação":
                    exibir = filtro.isEmpty() || item.getConservacao().toLowerCase().contains(filtro);
                    break;
            }
            if (exibir) {
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BorderLayout());
                JLabel imgLabel;
                if (item.getImgPath() != null && !item.getImgPath().isEmpty()) {
                    ImageIcon icon = new ImageIcon(item.getImgPath());
                    Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    imgLabel = new JLabel(new ImageIcon(img));
                } else {
                    imgLabel = new JLabel("Sem imagem");
                }
                itemPanel.add(imgLabel, BorderLayout.WEST);
                String info = "<html>" +
                        "Nome: " + item.getNome() + "<br>" +
                        "Cor: " + item.getCor() + "<br>" +
                        "Tamanho: " + item.getTamanho() + "<br>" +
                        "Loja: " + item.getOrigem() + "<br>" +
                        "Conservação: " + item.getConservacao() + "</html>";
                JLabel infoLabel = new JLabel(info);
                itemPanel.add(infoLabel, BorderLayout.CENTER);
                itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                itensListPanel.add(itemPanel);
            }
        }
        itensListPanel.revalidate();
        itensListPanel.repaint();
    }
}
