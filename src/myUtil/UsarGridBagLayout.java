package myUtil;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UsarGridBagLayout {

    JPanel painelAlvo = new JPanel();

    public UsarGridBagLayout(JPanel painel) {
        painelAlvo = painel;
        painelAlvo.setLayout(new GridBagLayout());
    }

    public void add(JLabel label, JComponent componente) {

        /**
         * Adiciona um label e um componente horizontalmente
         *
         * @param label que irá aparecer à esquerda
         * @param componente Componente de edição
         *
         * o painel que irá receber os componentes deve ter o layout setado para
         * GridBagLayout
         *
         * Por exemplo, painelCentro.setLayout(new GridBagLayout());
         *
         */
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.NONE;  //não "esticar"
        cons.anchor = GridBagConstraints.NORTHWEST; //canto superior esquerdo 
        cons.insets = new Insets(4, 4, 4, 4);  //distancia entre os componentes

        cons.weightx = 0;
        cons.gridwidth = 1;
        painelAlvo.add(label, cons);

        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 1;
        cons.gridwidth = GridBagConstraints.REMAINDER;
        painelAlvo.add(componente, cons);
    }

    /* 
    com cor
    
     */
    public void add(JLabel label, JComponent componente, Color cor) {

        /**
         * Adiciona um label e um componente horizontalmente
         *
         * @param label que irá aparecer à esquerda
         * @param componente Componente de edição
         *
         * o painel que irá receber os componentes deve ter o layout setado para
         * GridBagLayout
         *
         * Por exemplo, painelCentro.setLayout(new GridBagLayout());
         *
         */
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.NONE;  //não "esticar"
        cons.anchor = GridBagConstraints.NORTHWEST; //canto superior esquerdo 
        cons.insets = new Insets(4, 4, 4, 4);  //distancia entre os componentes

        cons.weightx = 0;
        cons.gridwidth = 1;
        painelAlvo.add(label, cons);

        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 1;
        cons.gridwidth = GridBagConstraints.REMAINDER;
        componente.setBackground(cor);
        painelAlvo.add(componente, cons);
    }

    /**
     * com cor
     */
    public void add(JLabel label1, JComponent componente, JLabel label2, JComponent componente2) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        cons.insets = new Insets(4, 4, 4, 4);

        cons.fill = GridBagConstraints.NONE;
        cons.anchor = GridBagConstraints.NORTHWEST;
        cons.weightx = 0;
        cons.gridwidth = 1;
        painelAlvo.add(label1, cons);

        cons.weightx = 1;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH;
        painelAlvo.add(componente, cons);

        cons.fill = GridBagConstraints.NONE;
        cons.weightx = 0;
        cons.gridwidth = 1;
        painelAlvo.add(label2, cons);

        cons.weightx = 1;
        cons.fill = GridBagConstraints.BOTH;
        cons.gridwidth = GridBagConstraints.REMAINDER;

        painelAlvo.add(componente2, cons);
    }

    /**
     * Adiciona um label e um componente horizontalmente. O componente ocupará
     * todo o resto da tela
     *
     * @param label String que irá aparecer no label
     * @param componente Componente de edição
     */
    public void add(JLabel label, JScrollPane componente) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.NONE;
        cons.anchor = GridBagConstraints.NORTHWEST;
        cons.insets = new Insets(4, 4, 4, 4);
        cons.weighty = 1;
        cons.gridheight = GridBagConstraints.REMAINDER;

        cons.weightx = 0;
        cons.gridwidth = 1;
        painelAlvo.add(label, cons);

        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 1;
        cons.gridwidth = GridBagConstraints.REMAINDER;
        painelAlvo.add(componente, cons);
    }

    public void add(DateTextField dateTextField, DateTextField dateTextField0, Color corPadrao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
