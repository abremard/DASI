package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.ConsultationDao;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Statut;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import util.AstroTest;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DASI Team
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected MediumDao mediumDao = new MediumDao();
    protected ConsultationDao consultationDao = new ConsultationDao();
    protected AstroTest astroTest = new AstroTest();

    public Client inscrireClient(String nom, String prenom, Date dateDeNaissance, String mail, String adresse, String telephone, String motDePasse) throws IOException {
        Client client = new Client(nom, prenom, dateDeNaissance, mail, adresse, telephone, motDePasse);
        List<String> profilAstral = this.astroTest.getProfil(client.getPrenom(), client.getDateDeNaissance());
        client.setSigneZodiaque(profilAstral.get(0));
        client.setSigneAstro(profilAstral.get(1));
        client.setCouleurBonheur(profilAstral.get(2));
        client.setAnimalTotem(profilAstral.get(3));
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.creer(client);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            client = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;
    }

    public Long inscrireEmploye(Employe employe) throws IOException {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            employeDao.creer(employe);
            JpaUtil.validerTransaction();
            resultat = employe.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireEmplye(employe)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Long creerMedium(Medium medium) throws IOException {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            mediumDao.creer(medium);
            JpaUtil.validerTransaction();
            resultat = medium.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service creerMedium(medium)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Consultation DemanderConsultation(Client client, Medium medium) throws IOException {
        Consultation resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            Consultation consultation = new Consultation(client, medium); // ADAPTER A ORM
            //Consultation consultation = new Consultation(client,mediumDao.chercherParId(Long.valueOf(5)));
            
            consultation.setEmploye(SelectionAutomatiqueEmploye());
            JpaUtil.ouvrirTransaction();
            System.out.print("apres ouvrirTransaction");
            consultationDao.creer(consultation);
            JpaUtil.validerTransaction();
            resultat = consultation;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service DemanderConsultation(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client rechercherClientParId(Long id) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client authentifierClient(String mail, String motDePasse) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Employe authentifierEmploye(String mail, String motDePasse) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche de l'employé
            Employe employe = employeDao.chercherParMail(mail);
            if (employe != null) {
                // Vérification du mot de passe
                if (employe.getMotDePasse().equals(motDePasse)) {
                    resultat = employe;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Client> listerClients() throws IOException {
        List<Client> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.listerClients();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    // Envoyer un mail au client
    public String EnvoyerMailConfirmationInscription(Client client) throws IOException {
        Client resultat = null;
        String message = 
        "Expéditeur : contact@predict.if" + System.lineSeparator() +
        "Pour : " + client.getMail() + System.lineSeparator();
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParMail(client.getMail());
            if (resultat != null) {
                message +=
                "Sujet : Echec de l’inscription chez PREDICT’IF" + System.lineSeparator() +
                "Corps : Bonjour " + client.getPrenom() + ", votre inscription au service PREDICT’IF a malencontreusement échoué..." + System.lineSeparator() +
                "Merci de recommencer ultérieurement.";
            }
            else {
                message +=
                "Sujet : Bienvenue chez PREDICT’IF" + System.lineSeparator() +
                "Corps : Bonjour " + client.getPrenom() + ", nous vous confirmons votre inscription au service PREDICT’IF.Rendezvous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums";                
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service remplirProfilAstral(client)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return message;
    }

    // Envoyer un SMS au client
    public String EnvoyerMessageDemandeConsultation(Client client, Employe employe, Medium medium, Consultation consultation) {
        String message =
        "Pour : " + client.getPrenom() + " " + client.getNom() + ", Tel : " + client.getTelephone() + System.lineSeparator() +
        "Message : Bonjour " + client.getPrenom() + ". J'ai bien reçu votre demande de consultation du " + consultation.getTemps().toString() + ". Vous pouvez dès à présent me contacter au " + employe.getTelephone() + ". A tout de suite ! Médiumiquement vôtre, " + medium.getDenomination();
        JpaUtil.creerContextePersistance();
        return message;
    }

    // Envoyer un SMS à l'employé
    public String EnvoyerMessageConsultation(Client client, Employe employe, Medium medium, Consultation consultation) {
        String message =
        "Pour : " + employe.getPrenom() + " " + employe.getNom() + ", Tel : " + employe.getTelephone() + System.lineSeparator() +
        "Message : Bonjour " + employe.getPrenom() + ". Consultation requise pour " + client.getPrenom() + " " + client.getNom() + ". Médium à incarner : " + medium.getDenomination();
        JpaUtil.creerContextePersistance();
        return message;
    }

    public void SignalerDebutConsultation(Employe employe) throws IOException {
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            Consultation consultation = new Consultation(); // ADAPTER A ORM employe.getConsultation().getStatut == PENDING...
            consultation.setStatut(Statut.STARTED);
            consultationDao.modifierConsultation(consultation);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service SignalerDebutConsultation(consultation)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public List<String> PredictionsAide(String couleur, String animal, int amour, int sante, int travail) throws IOException {
        List<String> result = astroTest.getPredictions(couleur, animal, amour, sante, travail);
        return result;
    }

    public Employe SelectionAutomatiqueEmploye() {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.SelectionEmployeDisponible();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public void ValiderFinConsultation(String employeMail, String commentaire) throws IOException {
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            Employe employe = employeDao.chercherParMail(employeMail);
            Consultation consultation = new Consultation(); // ADAPTER A ORM employe.getConsultation().getStatut == STARTED...
            consultation.setCommentaire(commentaire);
            consultation.setStatut(Statut.FINISHED);
            consultationDao.modifierConsultation(consultation);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ValiderFinConsultation(consultation)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }    

    public List<String> ConsulterProfilEmploye(String mail) throws IOException {
        JpaUtil.creerContextePersistance();
        List<String> profilAstral = new ArrayList<String>();
        try {
            JpaUtil.ouvrirTransaction();
            Client client = clientDao.chercherParMail(mail);
            profilAstral.add(client.getSigneZodiaque());
            profilAstral.add(client.getSigneAstro());
            profilAstral.add(client.getCouleurBonheur());
            profilAstral.add(client.getAnimalTotem());
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ConsulterProfilEmploye(client)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return profilAstral;
    }

    public List<String> listerTypeMedium() {	
        List<String> resultat = null;	
        JpaUtil.creerContextePersistance();	
        try {	
            resultat = mediumDao.listerTypeMedium();	
        } catch (Exception ex) {	
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerTypeMedium()", ex);	
            resultat = null;	
        } finally {	
            JpaUtil.fermerContextePersistance();	
        }	
        return resultat;	
    }	

    public List<String> listerMediumParType(String leType) {	
        List<String> resultat = null;	
        JpaUtil.creerContextePersistance();	
        try {	
            resultat = mediumDao.listerMediumsParType(leType);	
        } catch (Exception ex) {	
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMediumParType()", ex);	
            resultat = null;	
        } finally {	
            JpaUtil.fermerContextePersistance();	
        }	
        return resultat;	
    }	

    public List<Consultation> ConsulterHistoriqueConsultation(String mail) {	
        List<Consultation> resultat = null;	
        JpaUtil.creerContextePersistance();	
        try {	
            resultat = consultationDao.ConsulterHistoriqueConsultation(mail);	
        } catch (Exception ex) {	
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ConsulterHistoriqueConsultation()", ex);	
            resultat = null;	
        } finally {	
            JpaUtil.fermerContextePersistance();	
        }	
        return resultat;	
    }	

    public Client ConsulterProfilClient(String mail) {	
        Client resultat = new Client();	
        JpaUtil.creerContextePersistance();	
        try {	
            resultat = clientDao.chercherParMail(mail);	
        } catch (Exception ex) {	
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ConsulterProfilClient()", ex);	
            resultat = null;	
        } finally {	
            JpaUtil.fermerContextePersistance();	
        }	
        return resultat;	
    }

    public Medium afficherDetailsMedium(String denomination) {	
        Medium resultat = new Medium();	
        JpaUtil.creerContextePersistance();	
        try {	
            resultat = mediumDao.chercherMediumParDenomination(denomination);	
        } catch (Exception ex) {	
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherMediumParDenomination()", ex);	
            resultat = null;	
        } finally {	
            JpaUtil.fermerContextePersistance();	
        }	
        return resultat;	
    }	

    public void remplirProfilAstral(Client client) throws IOException {	
        List<String> profilAstral = this.astroTest.getProfil(client.getPrenom(), client.getDateDeNaissance());	
        JpaUtil.creerContextePersistance();	
        try {	
            JpaUtil.ouvrirTransaction();	
            clientDao.ajouterProfilAstral(client, profilAstral);	
            JpaUtil.validerTransaction();	
        } catch (Exception ex) {	
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service remplirProfilAstral(client)", ex);	
            JpaUtil.annulerTransaction();	
        } finally {	
            JpaUtil.fermerContextePersistance();	
        }	
    }   

}
