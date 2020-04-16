package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) {

        JpaUtil.init();

        // initialiserClients(); // Question 3

        // testerListeClients(); // Question 7
        // testerAuthentificationClient(); // Question 8
        // saisirInscriptionClient(); // Question 9
        // saisirRechercheClient();

        // INSCRIRE CLIENT 
        // testerInscriptionClient(); // Cas normal
        // testerDuplicatMailInscription;

        // DEMANDER CONSULTATION
        // testerDemanderConsultation(); // Cas normal

        // RECHERCHE CLIENT PAR ID
        // testerRechercheClient(); // 2 cas normaux + 1 cas anormal

        // AUTHENTIFIER CLIENT
        // testerAuthentificationClient(); // 

        JpaUtil.destroy();
    }

    public static void afficherClient(Client client) {
        System.out.println("-> " + client.toString());
    }

    public static void afficherConsultation(Consultation consultation) {
        System.out.println("-> " + consultation.toString());
    }

    public static void testerInscriptionClient() {

        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,1,26);
        Service service = new Service();
        Client claude = new Client("Chappe", "Claude", calendar.getTime(), "claude.chappe@insa-lyon.fr",
                "15 rue plolplo", "684318", "mdp1");
        Long idClaude;
        try {
            idClaude = service.inscrireClient(claude);
            System.out.println(idClaude);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherClient(claude);

        calendar.set(1996,4,11);
        Client hedy = new Client("Lamarr", "Hedy", new Date(calendar.getTimeInMillis()), "hlamarr@insa-lyon.fr", "58 avenue des morts","6846518","mdp2");
        Long idHedy;
        try {
            idHedy = service.inscrireClient(hedy);
            System.out.println(idHedy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherClient(hedy);
    }

    public static void testerDuplicatMailInscription() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,4,11);
        Service service = new Service();
        Client hedy = new Client("Lamarr", "Hedy", new Date(calendar.getTimeInMillis()), "hlamarr@insa-lyon.fr", "58 avenue des morts","6846518","mdp2");
        Long idHedy;
        try {
            idHedy = service.inscrireClient(hedy);
            System.out.println(idHedy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherClient(hedy);

        calendar.set(1996,1,15);
        Client hedwig = new Client("Lamarr", "Hedwig Eva Maria", new Date(calendar.getTimeInMillis()), "hlamarr@insa-lyon.fr", "98 boulevard des oublies","98453","mdp3");
        Long idHedwig;
        try {
            idHedwig = service.inscrireClient(hedwig);
            System.out.println(idHedwig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherClient(hedwig);
    }

    public static void testerDemanderConsultation() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,4,11);
        Service service = new Service();
        Client hedy = new Client("Lamarr", "Hedy", new Date(calendar.getTimeInMillis()), "hlamarr@insa-lyon.fr", "58 avenue des morts","6846518","mdp2");
        Consultation consultation;
        try {
            consultation = service.DemanderConsultation(hedy);
            afficherConsultation(consultation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testerRechercheClient() {
        
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();

        testerInscriptionClient();
        
        Service service = new Service();
        long id;
        Client client;

        id = 1;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 2;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client #" + id + " non-trouvé");
        }
    }

    public static void testerAuthentificationClient() {
        
        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client client;
        String mail;
        String motDePasse;

        mail = "claude.chappe@insa-lyon.fr";
        motDePasse = "mdp1";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "claude.chappe@insa-lyon.fr";
        motDePasse = "mdp2";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "hlamarr@insa-lyon.fr";
        motDePasse = "mdp2";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "hlamarr@insa-lyon.fr";
        motDePasse = "****";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }

        // public static void initialiserClients() {

    //     System.out.println();
    //     System.out.println("**** initialiserClients() ****");
    //     System.out.println();

    //     EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetDASIPersistenceUnit");
    //     EntityManager em = emf.createEntityManager();

    //     Client ada = new Client("Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", "12 rue blabla", "65651", "mdp");
    //     // Client blaise = new Client("Pascal", "Blaise", "blaise.pascal@insa-lyon.fr",
    //     // "Blaise1906");
    //     // Client fred = new Client("Fotiadu", "Frédéric",
    //     // "frederic.fotiadu@insa-lyon.fr", "INSA-Forever");

    //     System.out.println();
    //     System.out.println("** Clients avant persistance: ");
    //     afficherClient(ada);
    //     // afficherClient(blaise);
    //     // afficherClient(fred);
    //     System.out.println();

    //     try {
    //         em.getTransaction().begin();
    //         em.persist(ada);
    //         // em.persist(blaise);
    //         // em.persist(fred);
    //         em.getTransaction().commit();
    //     } catch (Exception ex) {
    //         Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
    //         try {
    //             em.getTransaction().rollback();
    //         } catch (IllegalStateException ex2) {
    //             // Ignorer cette exception...
    //         }
    //     } finally {
    //         em.close();
    //     }

    //     System.out.println();
    //     System.out.println("** Clients après persistance: ");
    //     afficherClient(ada);
    //     // afficherClient(blaise);
    //     // afficherClient(fred);
    //     System.out.println();
    // }

    // public static void testerListeClients() {
        
    //     System.out.println();
    //     System.out.println("**** testerListeClients() ****");
    //     System.out.println();
        
    //     Service service = new Service();
    //     List<Client> listeClients = service.listerClients();
    //     System.out.println("*** Liste des Clients");
    //     if (listeClients != null) {
    //         for (Client client : listeClients) {
    //             afficherClient(client);
    //         }
    //     }
    //     else {
    //         System.out.println("=> ERREUR...");
    //     }
    // }

    // public static void saisirInscriptionClient() {
    //     Service service = new Service();

    //     System.out.println();
    //     System.out.println("Appuyer sur Entrée pour passer la pause...");
    //     Saisie.pause();

    //     System.out.println();
    //     System.out.println("**************************");
    //     System.out.println("** NOUVELLE INSCRIPTION **");
    //     System.out.println("**************************");
    //     System.out.println();

    //     String nom = Saisie.lireChaine("Nom ? ");
    //     String prenom = Saisie.lireChaine("Prénom ? ");
    //     String mail = Saisie.lireChaine("Mail ? ");
    //     String adresse = Saisie.lireChaine("Adresse ? ");
    //     String telephone = Saisie.lireChaine("Telephone ? ");
    //     String motDePasse = Saisie.lireChaine("Mot de passe ? ");

    //     Client client = new Client(nom, prenom, mail, adresse, telephone, motDePasse);
    //     Long idClient = service.inscrireClient(client);

    //     if (idClient != null) {
    //         System.out.println("> Succès inscription");
    //     } else {
    //         System.out.println("> Échec inscription");
    //     }
    //     afficherClient(client);

    // }

    // public static void saisirRechercheClient() {
    //     Service service = new Service();

    //     System.out.println();
    //     System.out.println("*********************");
    //     System.out.println("** MENU INTERACTIF **");
    //     System.out.println("*********************");
    //     System.out.println();

    //     Saisie.pause();

    //     System.out.println();
    //     System.out.println("**************************");
    //     System.out.println("** RECHERCHE de CLIENTS **");
    //     System.out.println("**************************");
    //     System.out.println();
    //     System.out.println();
    //     System.out.println("** Recherche par Identifiant:");
    //     System.out.println();

    //     Integer idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
    //     while (idClient != 0) {
    //         Client client = service.rechercherClientParId(idClient.longValue());
    //         if (client != null) {
    //             afficherClient(client);
    //         } else {
    //             System.out.println("=> Client #" + idClient + " non-trouvé");
    //         }
    //         System.out.println();
    //         idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
    //     }

    //     System.out.println();
    //     System.out.println("********************************");
    //     System.out.println("** AUTHENTIFICATION de CLIENT **");
    //     System.out.println("********************************");
    //     System.out.println();
    //     System.out.println();
    //     System.out.println("** Authentifier Client:");
    //     System.out.println();

    //     String clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");

    //     while (!clientMail.equals("0")) {
    //         String clientMotDePasse = Saisie.lireChaine("Mot de passe ? ");
    //         Client client = service.authentifierClient(clientMail, clientMotDePasse);
    //         if (client != null) {
    //             afficherClient(client);
    //         } else {
    //             System.out.println("=> Client non-authentifié");
    //         }
    //         clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
    //     }

    //     System.out.println();
    //     System.out.println("*****************");
    //     System.out.println("** AU REVOIR ! **");
    //     System.out.println("*****************");
    //     System.out.println();

    // }

}
