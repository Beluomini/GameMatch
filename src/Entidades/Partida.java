/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "partida")
@NamedQueries({
    @NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p")})
public class Partida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_partida")
    private Integer idPartida;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "id_esporte", referencedColumnName = "id_esporte")
    @ManyToOne(optional = false)
    private Esporte idEsporte;
    @JoinColumn(name = "id_time1", referencedColumnName = "id_time")
    @ManyToOne(optional = false)
    private Time idTime1;
    @JoinColumn(name = "id_time2", referencedColumnName = "id_time")
    @ManyToOne(optional = false)
    private Time idTime2;

    public Partida() {
    }

    public Partida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Esporte getIdEsporte() {
        return idEsporte;
    }

    public void setIdEsporte(Esporte idEsporte) {
        this.idEsporte = idEsporte;
    }

    public Time getIdTime1() {
        return idTime1;
    }

    public void setIdTime1(Time idTime1) {
        this.idTime1 = idTime1;
    }

    public Time getIdTime2() {
        return idTime2;
    }

    public void setIdTime2(Time idTime2) {
        this.idTime2 = idTime2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartida != null ? idPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partida)) {
            return false;
        }
        Partida other = (Partida) object;
        if ((this.idPartida == null && other.idPartida != null) || (this.idPartida != null && !this.idPartida.equals(other.idPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Partida[ idPartida=" + idPartida + " ]";
    }
    
}
