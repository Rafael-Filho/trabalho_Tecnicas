import javax.swing.*;
import java.awt.*;

public class Swing {
    private Vestuario gvp;
    private JFrame janela;
    private JPanel itensListPanel;
    private JTextField buscaField;
    private JButton buscarBtn;
    private JComboBox<String> filtroCombo;
    private java.util.List<Look> looks = new java.util.ArrayList<>();
    private JPanel looksListPanel;

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

        // Painel de itens
        itensListPanel = new JPanel();
        itensListPanel.setLayout(new BoxLayout(itensListPanel, BoxLayout.Y_AXIS));
        JScrollPane itensScrollPane = new JScrollPane(itensListPanel);
        itensScrollPane.setPreferredSize(new Dimension(0, 450));

        // Barra de busca
        JPanel buscaPanel = new JPanel(new BorderLayout());
        buscaField = new JTextField();
        buscarBtn = new JButton("Buscar");
        // combo de filtros
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

        // painel de looks
        JPanel looksPanel = new JPanel(new BorderLayout());
        JButton bAdicionarLook = new JButton("Adicionar Look");
        JButton bRemoverLook = new JButton("Remover Look");

        JPanel botoesLooksPanel = new JPanel();
        botoesLooksPanel.add(bAdicionarLook);
        botoesLooksPanel.add(bRemoverLook);

        looksListPanel = new JPanel();
        looksListPanel.setLayout(new BoxLayout(looksListPanel, BoxLayout.Y_AXIS));
        JScrollPane looksScrollPane = new JScrollPane(looksListPanel);
        looksScrollPane.setPreferredSize(new Dimension(0, 450));

        looksPanel.add(botoesLooksPanel, BorderLayout.NORTH);
        looksPanel.add(looksScrollPane, BorderLayout.CENTER);

