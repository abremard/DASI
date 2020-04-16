package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


/**
 *
 * @author DASI Team
 */
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeNaissance;
    @Column(unique = true)
    private String mail;
    private String adresse;
    private String telephone;
    private String signeZodiaque;
    private String signeAstro;
    private String couleurBonheur; 
    private String animalTotem;
    private String motDePasse;
    @OneToMany(mappedBy="client")
    private List<Consultation> HistoriqueConsultations;
    public Client() {
    }

    public Client(String nom, String prenom, Date dateDeNaissance, String mail, String adresse, String telephone, String motDePasse) { //JUSTE POUR TEST, A REMPLACER 
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.mail = mail;
        this.adresse = adresse;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        
    }
    /*public Client(String nom, String prenom, String mail, String adresse, String telephone, String motDePasse, Date dateDeNaissance) { //A BIEN IMPLEMENTER
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse = adresse;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.dateDeNaissance = dateDeNaissance;
        
    }*/
    
    public Long getId() {
        return id;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public String getSigneAstro() {
        return signeAstro;
    }

    public void setSigneAstro(String signeAstro) {
        this.signeAstro = signeAstro;
    }

    public String getCouleurBonheur() {
        return couleurBonheur;
    }

    public void setCouleurBonheur(String couleurBonheur) {
        this.couleurBonheur = couleurBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    } 

    @Override
    public String toString() {
        return 
            "Client : id=" + id +
            ", nom=" + nom +
            ", prenom=" + prenom +
            ", date=" + dateDeNaissance.toString() +
            ", mail=" + mail +
            ", motDePasse=" + motDePasse +
            ", signeZodiaque=" + signeZodiaque +
            ", signeAstro=" + signeAstro +
            ", couleurBonheur=" + couleurBonheur +
            ", animalTotem=" + animalTotem;
    }

}
