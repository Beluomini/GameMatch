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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class GUITime extends JFrame {

    private Container cp;
    private Point p;
    private CardLayout cardLayout = new CardLayout();
    private boolean inserindo;

    Time time = new Time();
    DAOTime daoTime = new DAOTime();

    private JPanel pnPrincipal = new JPanel(cardLayout);
    private JPanel pnMenu = new JPanel(new BorderLayout());
    private JPanel pnLista = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JPanel pnMenuNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnMenuCentro = new JPanel(new GridLayout(3, 2, 0, 10));
    private JPanel pnAddParticipante = new JPanel(new FlowLayout());
    private JPanel pnNorteRegistrar = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JPanel pnListaNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnListaSul = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private JLabel lbIdTime = new JLabel("Digite o id do time:");
    private JTextField tfIdTime = new JTextField(5);
    private JLabel lbNomeTime = new JLabel("Nome:");
    private JTextField tfNomeTime = new JTextField(20);
    private JLabel lbQtdJogador = new JLabel("Quantidade de Troféis:");
    private JTextField tfQtdJogador = new JTextField(5);
    private JLabel lbIdEsporte = new JLabel("Esporte:");

    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);

    Icon imgBuscar = new ImageIcon(getClass().getResource("/Imagens/lupa.png"));
    private JButton btBuscar = new JButton(imgBuscar);
    private JButton btLimpar = new JButton(imgBuscar);
    Icon imgColar = new ImageIcon(getClass().getResource("/Imagens/criar.png"));
    private JButton btCriar = new JButton(imgColar);
    Icon imgDescolar = new ImageIcon(getClass().getResource("/Imagens/lixeira.png"));
    private JButton btExcluir = new JButton(imgDescolar);
    Icon imgListar = new ImageIcon(getClass().getResource("/Imagens/listar.png"));
    private JButton btListar = new JButton(imgListar);
    Icon imgSalvar = new ImageIcon(getClass().getResource("/Imagens/salvar.png"));
    private JButton btSalvar = new JButton(imgSalvar);
    Icon imgCancelar = new ImageIcon(getClass().getResource("/Imagens/cancelar.png"));
    private JButton btCancelar = new JButton(imgCancelar);
    Icon imgVoltar = new ImageIcon(getClass().getResource("/Imagens/voltarMenu.png"));
    private JButton btVoltar = new JButton(imgVoltar);
    private JButton btGravar = new JButton();

    private JButton btVoltar2 = new JButton("Voltar");

    private JComboBox cbEsportes = new JComboBox();

    private void limparEAtivarTF() {
        tfNomeTime.setText("");
        tfQtdJogador.setText("");
        tfNomeTime.setEditable(true);
        tfQtdJogador.setEditable(true);
    }

    private void limparEDesativarTF() {
        tfNomeTime.setText("");
        tfQtdJogador.setText("");
        tfNomeTime.setEditable(false);
        cbEsportes.setEditable(false);
        tfQtdJogador.setEditable(false);
    }

    public GUITime(Dimension dimensao, Jogador logado) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("Game Match \0/");
        
        
        JScrollPane scroll = new JScrollPane();
        DAOEsporte daoEsporte = new DAOEsporte();
        List<Esporte> esportes = daoEsporte.listInOrderId();
        

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnPrincipal, BorderLayout.CENTER);
        pnPrincipal.add(pnMenu, "pnMenu");
        pnPrincipal.add(pnLista, "pnLista");
        
        pnLista.add(pnListaNorte);
        pnLista.add(pnListaSul);
        
        pnListaSul.add(scroll);
        pnListaNorte.add(btVoltar2);

        //--------------------------COMBOBOX ESPORTES---------------------------
        
        for (Esporte e : esportes) {
            cbEsportes.addItem(e.getNomeEsporte());
        }
        
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
        pnMenuNorte.add(btCriar);
        btCriar.setToolTipText("Criar");
        pnMenuNorte.add(btExcluir);
        btExcluir.setToolTipText("Excluir");
        pnMenuNorte.add(btSalvar);
        btSalvar.setToolTipText("Confirmar");
        pnMenuNorte.add(btCancelar);
        btCancelar.setToolTipText("Cancelar");
        pnMenuNorte.add(btListar);
        btListar.setToolTipText("Listar times");

        pnMenuCentro.add(lbNomeTime);
        pnMenuCentro.add(tfNomeTime);
        pnMenuCentro.add(lbIdEsporte);
        pnMenuCentro.add(cbEsportes);
        pnMenuCentro.add(lbQtdJogador);
        pnMenuCentro.add(tfQtdJogador);

        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btCriar.setVisible(false);
        btExcluir.setVisible(false);
        btLimpar.setVisible(false);

        tfNomeTime.setEditable(false);
        cbEsportes.setEditable(false);
        tfQtdJogador.setEditable(false);

