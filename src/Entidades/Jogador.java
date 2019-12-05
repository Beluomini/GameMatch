/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "jogador")
@NamedQueries({
    @NamedQuery(name = "Jogador.findAll", query = "SELECT j FROM Jogador j")})
public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_jogador")
    private Integer idJogador;
    @Basic(optional = false)
    @Column(name = "nome_jogador")
    private String nomeJogador;
    @Basic(optional = false)
    @Column(name = "idade_jogador")
    private int idadeJogador;
    @Basic(optional = false)
    @Column(name = "email_usuario")
    private String emailUsuario;
    @Basic(optional = false)
    @Column(name = "senha_jogador")
    private String senhaJogador;
    @JoinTable(name = "time_has_jogador", joinColumns = {
        @JoinColumn(name = "jogador_id_jogador", referencedColumnName = "id_jogador")}, inverseJoinColumns = {
        @JoinColumn(name = "time_id_time", referencedColumnName = "id_time")})
    @ManyToMany
    private List<Time> timeList;

    public Jogador() {
    }

    public Jogador(Integer idJogador) {
        this.idJogador = idJogador;
    }

    public Jogador(Integer idJogador, String nomeJogador, int idadeJogador, String emailUsuario, String senhaJogador) {
        this.idJogador = idJogador;
        this.nomeJogador = nomeJogador;
        this.idadeJogador = idadeJogador;
        this.emailUsuario = emailUsuario;
        this.senhaJogador = senhaJogador;
    }

    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getIdadeJogador() {
        return idadeJogador;
    }

    public void setIdadeJogador(int idadeJogador) {
        this.idadeJogador = idadeJogador;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaJogador() {
        return senhaJogador;
    }

    public void setSenhaJogador(String senhaJogador) {
        this.senhaJogador = senhaJogador;
    }

    public List<Time> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJogador != null ? idJogador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jogador)) {
            return false;
        }
        Jogador other = (Jogador) object;
        if ((this.idJogador == null && other.idJogador != null) || (this.idJogador != null && !this.idJogador.equals(other.idJogador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Jogador[ idJogador=" + idJogador + " ]";
    }
    
}
