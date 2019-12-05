package DAOs;

import Entidades.Jogador;
import java.util.ArrayList;
import java.util.List;

public class DAOJogador extends DAOGenerico<Jogador> {

    private final List<Jogador> lista = new ArrayList<>();

    public DAOJogador() {
        super(Jogador.class);
    }

    public int autoIdJogador() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idJogador) FROM Jogador e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Jogador> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Jogador e WHERE e.idJogador LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Jogador> listById(int id) {
        return em.createQuery("SELECT e FROM Jogador + e WHERE e.nomeJogador= :id").setParameter("id", id).getResultList();
    }

    public List<Jogador> listInOrderNome() {
        return em.createQuery("SELECT e FROM Jogador e ORDER BY e.nomeJogador").getResultList();
    }

    public List<Jogador> listInOrderId() {
        return em.createQuery("SELECT e FROM Jogador e ORDER BY e.idJogador").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Jogador> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdJogador() + "-" + lf.get(i).getNomeJogador());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOJogador daoJogador = new DAOJogador();
        List<Jogador> listaJogador = daoJogador.list();
        for (Jogador jogador : listaJogador) {
            System.out.println(jogador.getIdJogador()+ "-" + jogador.getNomeJogador());
        }
    }
}
