package DAOs;

import Entidades.Time;
import java.util.ArrayList;
import java.util.List;

public class DAOTime extends DAOGenerico<Time> {

    private final List<Time> lista = new ArrayList<>();

    public DAOTime() {
        super(Time.class);
    }

    public int autoIdTime() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTime) FROM Time e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Time> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Time e WHERE e.idTime LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Time> listById(int id) {
        return em.createQuery("SELECT e FROM Time + e WHERE e.nomeTime= :id").setParameter("id", id).getResultList();
    }

    public List<Time> listInOrderNome() {
        return em.createQuery("SELECT e FROM Time e ORDER BY e.nomeTime").getResultList();
    }

    public List<Time> listInOrderId() {
        return em.createQuery("SELECT e FROM Time e ORDER BY e.idTime").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Time> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTime() + "-" + lf.get(i).getNomeTime());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOTime daoTime = new DAOTime();
        List<Time> listaTime = daoTime.list();
        for (Time time : listaTime) {
            System.out.println(time.getIdTime()+ "-" + time.getNomeTime());
        }
    }
}
