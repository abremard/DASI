<<<<<<< HEAD
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
=======
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
import javax.persistence.ManyToOne;
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
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Medium medium;
    
    public Consultation() {
        this.statut = Statut.PENDING;
    }

    public Consultation(String commentaire) {
        this.commentaire = commentaire;
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
    
}
>>>>>>> a94a8f1d61947f83f207d0284e1da3b2c3a6631d
