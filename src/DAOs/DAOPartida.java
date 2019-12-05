package DAOs;

import Entidades.Partida;
import java.util.ArrayList;
import java.util.List;

public class DAOPartida extends DAOGenerico<Partida> {

    private final List<Partida> lista = new ArrayList<>();

    public DAOPartida() {
        super(Partida.class);
    }

    public int autoIdPartida() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPartida) FROM Partida e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Partida> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Partida e WHERE e.idPartida LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Partida> listById(int id) {
        return em.createQuery("SELECT e FROM Partida + e WHERE e.nomePartida= :id").setParameter("id", id).getResultList();
    }

    public List<Partida> listInOrderNome() {
        return em.createQuery("SELECT e FROM Partida e ORDER BY e.nomePartida").getResultList();
    }

    public List<Partida> listInOrderId() {
        return em.createQuery("SELECT e FROM Partida e ORDER BY e.idPartida").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Partida> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdPartida() + "-" + lf.get(i).getData());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPartida daoPartida = new DAOPartida();
        List<Partida> listaPartida = daoPartida.list();
        for (Partida partida : listaPartida) {
            System.out.println(partida.getIdPartida()+ "-" + partida.getData());
        }
    }
}
