package GUIs;

import DAOs.DAOEsporte;
import DAOs.DAOGenerico;
import DAOs.DAOJogador;
import Entidades.Esporte;
import Entidades.Jogador;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class GUIMenu extends JFrame {

    private Container cp;
    private Point p;
    private CardLayout cardLayout = new CardLayout();
    ;
    
    private JPanel pnPrincipal = new JPanel(cardLayout);
    private JPanel pnMenu = new JPanel(new BorderLayout());
    private JPanel pnEsporte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    private JPanel pnNorteMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnCentroMenu = new JPanel(new GridLayout(1, 3, 10, 10));
    
    private JPanel pnNorteEsporte = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnCentroEsporte = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private JLabel lbMenu = new JLabel("O que você quer fazer?");
    
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);

    private JButton btCriarTime = new JButton("Criar Time");
    private JButton btEntrarTime = new JButton("Entrar em um time");
    private JButton btEsportes = new JButton("Ver Esportes");
    private JButton btPartida = new JButton("Criar Partida");
    private JButton btVoltar = new JButton("Voltar");

    public GUIMenu(Dimension dimensao, Jogador logado) {
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("Game Match \0/");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnPrincipal, BorderLayout.CENTER);
        pnPrincipal.add(pnMenu, "pnMenu");
        pnPrincipal.add(pnEsporte, "pnEsporte");
        
        pnMenu.add(pnNorteMenu, BorderLayout.NORTH);
        pnMenu.add(pnCentroMenu, BorderLayout.CENTER);
        
        pnNorteMenu.add(lbMenu);
        
        pnCentroMenu.add(btCriarTime);
        pnCentroMenu.add(btEntrarTime);
        pnCentroMenu.add(btEsportes);
        pnCentroMenu.add(btPartida);
        
        JScrollPane scroll = new JScrollPane();
        DAOEsporte daoEsporte = new DAOEsporte();
        List<Esporte> esportes = daoEsporte.listInOrderId();
        
        String[] colunas = new String[]{"idEsporte", "nomeEsporte",};
        String[][] dados = new String[0][2];

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);
        scroll.setViewportView(tabela);
        
        for (Esporte e : esportes) {
            String [] linha = new String []{String.valueOf(e.getIdEsporte()), e.getNomeEsporte()};
            model.addRow(linha);
        }
        
        pnEsporte.add(pnNorteEsporte);
        pnEsporte.add(pnCentroEsporte);
        
        pnNorteEsporte.add(btVoltar);
        pnCentroEsporte.add(scroll);
        
        btCriarTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                dispose();
                new GUITime(new Dimension(550, 200), logado);
            }
        });

        btEntrarTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                dispose();
                new GUIEntrar(new Dimension(500, 300), logado);
            }
        });

        btEsportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnEsporte");
            }
        });
        
        btPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                dispose();
                new GUIPartida(new Dimension(500, 300), logado);
            }
        });
        
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnMenu");
            }
        });

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    } //fecha o contrutor
}