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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "time")
@NamedQueries({
    @NamedQuery(name = "Time.findAll", query = "SELECT t FROM Time t")})
public class Time implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_time")
    private Integer idTime;
    @Column(name = "qtdParticipantes_time")
    private Integer qtdParticipantestime;
    @Column(name = "nome_time")
    private String nomeTime;
    @ManyToMany(mappedBy = "timeList")
    private List<Jogador> jogadorList;
    @JoinColumn(name = "id_esporte", referencedColumnName = "id_esporte")
    @ManyToOne(optional = false)
    private Esporte idEsporte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTime1")
    private List<Partida> partidaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTime2")
    private List<Partida> partidaList1;

    public Time() {
    }

    public Time(Integer idTime) {
        this.idTime = idTime;
    }

    public Integer getIdTime() {
        return idTime;
    }

    public void setIdTime(Integer idTime) {
        this.idTime = idTime;
    }

    public Integer getQtdParticipantestime() {
        return qtdParticipantestime;
    }

    public void setQtdParticipantestime(Integer qtdParticipantestime) {
        this.qtdParticipantestime = qtdParticipantestime;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    public List<Jogador> getJogadorList() {
        return jogadorList;
    }

    public void setJogadorList(List<Jogador> jogadorList) {
        this.jogadorList = jogadorList;
    }

    public Esporte getIdEsporte() {
        return idEsporte;
    }

    public void setIdEsporte(Esporte idEsporte) {
        this.idEsporte = idEsporte;
    }

    public List<Partida> getPartidaList() {
        return partidaList;
    }

    public void setPartidaList(List<Partida> partidaList) {
        this.partidaList = partidaList;
    }

    public List<Partida> getPartidaList1() {
        return partidaList1;
    }

    public void setPartidaList1(List<Partida> partidaList1) {
        this.partidaList1 = partidaList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTime != null ? idTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Time)) {
            return false;
        }
        Time other = (Time) object;
        if ((this.idTime == null && other.idTime != null) || (this.idTime != null && !this.idTime.equals(other.idTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Time[ idTime=" + idTime + " ]";
    }
    
}
