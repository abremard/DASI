/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Iyad
 */

@Entity
public class Consultation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentaire;
    private Statut statut;
    @Temporal(TemporalType.TIMESTAMP)
    private Date temps;

    public Consultation() {
        this.statut = Statut.PENDING;
        this.commentaire = "";
        this.temps = new Date();
    }

    public Long getId() {
        return id;
    }

    public Date getTemps() {
        return temps;
    }

    public Statut getStatut() {
        return statut;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
    
    @Override
    public String toString() {
        return 
            "Consultation : id=" + id +
            ", commentaire=" + commentaire +
            ", statut=" + statut +
            ", temps=" + temps.toString();
    }

}
