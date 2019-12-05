/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "esporte")
@NamedQueries({
    @NamedQuery(name = "Esporte.findAll", query = "SELECT e FROM Esporte e")})
public class Esporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_esporte")
    private Integer idEsporte;
    @Basic(optional = false)
    @Column(name = "nome_esporte")
    private String nomeEsporte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEsporte")
    private List<Time> timeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEsporte")
    private List<Partida> partidaList;

    public Esporte() {
    }

    public Esporte(Integer idEsporte) {
        this.idEsporte = idEsporte;
    }

    public Esporte(Integer idEsporte, String nomeEsporte) {
        this.idEsporte = idEsporte;
        this.nomeEsporte = nomeEsporte;
    }

    public Integer getIdEsporte() {
        return idEsporte;
    }

    public void setIdEsporte(Integer idEsporte) {
        this.idEsporte = idEsporte;
    }

    public String getNomeEsporte() {
        return nomeEsporte;
    }

    public void setNomeEsporte(String nomeEsporte) {
        this.nomeEsporte = nomeEsporte;
    }

    public List<Time> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;
    }

    public List<Partida> getPartidaList() {
        return partidaList;
    }

    public void setPartidaList(List<Partida> partidaList) {
        this.partidaList = partidaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEsporte != null ? idEsporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Esporte)) {
            return false;
        }
        Esporte other = (Esporte) object;
        if ((this.idEsporte == null && other.idEsporte != null) || (this.idEsporte != null && !this.idEsporte.equals(other.idEsporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Esporte[ idEsporte=" + idEsporte + " ]";
    }
    
}
