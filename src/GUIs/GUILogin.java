package GUIs;

import DAOs.DAOGenerico;
import DAOs.DAOJogador;
import GUIs.GUIMenu;
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
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import myUtil.CentroDoMonitorMaior;

public class GUILogin extends JFrame {

    private Container cp;
    private Point p;
    private CardLayout cardLayout = new CardLayout();

    boolean disponivel = true;
    boolean removivel = false;
    Jogador jogadorRemover;

    private JPanel pnPrincipal = new JPanel(cardLayout);
    private JPanel pnLogin = new JPanel(new BorderLayout());
    private JPanel pnRegistrar = new JPanel(new BorderLayout());
    private JPanel pnNorteLogin = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnCentroLogin = new JPanel(new GridLayout(2, 2, 0, 10));
    private JPanel pnSulLogin = new JPanel(new GridLayout(3, 3));
    private JPanel pnNorteRegistrar = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnCentroRegistrar = new JPanel(new GridLayout(4, 4, 0, 10));
    private JPanel pnSulRegistrar = new JPanel(new GridLayout(2, 1));

    private JLabel lbLogin = new JLabel("Login");
    private JLabel lbRegistrar = new JLabel("Registrar");
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();

    private JLabel lbEmailLogin = new JLabel("Email:");
    private JTextField tfEmailLogin = new JTextField(60);
    private JLabel lbSenhaLogin = new JLabel("Senha:");
    private JTextField tfSenhaLogin = new JTextField(15);

