package GUIs;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import DAOs.DAOEsporte;
import DAOs.DAOGenerico;
import DAOs.DAOJogador;
import DAOs.DAOPartida;
import DAOs.DAOTime;
import Entidades.Esporte;
import GUIs.GUIMenu;
import Entidades.Jogador;
import Entidades.Partida;
import Entidades.Time;
import com.itextpdf.text.PageSize;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.scene.control.ComboBox;
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
import myTools.Ferramentas;
import myUtil.CentroDoMonitorMaior;
import myUtil.DateTextField;
import myUtil.MyDateTimeFormat;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class GUIPartida extends JFrame {

    private Container cp;
    private Point p;
    private CardLayout cardLayout = new CardLayout();
    private boolean inserindo;

    DAOTime daoTime = new DAOTime();
    DAOEsporte daoEsporte = new DAOEsporte();
    DAOJogador daoJogador = new DAOJogador();
    DAOPartida daoPartida = new DAOPartida();

    JScrollPane scroll = new JScrollPane();

    Time timeBuscando = new Time();

    boolean esta = false;

    private JPanel pnPrincipal = new JPanel(cardLayout);
    private JPanel pnMenu = new JPanel(new BorderLayout());
    private JPanel pnLista = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JPanel pnMenuNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnMenuMeio = new JPanel(new GridLayout(4, 1));

    private JPanel pnMenuCentro1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnMenuCentro2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnMenuCentro3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnMenuCentro4 = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JPanel pnListaNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnListaSul = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private JComboBox cbTimesPresente = new JComboBox();
    private JComboBox cbTimesContra = new JComboBox();
    private JComboBox cbEsportes = new JComboBox();

    private JLabel lbTimePresente = new JLabel("Seu time");
    private JLabel lbTimeContra = new JLabel("Outro time");
    private JLabel lbData = new JLabel("DATA");
    private JLabel lbEsportes = new JLabel("ESPORTES");

    private DateTextField tfDataPartida = new DateTextField();

    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);

    Icon imgVoltar = new ImageIcon(getClass().getResource("/Imagens/voltarMenu.png"));
    Icon imgCancelar = new ImageIcon(getClass().getResource("/Imagens/cancelar.png"));
    Icon imgSalvar = new ImageIcon(getClass().getResource("/Imagens/salvar.png"));
    Icon imgListar = new ImageIcon(getClass().getResource("/Imagens/listar.png"));

    private JButton btListar = new JButton(imgListar);

    private JButton btOk1 = new JButton("OK");
    private JButton btOk2 = new JButton("OK");
    private JButton btCancelar1 = new JButton("Cancelar");
    private JButton btCancelar2 = new JButton("Cancelar");
    private JButton btSalvar = new JButton(imgSalvar);
    private JButton btVoltar2 = new JButton(imgVoltar);
    private JButton btVoltar = new JButton(imgVoltar);
    private JButton btGravar = new JButton();

    private void limparEDesativarTF() {
    }

    public GUIPartida(Dimension dimensao, Jogador logado) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("Game Match \0/");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        MyDateTimeFormat myDateTimeFormat = new MyDateTimeFormat();
        Ferramentas fer = new Ferramentas();

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnPrincipal, BorderLayout.CENTER);
        pnPrincipal.add(pnMenu, "pnMenu");
        pnPrincipal.add(pnLista, "pnLista");

        //--------------------------LISTA---------------------------
        pnLista.add(pnListaNorte);
        pnLista.add(pnListaSul);

        //List<Partida> todosPartidas = daoPartida.listInOrderId();
        List<Time> todosTimes = daoTime.listInOrderId();
        List<Time> timesLogado = logado.getTimeList();

        System.out.println(logado);

        pnListaNorte.add(btVoltar2);

        //------------------------------------MENU--------------------------
        pnMenu.add(pnMenuNorte, BorderLayout.NORTH);
        pnMenu.add(pnMenuMeio);

        pnMenuMeio.add(pnMenuCentro1);
        pnMenuMeio.add(pnMenuCentro2);
        pnMenuMeio.add(pnMenuCentro3);
        pnMenuMeio.add(pnMenuCentro4);

        pnMenuNorte.add(btVoltar);
        btVoltar.setToolTipText("Voltar para menu");
        pnMenuNorte.add(btListar);
        btListar.setToolTipText("Listar times");
        pnMenuNorte.add(btSalvar);
        btSalvar.setToolTipText("Salvar partida");

        pnMenuCentro1.add(lbTimePresente);
        pnMenuCentro1.add(cbTimesPresente);
        pnMenuCentro1.add(btOk1);
        pnMenuCentro1.add(btCancelar1);

        pnMenuCentro2.add(lbTimeContra);
        pnMenuCentro2.add(cbTimesContra);
        pnMenuCentro2.add(btOk2);
        pnMenuCentro2.add(btCancelar2);

        pnMenuCentro3.add(lbData);
        pnMenuCentro3.add(tfDataPartida);
        pnMenuCentro4.add(lbEsportes);
        pnMenuCentro4.add(cbEsportes);

        btCancelar1.setVisible(false);
        btCancelar2.setVisible(false);
        btOk2.setVisible(false);
        cbTimesContra.setEnabled(false);
        btSalvar.setVisible(false);

        List<Esporte> esportes = daoEsporte.listInOrderId();
        for (Esporte e : esportes) {
            String esport = e.getIdEsporte() + " - " + e.getNomeEsporte();
            cbEsportes.addItem(esport);
        }

        for (Time t : timesLogado) {
            String time2 = t.getIdTime() + " - " + t.getNomeTime();
            cbTimesPresente.addItem(time2);
        }

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

        btOk1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                btOk1.setVisible(false);
                btCancelar1.setVisible(true);
                btOk2.setVisible(true);
                cbTimesContra.setEnabled(true);
                cbTimesPresente.setEnabled(false);
                for (Time t : todosTimes) {
                    char chTime1[] = {cbTimesPresente.getSelectedItem().toString().charAt(0)};
                    String strTime1 = String.copyValueOf(chTime1);
                    if (t != daoTime.obter(Integer.valueOf(strTime1))) {
                        String time1 = t.getIdTime() + " - " + t.getNomeTime();
                        cbTimesContra.addItem(time1);
                    }
                }
            }
        });

        btOk2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                btOk2.setVisible(false);
                btCancelar2.setVisible(true);
                btSalvar.setVisible(true);
                cbTimesContra.setEnabled(false);
                //System.out.println(cbTimesPresente.getSelectedItem());
            }
        });

        btCancelar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                btCancelar1.setVisible(false);
                btOk2.setVisible(false);
                btCancelar2.setVisible(false);
                cbTimesContra.setEnabled(false);
                btOk1.setVisible(true);
                btSalvar.setVisible(false);
                cbTimesContra.removeAllItems();
                cbTimesPresente.setEnabled(true);
                cbTimesContra.setEnabled(false);
            }
        });

        btCancelar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                btCancelar2.setVisible(false);
                btOk2.setVisible(true);
                btSalvar.setVisible(false);
                cbTimesContra.setEnabled(true);
            }
        });

        //=============== B O T O E S ======================================================================
        btSalvar.addActionListener((ActionEvent e) -> {
            Date hoje = new Date();

            Partida p = new Partida();

            p.setIdPartida(daoPartida.autoIdPartida());
            p.setData(tfDataPartida.getDate());

            char chTime1[] = {cbTimesPresente.getSelectedItem().toString().charAt(0)};
            String strTime1 = String.copyValueOf(chTime1);
            p.setIdTime1(daoTime.obter(Integer.valueOf(strTime1)));

            char chTime2[] = {cbTimesContra.getSelectedItem().toString().charAt(0)};
            String strTime2 = String.copyValueOf(chTime2);
            p.setIdTime2(daoTime.obter(Integer.valueOf(strTime2)));

            char chEsporte[] = {cbEsportes.getSelectedItem().toString().charAt(0)};
            String strEsporte = String.copyValueOf(chEsporte);
            p.setIdEsporte(daoEsporte.obter(Integer.valueOf(strEsporte)));

            System.out.println(p.getIdTime1());
            System.out.println(p.getIdTime2());
            System.out.println(p.getIdEsporte());
            System.out.println(p.getIdEsporte());

            daoPartida.inserir(p);

            JOptionPane.showMessageDialog(pnMenuMeio, "Partida Criada :)");

//-----------------------CRIAR PDF AQUI------------------------------------------------
            
            Document document = new Document();

            try {

                PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\lucas\\Documents\\NetBeansProjects\\GameMatch/ComprovanteDePartida" + p.getIdPartida() + ".pdf"));

                document.open();

                document.setPageSize(PageSize.A4);
                document.newPage();

                document.add(new Paragraph("Perfil do usuário ------------------------------------------"));
                document.add(new Paragraph("Nome: " + logado.getNomeJogador()));
                document.add(new Paragraph("Email: " + logado.getEmailUsuario()));
                document.add(new Paragraph("Idade: " + logado.getIdadeJogador()));
                document.add(new Paragraph("Dados da partida -------------------------------------------"));
                document.add(new Paragraph("ID: " + p.getIdPartida()));
                document.add(new Paragraph("Time 1: " + p.getIdTime1().getNomeTime()));
                document.add(new Paragraph("Time 2: " + p.getIdTime2().getNomeTime()));
                document.add(new Paragraph("Data: " + p.getData()));
                document.add(new Paragraph("Esporte: " + p.getIdEsporte().getNomeEsporte()));

            } catch (DocumentException de) {
                System.err.println(de.getMessage());
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }

            // step 5: we close the document
            document.close();
            
//--------------------MANDA EMAIL AQUI -------------------------------------------------

            Properties props = new Properties();
            /**
             * Parâmetros de conexão com servidor Gmail
             */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("uzumcorp@gmail.com",
                                    "uzumjamal");
                        }
                    });

            /**
             * Ativa Debug para sessão
             */
            session.setDebug(true);

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("uzumcorp@gmail.com"));
      //Remetente

                Address[] toUser = InternetAddress //Destinatário(s)
                        .parse(logado.getEmailUsuario());

                message.setRecipients(Message.RecipientType.TO, toUser);
                message.setSubject("Comprovante de Partida Gamematch");//Assunto
                message.setText("Segue em anexo o comprovante de sua partida");
                FileDataSource fds = new FileDataSource("ComprovanteDePartida" + p.getIdPartida() + ".pdf");
                message.setDataHandler(new DataHandler(fds));
                message.setFileName(fds.getName());
                /**
                 * Método para enviar a mensagem criada
                 */
                Transport.send(message);

            } catch (MessagingException x) {
                throw new RuntimeException(x);
            }

            //VOLTA PARA TELA INICIAL-------------------------------------------------
            
            btSalvar.setVisible(false);
            btListar.setVisible(true);
            btOk1.setVisible(true);
            btOk2.setVisible(false);
            btCancelar1.setVisible(false);
            btCancelar2.setVisible(false);
            cbTimesContra.setEnabled(false);
            cbTimesPresente.setEnabled(true);
        }
        );

        //======================= LISTAR =============================================
        btListar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
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
                }
        );

        btVoltar2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        Date hoje = new Date();
                        cardLayout.show(pnPrincipal, "pnMenu");
                    }
                }
        );

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);

        setLocation(p);

        setVisible(
                true);
    } //fecha o contrutor
}