        //painel de estatisticas
        JPanel statsPanel = new JPanel(new BorderLayout());
        JTabbedPane statsTabs = new JTabbedPane();
        // Geral
        JPanel geralPanel = new JPanel(new BorderLayout());
        JTextArea statsTextArea = new JTextArea();
        statsTextArea.setEditable(false);
        JScrollPane statsScrollPane = new JScrollPane(statsTextArea);
        geralPanel.add(statsScrollPane, BorderLayout.CENTER);
        statsTabs.addTab("Geral", geralPanel);
        // Looks
        JPanel looksStatsPanel = new JPanel();
        looksStatsPanel.add(new JLabel("Estatísticas de Looks (em breve)"));
        statsTabs.addTab("Looks", looksStatsPanel);
        // Aba Itens Emprestados
        JPanel emprestadosPanel = new JPanel();
        emprestadosPanel.add(new JLabel("Itens Emprestados (em breve)"));
        statsTabs.addTab("Itens Emprestados", emprestadosPanel);
        statsPanel.add(statsTabs, BorderLayout.CENTER);
        // Atualizar estatísticas ao abrir a aba
        statsTabs.addChangeListener(e -> atualizarEstatisticasBasicas(statsTextArea));

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
                            novoItem = new Camisa(nomeField.getText(), corField.getText(),
                                    tamanhoField.getText(), lojaField.getText(), conservacaoField.getText(), imgPathField.getText());
                            break;
                        case "Calça":
                            novoItem = new Calca(nomeField.getText(), corField.getText(),
                                    tamanhoField.getText(), lojaField.getText(), conservacaoField.getText(), imgPathField.getText());
                            break;
                        case "Saia":
                            novoItem = new Saia(nomeField.getText(), corField.getText(),
                                    tamanhoField.getText(), lojaField.getText(), conservacaoField.getText(), imgPathField.getText());
                            break;
                        case "Casaco":
                            novoItem = new Casaco(nomeField.getText(), corField.getText(),
                                    tamanhoField.getText(), lojaField.getText(), conservacaoField.getText(), imgPathField.getText());
                            break;
                        case "Roupa Íntima":
                            novoItem = new RoupaIntima(nomeField.getText(), corField.getText(),
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
                        atualizarLista();
                        atualizarEstatisticasBasicas(statsTextArea);
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
                atualizarEstatisticasBasicas(statsTextArea);
            }
        });

        // Botão buscar
        buscarBtn.addActionListener(e -> atualizarLista());
        // Enter no campo de busca
        buscaField.addActionListener(e -> atualizarLista());

        // Atualizar lista ao listar itens
        bAdicionarLook.addActionListener(e -> {
            abrirModalCriarLook();
            atualizarListaLooks();
        });

        bRemoverLook.addActionListener(e -> abrirModalExcluirLook());

        janela.add(tabbedPane);
        janela.setVisible(true);
    }

    // Função para atualizar a lista de itens com filtro
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
                itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                itemPanel.setPreferredSize(new Dimension(itemPanel.getPreferredSize().width, 100));

                // Painel de ações
                itemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) { 
                            JPopupMenu menu = new JPopupMenu();
                            JMenuItem editarItem = new JMenuItem("Editar");
                            editarItem.addActionListener(ev -> editarItemDialog(item));
                            menu.add(editarItem);
                            if (item instanceof ILavavel) {
                                JMenuItem lavarItem = new JMenuItem("Lavar");
                                lavarItem.addActionListener(ev -> {
                                    ((ILavavel) item).registrarLavagem();
                                    JOptionPane.showMessageDialog(janela, "Roupa lavada!");
                                    atualizarLista();
                                });
                                menu.add(lavarItem);
                            }
                            if (item instanceof IEmprestavel) {
                                JMenuItem emprestarItem = new JMenuItem("Emprestar");
                                emprestarItem.addActionListener(ev -> emprestarItemDialog(item));
                                menu.add(emprestarItem);
                            }
                            menu.show(itemPanel, e.getX(), e.getY());
                        }
                    }
                });
                itensListPanel.add(itemPanel);
            }
        }
        itensListPanel.revalidate();
        itensListPanel.repaint();
    }

    // Métodos para editar e emprestar
    private void editarItemDialog(Item item) {
        JPanel panel = new JPanel(new java.awt.GridLayout(0, 2));
        JTextField nomeField = new JTextField(item.getNome());
        JTextField corField = new JTextField(item.getCor());
        JTextField tamanhoField = new JTextField(item.getTamanho());
        JTextField lojaField = new JTextField(item.getOrigem());
        JTextField conservacaoField = new JTextField(item.getConservacao());
        JTextField imgPathField = new JTextField(item.getImgPath());
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
        int result = JOptionPane.showConfirmDialog(janela, panel, "Editar Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            item.setNome(nomeField.getText());
            item.setCor(corField.getText());
            item.setTamanho(tamanhoField.getText());
            item.setOrigem(lojaField.getText());
            item.setConservacao(conservacaoField.getText());
            item.setImgPath(imgPathField.getText());
            atualizarLista();
        }
    }
    private void emprestarItemDialog(Item item) {
        if (!(item instanceof IEmprestavel)) return;
        JPanel panel = new JPanel(new java.awt.GridLayout(0, 2));
        JTextField pessoaField = new JTextField();
        JTextField dataField = new JTextField();
        panel.add(new JLabel("Pessoa:"));
        panel.add(pessoaField);
        panel.add(new JLabel("Data:"));
        panel.add(dataField);
        int result = JOptionPane.showConfirmDialog(janela, panel, "Emprestar Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            ((IEmprestavel) item).registrarEmprestimo(pessoaField.getText(), dataField.getText());
            JOptionPane.showMessageDialog(janela, "Item emprestado!");
            atualizarLista();
        }
    }

    private void abrirModalCriarLook() {
        java.util.List<Roupas_nao_intimas> roupasNaoIntimas = new java.util.ArrayList<>();
        java.util.List<Acessorios> acessorios = new java.util.ArrayList<>();
        java.util.List<RoupaIntima> roupasIntimas = new java.util.ArrayList<>();
        for (Item item : gvp.getItens()) {
            if (item instanceof Roupas_nao_intimas) roupasNaoIntimas.add((Roupas_nao_intimas) item);
            if (item instanceof Acessorios) acessorios.add((Acessorios) item);
            if (item instanceof RoupaIntima) roupasIntimas.add((RoupaIntima) item);
        }
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JTextField nomeField = new JTextField();
        panel.add(new JLabel("Nome do Look:"));
        panel.add(nomeField);
        panel.add(new JLabel("Para selecionar mais de uma roupa ou acessório, utilize 'Ctrl+Click' (ou 'Cmd+Click' no Mac)."));
        // Roupas não íntimas
        panel.add(new JLabel("Selecione as roupas não íntimas:"));
        JList<Roupas_nao_intimas> listRoupas = new JList<>(roupasNaoIntimas.toArray(new Roupas_nao_intimas[0]));
        listRoupas.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Roupas_nao_intimas) {
                    setText(((Roupas_nao_intimas) value).getNome());
                }
                return this;
            }
        });
        listRoupas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel.add(new JScrollPane(listRoupas));
        // Roupa íntima
        panel.add(new JLabel("Selecione uma roupa íntima:"));
        JList<RoupaIntima> listIntima = new JList<>(roupasIntimas.toArray(new RoupaIntima[0]));
        listIntima.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof RoupaIntima) {
                    setText(((RoupaIntima) value).getNome());
                }
                return this;
            }
        });
        listIntima.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(listIntima));
        // Acessórios
        panel.add(new JLabel("Selecione os acessórios:"));
        JList<Acessorios> listAcessorios = new JList<>(acessorios.toArray(new Acessorios[0]));
        listAcessorios.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Acessorios) {
                    setText(((Acessorios) value).getNome());
                }
                return this;
            }
        });
        listAcessorios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel.add(new JScrollPane(listAcessorios));
        int result = JOptionPane.showConfirmDialog(janela, panel, "Criar Look", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            java.util.List<Roupas_nao_intimas> selecionadas = listRoupas.getSelectedValuesList();
            RoupaIntima intima = listIntima.getSelectedValue();
            java.util.List<Acessorios> selecionados = listAcessorios.getSelectedValuesList();
            if (nome.isEmpty() || intima == null || selecionadas.isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Preencha o nome, selecione pelo menos uma roupa não íntima e uma roupa íntima.");
                return;
            }
            Look look = new Look(nome, selecionadas, intima, selecionados);
            looks.add(look);
            JOptionPane.showMessageDialog(janela, "Look criado com sucesso!");
        }
    }

    private void atualizarListaLooks() {
        looksListPanel.removeAll();
        for (Look look : looks) {
            JPanel lookPanel = new JPanel();
            lookPanel.setLayout(new BorderLayout());
            JLabel nomeLabel = new JLabel("<html><b>" + look.getNome() + "</b> - Usado: " + look.getVezesUsado() + "x</html>");
            lookPanel.add(nomeLabel, BorderLayout.WEST);
            JPanel fotosPanel = new JPanel();
            fotosPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            // Roupas não íntimas
            for (Roupas_nao_intimas r : look.getRoupasNaoIntimas()) {
                if (r.getImgPath() != null && !r.getImgPath().isEmpty()) {
                    ImageIcon icon = new ImageIcon(r.getImgPath());
                    Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    fotosPanel.add(new JLabel(new ImageIcon(img)));
                } else {
                    fotosPanel.add(new JLabel("Sem imagem"));
                }
            }
            // Roupa íntima
            RoupaIntima intima = look.getRoupaIntima();
            if (intima != null) {
                if (intima.getImgPath() != null && !intima.getImgPath().isEmpty()) {
                    ImageIcon icon = new ImageIcon(intima.getImgPath());
                    Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    fotosPanel.add(new JLabel(new ImageIcon(img)));
                } else {
                    fotosPanel.add(new JLabel("Sem imagem"));
                }
            }
            // Acessórios
            for (Acessorios a : look.getAcessorios()) {
                if (a.getImgPath() != null && !a.getImgPath().isEmpty()) {
                    ImageIcon icon = new ImageIcon(a.getImgPath());
                    Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    fotosPanel.add(new JLabel(new ImageIcon(img)));
                } else {
                    fotosPanel.add(new JLabel("Sem imagem"));
                }
            }
            lookPanel.add(fotosPanel, BorderLayout.CENTER);
            JButton usarBtn = new JButton("Usar Look");
            usarBtn.addActionListener(e -> {
                look.incrementarUso();
                atualizarListaLooks();
            });
            lookPanel.add(usarBtn, BorderLayout.EAST);
            JButton editarBtn = new JButton("Editar");
            editarBtn.addActionListener(e -> abrirModalEditarLook(look));
            lookPanel.add(editarBtn, BorderLayout.SOUTH);
            JButton excluirBtn = new JButton("Excluir");
            excluirBtn.addActionListener(e -> {
                looks.remove(look);
                atualizarListaLooks();
            });
            lookPanel.add(excluirBtn, BorderLayout.NORTH);
            lookPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            lookPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
            looksListPanel.add(lookPanel);
        }
        looksListPanel.revalidate();
        looksListPanel.repaint();
    }

    private void abrirModalEditarLook(Look look) {
        java.util.List<Roupas_nao_intimas> roupasNaoIntimas = new java.util.ArrayList<>();
        java.util.List<Acessorios> acessorios = new java.util.ArrayList<>();
        java.util.List<RoupaIntima> roupasIntimas = new java.util.ArrayList<>();
        for (Item item : gvp.getItens()) {
            if (item instanceof Roupas_nao_intimas) roupasNaoIntimas.add((Roupas_nao_intimas) item);
            if (item instanceof Acessorios) acessorios.add((Acessorios) item);
            if (item instanceof RoupaIntima) roupasIntimas.add((RoupaIntima) item);
        }
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JTextField nomeField = new JTextField(look.getNome());
        panel.add(new JLabel("Nome do Look:"));
        panel.add(nomeField);
        panel.add(new JLabel("Para selecionar mais de uma roupa ou acessório, utilize 'Ctrl+Click' (ou 'Cmd+Click' no Mac)."));
        // Roupas não íntimas
        panel.add(new JLabel("Selecione as roupas não íntimas:"));
        JList<Roupas_nao_intimas> listRoupas = new JList<>(roupasNaoIntimas.toArray(new Roupas_nao_intimas[0]));
        listRoupas.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Roupas_nao_intimas) {
                    setText(((Roupas_nao_intimas) value).getNome());
                }
                return this;
            }
        });
        listRoupas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // Selecionar as roupas já presentes no look
        int[] selectedRoupas = roupasNaoIntimas.stream().mapToInt(r -> look.getRoupasNaoIntimas().contains(r) ? roupasNaoIntimas.indexOf(r) : -1).filter(i -> i >= 0).toArray();
        listRoupas.setSelectedIndices(selectedRoupas);
        panel.add(new JScrollPane(listRoupas));
        // Roupa íntima
        panel.add(new JLabel("Selecione uma roupa íntima:"));
        JList<RoupaIntima> listIntima = new JList<>(roupasIntimas.toArray(new RoupaIntima[0]));
        listIntima.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof RoupaIntima) {
                    setText(((RoupaIntima) value).getNome());
                }
                return this;
            }
        });
        listIntima.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Selecionar a roupa íntima já presente no look
        int selectedIntima = roupasIntimas.indexOf(look.getRoupaIntima());
        if (selectedIntima >= 0) listIntima.setSelectedIndex(selectedIntima);
        panel.add(new JScrollPane(listIntima));
        // Acessórios
        panel.add(new JLabel("Selecione os acessórios:"));
        JList<Acessorios> listAcessorios = new JList<>(acessorios.toArray(new Acessorios[0]));
        listAcessorios.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Acessorios) {
                    setText(((Acessorios) value).getNome());
                }
                return this;
            }
        });
        listAcessorios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // Selecionar os acessórios já presentes no look
        int[] selectedAcessorios = acessorios.stream().mapToInt(a -> look.getAcessorios().contains(a) ? acessorios.indexOf(a) : -1).filter(i -> i >= 0).toArray();
        listAcessorios.setSelectedIndices(selectedAcessorios);
        panel.add(new JScrollPane(listAcessorios));
        int result = JOptionPane.showConfirmDialog(janela, panel, "Editar Look", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            java.util.List<Roupas_nao_intimas> selecionadas = listRoupas.getSelectedValuesList();
            RoupaIntima intima = listIntima.getSelectedValue();
            java.util.List<Acessorios> selecionados = listAcessorios.getSelectedValuesList();
            if (nome.isEmpty() || intima == null || selecionadas.isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Preencha o nome, selecione pelo menos uma roupa não íntima e uma roupa íntima.");
                return;
            }
            look.setNome(nome);
            look.setRoupasNaoIntimas(selecionadas);
            look.setRoupaIntima(intima);
            look.setAcessorios(selecionados);
            atualizarListaLooks();
        }
    }

    private void abrirModalExcluirLook() {
        if (looks.isEmpty()) {
            JOptionPane.showMessageDialog(janela, "Nenhum look cadastrado para excluir.");
            return;
        }
        Look[] arrayLooks = looks.toArray(new Look[0]);
        JList<Look> list = new JList<>(arrayLooks);
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Look) {
                    setText(((Look) value).getNome());
                }
                return this;
            }
        });
        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(300, 200));
        int result = JOptionPane.showConfirmDialog(janela, scroll, "Selecione o look para excluir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Look selecionado = list.getSelectedValue();
            if (selecionado != null) {
                looks.remove(selecionado);
                atualizarListaLooks();
                JOptionPane.showMessageDialog(janela, "Look excluído com sucesso!");
            }
        }
    }

    private void atualizarEstatisticasBasicas(JTextArea statsTextArea) {
        int totalAcessorios = 0;
        int totalRoupas = 0;
        int totalIntimas = 0;
        int totalNaoIntimas = 0;
        for (Item item : gvp.getItens()) {
            if (item instanceof Acessorios) totalAcessorios++;
            if (item instanceof Roupas) totalRoupas++;
            if (item instanceof RoupaIntima) totalIntimas++;
            if (item instanceof Roupas_nao_intimas) totalNaoIntimas++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Estatísticas básicas:\n");
        sb.append("Total de acessórios: ").append(totalAcessorios).append("\n");
        sb.append("Total de roupas: ").append(totalRoupas).append("\n");
        sb.append("Total de roupas íntimas: ").append(totalIntimas).append("\n");
        sb.append("Total de roupas não íntimas: ").append(totalNaoIntimas).append("\n");
        statsTextArea.setText(sb.toString());
    }
}
