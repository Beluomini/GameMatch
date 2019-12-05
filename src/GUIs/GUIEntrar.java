package GUIs;

import DAOs.DAOEsporte;
import DAOs.DAOGenerico;
import DAOs.DAOJogador;
import DAOs.DAOTime;
import Entidades.Esporte;
import GUIs.GUIMenu;
import Entidades.Jogador;
import Entidades.Time;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import myUtil.CentroDoMonitorMaior;

public class GUIEntrar extends JFrame {

    private Container cp;
    private Point p;
    private CardLayout cardLayout = new CardLayout();
    private boolean inserindo;

    DAOTime daoTime = new DAOTime();
    DAOEsporte daoEsporte = new DAOEsporte();
    DAOJogador daoJogador = new DAOJogador();

    JScrollPane scroll = new JScrollPane();

    Time timeBuscando = new Time();

    boolean esta = false;

    private JPanel pnPrincipal = new JPanel(cardLayout);
    private JPanel pnMenu = new JPanel(new BorderLayout());
    private JPanel pnLista = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JPanel pnMenuNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnMenuCentro = new JPanel(new GridLayout(4, 2, 0, 10));
    private JPanel pnAddParticipante = new JPanel(new FlowLayout());
    private JPanel pnNorteRegistrar = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JPanel pnListaNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnListaSul = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private JLabel lbIdTime = new JLabel("Digite o id do time:");
    private JTextField tfIdTime = new JTextField(5);
    private JLabel lbNomeTime = new JLabel("Nome:");
    private JTextField tfNomeTime = new JTextField(20);
    private JLabel lbQtdJogador = new JLabel("Quantidade de Troféis:");
    private JTextField tfQtdJogador = new JTextField(20);
    private JLabel lbIdEsporte = new JLabel("Esporte:");
    private JTextField tfIdEsporte = new JTextField(20);
    private JLabel lbPresente = new JLabel("Esta no time:");
    private JTextField tfPresente = new JTextField(20);

    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);

    private JButton btVoltar2 = new JButton("Voltar");

    Icon imgBuscar = new ImageIcon(getClass().getResource("/Imagens/lupa.png"));
    private JButton btBuscar = new JButton(imgBuscar);
    private JButton btLimpar = new JButton(imgBuscar);
    Icon imgColar = new ImageIcon(getClass().getResource("/Imagens/entrar.png"));
    private JButton btEntrar = new JButton(imgColar);
    Icon imgDescolar = new ImageIcon(getClass().getResource("/Imagens/sair.png"));
    private JButton btSair = new JButton(imgDescolar);
    Icon imgListar = new ImageIcon(getClass().getResource("/Imagens/listar.png"));
    private JButton btListar = new JButton(imgListar);
    Icon imgSalvar = new ImageIcon(getClass().getResource("/Imagens/salvar.png"));
    private JButton btSalvar = new JButton(imgSalvar);
    Icon imgCancelar = new ImageIcon(getClass().getResource("/Imagens/cancelar.png"));
    private JButton btCancelar = new JButton(imgCancelar);
    Icon imgVoltar = new ImageIcon(getClass().getResource("/Imagens/voltarMenu.png"));
    private JButton btVoltar = new JButton(imgVoltar);
    private JButton btGravar = new JButton();

    private void limparEDesativarTF() {
        tfNomeTime.setText("");
        tfQtdJogador.setText("");
        tfNomeTime.setEditable(false);
        tfIdEsporte.setEditable(false);
        tfPresente.setEditable(false);
        tfQtdJogador.setEditable(false);
    }

    public GUIEntrar(Dimension dimensao, Jogador logado) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("Game Match \0/");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnPrincipal, BorderLayout.CENTER);
        pnPrincipal.add(pnMenu, "pnMenu");
        pnPrincipal.add(pnLista, "pnLista");

        //--------------------------LISTA---------------------------
        pnLista.add(pnListaNorte);
        pnLista.add(pnListaSul);

        List<Time> todosTimes = daoTime.listInOrderId();
        List<Time> timesLogado = logado.getTimeList();

        System.out.println(logado);

        pnListaNorte.add(btVoltar2);

        //------------------------------------MENU--------------------------
        pnMenu.add(pnMenuNorte, BorderLayout.NORTH);
        pnMenu.add(pnMenuCentro, BorderLayout.CENTER);

        pnMenuNorte.add(btVoltar);
        btVoltar.setToolTipText("Voltar para menu");
        pnMenuNorte.add(lbIdTime);
        pnMenuNorte.add(tfIdTime);
        pnMenuNorte.add(btBuscar);
        btBuscar.setToolTipText("Buscar");
        pnMenuNorte.add(btLimpar);
        btLimpar.setToolTipText("Pesquisar");
        pnMenuNorte.add(btEntrar);
        btEntrar.setToolTipText("Entrar");
        pnMenuNorte.add(btSair);
        btSair.setToolTipText("Sair");
        pnMenuNorte.add(btSalvar);
        btSalvar.setToolTipText("Confirmar");
        pnMenuNorte.add(btCancelar);
        btCancelar.setToolTipText("Cancelar");
        pnMenuNorte.add(btListar);
        btListar.setToolTipText("Listar times");

        pnMenuCentro.add(lbNomeTime);
        pnMenuCentro.add(tfNomeTime);
        pnMenuCentro.add(lbIdEsporte);
        pnMenuCentro.add(tfIdEsporte);
        pnMenuCentro.add(lbPresente);
        pnMenuCentro.add(tfPresente);
        pnMenuCentro.add(lbQtdJogador);
        pnMenuCentro.add(tfQtdJogador);

        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btEntrar.setVisible(false);
        btSair.setVisible(false);
        btLimpar.setVisible(false);

        tfNomeTime.setEditable(false);
        tfIdEsporte.setEditable(false);
        tfQtdJogador.setEditable(false);
        tfPresente.setEditable(false);

        //============================== B O T Õ E S =============================================
        //=========================BOTAO VOLTAR MENU====================================
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                dispose();
                new GUIMenu(new Dimension(500, 200), logado);
            }
        });

