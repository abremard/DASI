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
import java.io.IOException;
import java.util.List;

import util.AstroTest;
import util.Message;

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

    public Long inscrireClient(Client client) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            resultat = client.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
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
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Client> listerClients() {
        List<Client> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.listerClients();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Medium> listerMediumParType(String leType) {
        List<Medium> resultat = null;
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

    // Envoyer un mail au client
    public String envoyerMailConfirmationInscription(Client client) throws IOException {
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
    public String EnvoyerMessageDemandeConsultation(Client client, Employe employe, Medium medium, Consultation consultation) throws IOException {
        String message =
        "Pour : " + client.getPrenom() + " " + client.getNom() + ", Tel : " + client.getTelephone() + System.lineSeparator() +
        "Message : Bonjour " + client.getPrenom() + ". J'ai bien reçu votre demande de consultation du " + consultation.getTemps().toString() + ". Vous pouvez dès à présent me contacter au " + employe.getTelephone() + ". A tout de suite ! Médiumiquement vôtre, " + medium.getDenomination();
        JpaUtil.creerContextePersistance();
        return message;
    }

    // Envoyer un SMS à l'employé
    public String EnvoyerMessageConsultation(Client client, Employe employe, Medium medium, Consultation consultation) throws IOException {
        String message =
        "Pour : " + employe.getPrenom() + " " + employe.getNom() + ", Tel : " + employe.getTelephone() + System.lineSeparator() +
        "Message : Bonjour " + employe.getPrenom() + ". Consultation requise pour " + client.getPrenom() + " " + client.getNom() + ". Médium à incarner : " + medium.getDenomination();
        JpaUtil.creerContextePersistance();
        return message;
    }

}