    private JLabel lbNomeRegistrar = new JLabel("Nome:");
    private JTextField tfNomeRegistrar = new JTextField(60);
    private JLabel lbIdadeRegistrar = new JLabel("Idade:");
    private JTextField tfIdadeRegistrar = new JTextField(3);
    private JLabel lbEmailRegistrar = new JLabel("Email:");
    private JTextField tfEmailRegistrar = new JTextField(60);
    private JLabel lbSenhaRegistrar = new JLabel("Senha:");
    private JTextField tfSenhaRegistrar = new JTextField(15);

    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);

    private JButton btEntrar = new JButton("Entrar");
    private JButton btCadastrar = new JButton("Quero me cadastrar");
    private JButton btTenhoConta = new JButton("Já tenho uma conta");
    private JButton btRegistrar = new JButton("Registrar");
    private JButton btDeletar = new JButton("Deletar conta");

    private JLabel vazio1 = new JLabel("");
    private JLabel vazio2 = new JLabel("");
    private JLabel vazio3 = new JLabel("");
    private JLabel vazio4 = new JLabel("");
    private JLabel vazio5 = new JLabel("");
    private JLabel vazio6 = new JLabel("");

    public GUILogin(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("Game Match \0/");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnPrincipal, BorderLayout.CENTER);
        pnPrincipal.add(pnLogin, "pnLogin");
        pnPrincipal.add(pnRegistrar, "pnRegistrar");

        //--------------------------LOGIN---------------------------
        pnLogin.add(pnNorteLogin, BorderLayout.NORTH);
        pnLogin.add(pnCentroLogin, BorderLayout.CENTER);
        pnLogin.add(pnSulLogin, BorderLayout.SOUTH);

        pnNorteLogin.add(lbLogin);

        pnCentroLogin.add(lbEmailLogin);
        pnCentroLogin.add(tfEmailLogin);
        pnCentroLogin.add(lbSenhaLogin);
        pnCentroLogin.add(tfSenhaLogin);

        tfEmailLogin.setText("jooj@gmail.com");
        tfSenhaLogin.setText("jooj123");

        pnSulLogin.add(vazio1);
        pnSulLogin.add(btEntrar);
        pnSulLogin.add(vazio2);
        pnSulLogin.add(vazio3);
        pnSulLogin.add(btCadastrar);
        pnSulLogin.add(vazio4);
        pnSulLogin.add(vazio5);
        pnSulLogin.add(btDeletar);
        pnSulLogin.add(vazio6);

        lbLogin.setFont(fonte);
        pnNorteLogin.setBackground(Color.LIGHT_GRAY);

        //--------------------------REGISTRAR---------------------------
        pnRegistrar.add(pnNorteRegistrar, BorderLayout.NORTH);
        pnRegistrar.add(pnCentroRegistrar, BorderLayout.CENTER);
        pnRegistrar.add(pnSulRegistrar, BorderLayout.SOUTH);

        pnNorteRegistrar.add(lbRegistrar);

        pnCentroRegistrar.add(lbNomeRegistrar);
        pnCentroRegistrar.add(tfNomeRegistrar);
        pnCentroRegistrar.add(lbEmailRegistrar);
        pnCentroRegistrar.add(tfEmailRegistrar);
        pnCentroRegistrar.add(lbSenhaRegistrar);
        pnCentroRegistrar.add(tfSenhaRegistrar);
        pnCentroRegistrar.add(lbIdadeRegistrar);
        pnCentroRegistrar.add(tfIdadeRegistrar);

        pnSulRegistrar.add(btRegistrar);
        pnSulRegistrar.add(btTenhoConta);

        lbRegistrar.setFont(fonte);
        pnNorteRegistrar.setBackground(Color.LIGHT_GRAY);

        //============================== B O T Õ E S =============================================
        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnRegistrar");
            }
        });

        btTenhoConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnLogin");
            }
        });

        btEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                DAOJogador daoJogador = new DAOJogador();
                List<Jogador> jogadores = daoJogador.listInOrderId();

                for (Jogador j : jogadores) {
                    if (tfEmailLogin.getText().equals(j.getEmailUsuario()) && tfSenhaLogin.getText().equals(j.getSenhaJogador())) {
                        daoJogador.setIdLogado(j.getIdJogador());
                        System.out.println(j.getNomeJogador());
                        System.out.println(daoJogador.getIdLogado());
                        dispose();
                        new GUIMenu(new Dimension(500, 200), j);
                    }
                }
            }
        });

        btRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                DAOJogador daoJogador = new DAOJogador();
                Jogador jogador = new Jogador();
                List<Jogador> jogadores = daoJogador.listInOrderId();

                for (Jogador j : jogadores) {
                    if (tfEmailRegistrar.getText().equals(j.getEmailUsuario())) {
                        JOptionPane.showMessageDialog(pnCentroRegistrar, "Já existe alguém usando esse e-mail :( digite outro");
                        disponivel = false;
                    }
                }

                if (disponivel) {
                    jogador.setIdJogador(daoJogador.autoIdJogador());
                    jogador.setNomeJogador(tfNomeRegistrar.getText());
                    jogador.setEmailUsuario(tfEmailRegistrar.getText());
                    jogador.setSenhaJogador(tfSenhaRegistrar.getText());
                    try {
                        jogador.setIdadeJogador(Integer.valueOf(tfIdadeRegistrar.getText()));
                        daoJogador.inserir(jogador);
                        dispose();
                        new GUIMenu(new Dimension(500, 200), jogador);
                    } catch (Exception x) {
                        JOptionPane.showMessageDialog(pnCentroRegistrar, "Digite a idade em numeros inteiros :)");
                        tfIdadeRegistrar.setText("");
                        tfIdadeRegistrar.requestFocus();
                    }
                }

            }
        });

        btDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                DAOJogador daoJogador = new DAOJogador();
                List<Jogador> jogadores = daoJogador.listInOrderId();

                for (Jogador j : jogadores) {
                    if (tfEmailLogin.getText().equals(j.getEmailUsuario()) && tfSenhaLogin.getText().equals(j.getSenhaJogador())) {
                        removivel = true;
                    }
                }

                if (removivel) {
                    try {
                        for (Jogador j : jogadores) {
                            if (tfEmailLogin.getText().equals(j.getEmailUsuario()) && tfSenhaLogin.getText().equals(j.getSenhaJogador())) {
                                jogadorRemover = j;
                            }
                        }
                        JOptionPane.showMessageDialog(pnCentroRegistrar, "Jogador de email: "+jogadorRemover.getEmailUsuario()+" foi removido com sucesso");
                        daoJogador.remover(jogadorRemover);
                        
                        tfEmailLogin.setText("");
                        tfSenhaLogin.setText("");
                        tfEmailLogin.requestFocus();
                        
                    } catch (Exception x) {
                        JOptionPane.showMessageDialog(pnCentroRegistrar, "Não foi possível deletar a conta, verifique os dados.");
                    }
                }
            }
        });

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    } //fecha o contrutor

    public static void main(String[] args) {
        new GUILogin(new Dimension(500, 300));
    }
}