// ------------------------BOTAO LIMPAR-----------------------------------------
        btLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                tfIdTime.setEditable(true);
                tfIdTime.setBackground(Color.WHITE);
                tfPresente.setBackground(null);
                tfPresente.setText("");
                btLimpar.setVisible(false);
                btBuscar.setVisible(true);
                btCancelar.setVisible(false);
                btEntrar.setVisible(false);
                btSair.setVisible(false);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
            }
        });

// ------------------------BOTAO BUSCAR ----------------------------------------        
        btBuscar.addActionListener((ActionEvent e) -> {
            Date hoje = new Date();
            boolean esta = false;
            boolean timeExiste = false;
            if (tfIdTime.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(pnMenu, "Pensei que você queria pesquisar algo... ");
            } else {
                try {
                    try {
                        tfIdTime.setBackground(Color.green);
                        timeBuscando = daoTime.obter(Integer.valueOf(tfIdTime.getText()));
                        for (Time t : todosTimes) {
                            if (timeBuscando == null) {//nao achou
                                tfIdTime.setText("");
                                tfIdTime.setEditable(true);
                                tfIdTime.requestFocus();
                                
                            } else {
                                timeExiste = true;
                                tfNomeTime.setText(String.valueOf(timeBuscando.getNomeTime()));
                                tfQtdJogador.setText(String.valueOf(timeBuscando.getQtdParticipantestime()));
                                tfIdEsporte.setText(String.valueOf(timeBuscando.getIdEsporte().getNomeEsporte()));

                                for (Time u : timesLogado) {
                                    if (timeBuscando == u) { //achou e está
                                        esta = true;
                                    }
                                }
                            }
                        }
                        if (timeExiste){
                            if (esta) {
                                btSair.setVisible(true);
                                btEntrar.setVisible(false);
                                tfPresente.setText("Sim!");
                                tfPresente.setBackground(Color.green);
                                
                            } else {
                                btEntrar.setVisible(true);
                                btSair.setVisible(false);
                                tfPresente.setBackground(Color.red);
                                tfPresente.setText("Não!");
                            }
                        }else{
                            JOptionPane.showMessageDialog(pnMenu, "O time não existe, digite outro numero");
                        }
                    } catch (Exception x) {
                        tfIdTime.selectAll();
                        tfIdTime.requestFocus();
                        tfIdTime.setBackground(Color.red);
                    }//else//else

                } catch (Exception x) {
                    JOptionPane.showMessageDialog(pnMenu, "O time está com codigo diferente");
                }
            }
            tfIdTime.selectAll();
            tfIdTime.requestFocus();
            tfIdTime.setEditable(false);
            btLimpar.setVisible(true);
            btBuscar.setVisible(false);
        });

//*********************** BOTÃO SALVAR ****************************************        
        btSalvar.addActionListener((ActionEvent e) -> {
            Date hoje = new Date();

            timesLogado.add(daoTime.obter(Integer.valueOf(tfIdTime.getText())));

            daoJogador.atualizar(logado);

            //voltar para tela inicial
            tfIdTime.requestFocus();
            tfIdTime.selectAll();
            tfIdTime.setEditable(true);
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btBuscar.setVisible(true);
            btLimpar.setVisible(false);
            btListar.setVisible(true);
            tfIdTime.setText("");
            limparEDesativarTF();
        });

//**************** Cancelar alteração ou inclusão ********************
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                tfIdTime.requestFocus();
                tfIdTime.selectAll();
                tfIdTime.setEditable(true);
                btEntrar.setVisible(false);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btLimpar.setVisible(false);
                btListar.setVisible(true);
                tfIdTime.setText("");
                limparEDesativarTF();
                tfIdTime.setBackground(Color.WHITE);
            }
        });

//||||||||||||||||||||||||||| BOTÃO COLAR |||||||||||||||||||||||||||||||||||
        btEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                btEntrar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btLimpar.setVisible(false);
                btListar.setVisible(false);
                inserindo = true;
            }
        });

//======================= LISTAR =============================================
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();

                String[] colunas = new String[]{"idTime", "nomeTime", "esporteTime"};
                String[][] dados = new String[0][3];

                DefaultTableModel model = new DefaultTableModel(dados, colunas);

                JTable tabela = new JTable(model);

                scroll.setViewportView(tabela);

                for (Time t : timesLogado) {
                    System.out.println(t);
                    String[] linha = new String[]{String.valueOf(t.getIdTime()), t.getNomeTime(), t.getIdEsporte().getNomeEsporte()};
                    model.addRow(linha);
                }

                pnListaSul.add(scroll);

                cardLayout.show(pnPrincipal, "pnLista");
            }
        });

        btVoltar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnMenu");
            }
        });
//***************************** DESCOLAR *************************************
        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                int dialogResult = JOptionPane.showConfirmDialog(pnMenu, "Vai descolar: "
                        + tfIdTime.getText() + "?", "Descolar do album", NORMAL);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    DAOTime daoTime = new DAOTime();

                    timesLogado.remove(daoTime.obter(Integer.valueOf(tfIdTime.getText())));

                    daoJogador.atualizar(logado);

                    btSair.setVisible(false);
                }
            }
        });

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    } //fecha o contrutor
}