//============================== B O T Õ E S =========================================================
//¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨BOTAO VOLTAR MENU¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨
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
                btLimpar.setVisible(false);
                btBuscar.setVisible(true);
                btCancelar.setVisible(false);
                btCriar.setVisible(false);
                btExcluir.setVisible(false);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
            }
        });

// ------------------------BOTAO BUSCAR ----------------------------------------        
        btBuscar.addActionListener((ActionEvent e) -> {
            Date hoje = new Date();
            if (tfIdTime.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(pnMenu, "Pensei que você queria pesquisar algo... ");
            } else {
                try {
                    try {
                        tfIdTime.setBackground(Color.green);
                        time = daoTime.obter(Integer.valueOf(tfIdTime.getText()));
                        if (time == null) { //nao achou
                            btCriar.setVisible(true);
                            btExcluir.setVisible(false);
                            limparEAtivarTF();
                        } else { //achou
                            btExcluir.setVisible(true);
                            btCriar.setVisible(false);
                            tfIdTime.setText(String.valueOf(time.getIdTime()));
                            tfNomeTime.setText(String.valueOf(time.getNomeTime()));
                            tfQtdJogador.setText(String.valueOf(time.getQtdParticipantestime()));
                            cbEsportes.setSelectedIndex(time.getIdEsporte().getIdEsporte() - 1);
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
            //para pesquisar na lista
            //precisamos do contato original (para depois modificar)
            if (inserindo) {
                time = new Time(); //criar um novo contato

            }
            Esporte esporte1 = daoEsporte.obter(cbEsportes.getSelectedIndex() + 1);
            //transfere os valores da GUI para classe contato
            time.setIdTime(Integer.valueOf(tfIdTime.getText()));
            time.setNomeTime(tfNomeTime.getText());
            time.setIdEsporte(esporte1);
            time.setQtdParticipantestime(Integer.valueOf(tfQtdJogador.getText()));

            if (inserindo) { //a variavel inserindo é preenchida nos
                daoTime.inserir(time);
            }

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

//**************** Cancelar alteração ou inclusão *******************************
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                tfIdTime.requestFocus();
                tfIdTime.selectAll();
                tfIdTime.setEditable(true);
                btCriar.setVisible(false);
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
        btCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                btCriar.setVisible(false);
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
                List<Time> times = daoTime.listInOrderId();

                String[] colunas = new String[]{"idTime", "nomeTime", "esporteTime"};
                String[][] dados = new String[0][3];

                DefaultTableModel model = new DefaultTableModel(dados, colunas);
                JTable tabela = new JTable(model);
                scroll.setViewportView(tabela);

                for (Time t : times) {
                    String[] linha = new String[]{String.valueOf(t.getIdTime()), t.getNomeTime(), t.getIdEsporte().getNomeEsporte()};
                    model.addRow(linha);
                }
                
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
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                int dialogResult = JOptionPane.showConfirmDialog(pnMenu, "Vai descolar: "
                        + tfIdTime.getText() + "?", "Descolar do album", NORMAL);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    daoTime.remover(time);
                    btExcluir.setVisible(false);
                    tfIdTime.setText("");
                    limparEDesativarTF();
                }
            }
        });

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    } 
}
