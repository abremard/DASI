/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Enumerated(EnumType.ORDINAL)
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
        this.commentaire = "";
        this.temps = new Date();
    }
    
    public Consultation(Client client, Medium medium) {
        this.client=client;
        this.medium=medium;
        this.statut = Statut.PENDING;
        this.commentaire = "";
        this.temps = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setTemps(Date temps) {
        this.temps = temps;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Client getClient() {
        return client;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
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
            ", employe=" + employe +    
            ", commentaire=" + commentaire +
            ", statut=" + statut +
            ",  client=" + client +
            ", medium=" + medium +    
            ", temps=" + temps.toString();
    }
    
}
