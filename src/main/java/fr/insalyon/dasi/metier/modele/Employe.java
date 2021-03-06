/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Iyad
 */
@Entity
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean disponible;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String telephone;
    private String motDePasse;
    private String genre;
    private int nbConsultation;

    protected Employe() {
    }

    public Employe(String nom, String prenom, String mail, String telephone, String motDePasse, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.genre = genre;
        this.disponible = true;
    }

    public Long getId() {
        return id;
    }
    
    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public int getNbConsultation() {
        return nbConsultation;
    }

    public void setNbConsultation(int nbConsultation) {
        this.nbConsultation = nbConsultation;
    }

    @Override
    public String toString() {
        return 
            "Employe : id=" + id +
            ", disponible=" + disponible +
            ", nom=" + nom +
            ", prenom=" + prenom +
            ", mail=" + mail +
            ", telephone=" + telephone +
            ", motDePasse=" + motDePasse +
            ", genre=" + genre +    
            ", nbConsultation=" + nbConsultation;
    }
    
}