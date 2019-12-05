package DAOs;

import Entidades.Esporte;
import java.util.ArrayList;
import java.util.List;

public class DAOEsporte extends DAOGenerico<Esporte> {

    private final List<Esporte> lista = new ArrayList<>();

    public DAOEsporte() {
        super(Esporte.class);
    }

    public int autoIdEsporte() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idEsporte) FROM Esporte e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Esporte> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Esporte e WHERE e.idEsporte LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Esporte> listById(int id) {
        return em.createQuery("SELECT e FROM Esporte + e WHERE e.nome= :id").setParameter("id", id).getResultList();
    }

    public List<Esporte> listInOrderNome() {
        return em.createQuery("SELECT e FROM Esporte e ORDER BY e.nome").getResultList();
    }

    public List<Esporte> listInOrderId() {
        return em.createQuery("SELECT e FROM Esporte e ORDER BY e.idEsporte").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Esporte> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdEsporte() + "-" + lf.get(i).getNomeEsporte());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOEsporte daoEsporte = new DAOEsporte();
        List<Esporte> listaEsporte = daoEsporte.list();
        for (Esporte esporte : listaEsporte) {
            System.out.println(esporte.getIdEsporte() + "-" + esporte.getNomeEsporte());
        }
    }
}
