package fr.insalyon.dasi.ihm.console;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.service.Service;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) {

        JpaUtil.init();
        //testerInscriptionClient();
        //testerIncriptionEmploye();
        //testerCreationMedium();
        // initialiserClients(); // Question 3

        // testerListeClients(); // Question 7
        // testerAuthentificationClient(); // Question 8
        // saisirInscriptionClient(); // Question 9
        // saisirRechercheClient();

        // INSCRIRE CLIENT 
        // f(); // Cas normal
        // testerDuplicatMailInscription;

        // DEMANDER CONSULTATION
        //testerDemanderConsultation(); // Cas normal
        
        // STATS EMPLOYES 
        //testerStatsDashboardEmploye();
        
        // STATS MEDIUMS 
        testerStatsDashboardMedium();
        
        //SIGNALER DEBUT CONSULTATION
        //testerSignalerDebutConsultation();
       
        //VALIDER FIN CONSULTATION
        //testerValiderFinConsultation();
        
        //CONSULTER HISTORIQUE CONSULTATION CLIENT
        testerConsulterHistoriqueConsultationClient();

        //CONSULTER HISTORIQUE CONSULTATION EMPLOYE
         testerConsulterHistoriqueConsultationEmploye();
        
        // RECHERCHE CLIENT PAR ID
        // testerRechercheClient(); // 2 cas normaux + 1 cas anormal

        // AUTHENTIFIER CLIENT
        //testerAuthentificationClient();

        // LISTER CLIENT
        // testerListerClients();

        // ENVOYER MAIL CONFIRMATION INSCRIPTION
        // testerEnvoyerMailConfirmationInscription();

        // ENVOYER MESSAGE DEMANDE CONSULTATION
        // testerEnvoyerMessageDemandeConsultation(); 

        // ENVOYER MESSAGE CONSULTATION
        // testerEnvoyerMessageConsultation();

        // PREDICTION AIDE
        // testerPredictionsAide();

        // SELECTION AUTOMATIQUE EMPLOYE //OBSOLETE
         //testerSelectionAutomatiqueEmploye(); 

        // CONSULTER PROFIL EMPLOYE
        // testerConsulterProfilEmploye();

        // CONSULTER PROFIL CLIENT
        // testerConsulterProfilClient();

        // LISTER MEDIUM PAR TYPE
        //testerListerMediumParType();
         
        // LISTER TYPE MEDIUM
        //testerListerTypeMedium();

        JpaUtil.destroy();
    }

    public static void afficherClient(Client client) {
        System.out.println("-> " + client.toString());
    }

    public static void afficherConsultation(Consultation consultation) {
        System.out.println("-> " + consultation.toString());
    }

    public static void afficherEmploye(Employe employe) {
        System.out.println("-> " + employe.toString());
    }

    public static void afficherMedium(Medium medium) {
        System.out.println("-> " + medium.toString());
    }

    public static void testerInscriptionClient() {

        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,1,26);
        Service service = new Service();
        Client claude = null;
        try {
            claude = service.inscrireClient("Chappe", "Claude", calendar.getTime(), "claude.chappe@insa-lyon.fr","15 rue plolplo", "684318", "mdp1");
            System.out.println(claude);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherClient(claude);

        calendar.set(1996,4,11);
        Client hedy = null;
        try {
            hedy = service.inscrireClient("Lamarr", "Hedy", calendar.getTime(), "hlamarr@insa-lyon.fr", "58 avenue des morts","6846518","mdp2");
            System.out.println(hedy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherClient(hedy);
    }

    public static void testerIncriptionEmploye() {
        Service service = new Service();

        Employe pierre = new Employe("Michel", "Pierre", "pierre-michel@insa-lyon.fr", "878945", "mdp", "M");
        Employe jjp = new Employe("jjp", "momo", "jjp@gmail.com", "8789485", "mdpfds", "F");
        System.out.println("-> " + pierre.toString());

        try {
            service.inscrireEmploye(pierre);
            System.out.println("Piere donent arg");
            service.inscrireEmploye(jjp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherEmploye(pierre);
    }

    public static void testerCreationMedium() {
        Service service = new Service();

        Spirite tran = new Spirite("machine","Spirite", "Professeur Tran", "H", "Votre avenir est devant vous : regardons-le ensemble !");
        try {
            service.creerMedium(tran);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherMedium(tran);

        Astrologue serena = new Astrologue("psycho","2006","Astrologue", "Serena", "F", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.");

        try {
            service.creerMedium(serena);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherMedium(serena);
        
        Cartomancien joe = new Cartomancien("Cartomancien", "Joe", "M", "Besoin de conseil concernant votre vie professionnelle? Vous etes venus au bon endroit.");

        try {
            service.creerMedium(joe);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherMedium(joe);
    }
    
    public static void testerDuplicatMailInscription() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,4,11);
        Service service = new Service();
        Client hedy = null;
        try {
            hedy = service.inscrireClient("Lamarr", "Hedy", new Date(calendar.getTimeInMillis()), "hlamarr@insa-lyon.fr", "58 avenue des morts","6846518","mdp2");
            System.out.println(hedy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherClient(hedy);

        calendar.set(1996,1,15);
        Client hedwig = null;
        try {
            hedwig = service.inscrireClient("Lamarr", "Hedwig Eva Maria", new Date(calendar.getTimeInMillis()), "hlamarr@insa-lyon.fr", "98 boulevard des oublies","98453","mdp3");
            System.out.println(hedwig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        afficherClient(hedwig);
    }

    public static void testerDemanderConsultation() {
        //Calendar calendar = Calendar.getInstance();
        //calendar.clear();
        //calendar.set(1996,4,11);
        Service service = new Service();
        //Client hedy = service.rechercherClientParId(Long.valueOf(2)); //chercher le client d'ID 2
        Client hedy= service.ConsulterProfilClient("claude.chappe@insa-lyon.fr");
        Medium pierre = service.afficherDetailsMedium(Long.valueOf(4));
        Consultation consultation;
        System.out.println("-> " + pierre.toString());
        try {
            consultation = service.DemanderConsultation(hedy,pierre);
            System.out.println("-> " + consultation.toString());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testerSignalerDebutConsultation() {
        Service service = new Service();
        Client hedy= service.ConsulterProfilClient("claude.chappe@insa-lyon.fr");
        Medium pierre = service.afficherDetailsMedium(Long.valueOf(2));
        
        try{
            Consultation consultation = service.DemanderConsultation(hedy,pierre);
            service.SignalerDebutConsultation(consultation);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void testerValiderFinConsultation() {
        Service service = new Service();
        Client hedy= service.ConsulterProfilClient("claude.chappe@insa-lyon.fr");
        Medium pierre = service.afficherDetailsMedium(Long.valueOf(2));
        try{
            Consultation consultation = service.DemanderConsultation(hedy,pierre);
            service.SignalerDebutConsultation(consultation);
            service.ValiderFinConsultation(consultation, "ok bref");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void testerConsulterHistoriqueConsultationClient(){
        Service service = new Service();
        int monId=1;
        List<Consultation> laListe;
        laListe = service.ConsulterHistoriqueConsultationClient( Long.valueOf(monId));
        System.out.println("-> " + (laListe.get(0)).toString());
        System.out.println("-> " + (laListe.get(1)).toString());
        System.out.println("-> " + (laListe.get(2)).toString());
        System.out.println("-> " + (laListe.get(3)).toString());
        System.out.println("-> " + (laListe.get(4)).toString());
        System.out.println("-> " + (laListe.get(5)).toString());
        System.out.println("***********************************");
    }
    
    public static void testerConsulterHistoriqueConsultationEmploye(){
        Service service = new Service();
        int monId=201;
        List<Consultation> laListe;
        laListe = service.ConsulterHistoriqueConsultationEmploye( Long.valueOf(monId));
        System.out.println("-> " + (laListe.get(0)).toString());
        System.out.println("-> " + (laListe.get(1)).toString());
        System.out.println("-> " + (laListe.get(2)).toString());
        System.out.println("-> " + (laListe.get(3)).toString());
        System.out.println("***********************************");
    }

    public static void testerStatsDashboardEmploye(){
        Service service = new Service();
        List<Employe> laListe;
        laListe = service.statsDashboardEmploye();
        System.out.println("-> " + (laListe.get(0)).toString());
        System.out.println("-> " + (laListe.get(1)).toString());
    }
    
    public static void testerStatsDashboardMedium(){
        Service service = new Service();
        List<Medium> laListe;
        laListe = service.statsDashboardMedium();
        System.out.println("-> " + (laListe.get(0)).toString());
        System.out.println("-> " + (laListe.get(1)).toString());
        System.out.println("-> " + (laListe.get(2)).toString());
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

    public static void testerListerClients() {
        System.out.println();
        System.out.println("**** testerListeClients() ****");
        System.out.println();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,1,26);
        Service service = new Service();
        try {
            service.inscrireClient("Chappe", "Claude", calendar.getTime(), "claude.chappe@insa-lyon.fr", "15 rue plolplo", "684318", "mdp1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        List<Client> listeClients = null;
        try {
            listeClients = service.listerClients();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("*** Liste des Clients");
        if (listeClients != null) {
            for (Client client : listeClients) {
                afficherClient(client);
            }
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerEnvoyerMailConfirmationInscription() {

        System.out.println();
        System.out.println("**** testerEnvoyerMailConfirmationInscription() ****");
        System.out.println();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,1,26);
        Service service = new Service();
        Client claude = new Client("Chappe", "Claude", calendar.getTime(), "claude.chappe@insa-lyon.fr",
                "15 rue plolplo", "684318", "mdp1");
        try {
            String message = service.EnvoyerMailConfirmationInscription(claude);
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testerEnvoyerMessageDemandeConsultation() {

        System.out.println();
        System.out.println("**** testerEnvoyerMessageDemandeConsultation() ****");
        System.out.println();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,1,26);
        Service service = new Service();
        Client claude = new Client("Chappe", "Claude", calendar.getTime(), "claude.chappe@insa-lyon.fr",
                "15 rue plolplo", "684318", "mdp1");
        
        Consultation consultation = new Consultation();

        Employe pierre = new Employe("Michel", "Pierre", "pierre-michel@insa-lyon.fr", "878945", "mdp", "M");

        Medium azGul = new Medium("Necromancien","AzGul","Homme","Quand les vivants n'ont plus de réponses à vos questions, les morts ont toujours leur mot à dire...");

        String mail = service.EnvoyerMessageDemandeConsultation(claude, pierre, azGul, consultation);

        System.out.println(mail);
    }

    public static void testerEnvoyerMessageConsultation() {

        System.out.println();
        System.out.println("**** testerEnvoyerMessageConsultation() ****");
        System.out.println();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,1,26);
        Service service = new Service();
        Client claude = new Client("Chappe", "Claude", calendar.getTime(), "claude.chappe@insa-lyon.fr",
                "15 rue plolplo", "684318", "mdp1");
        
        Consultation consultation = new Consultation();

        Employe pierre = new Employe("Michel", "Pierre", "pierre-michel@insa-lyon.fr", "878945", "mdp", "M");

        Medium azGul = new Medium("Necromancien","AzGul","Homme","Quand les vivants n'ont plus de réponses à vos questions, les morts ont toujours leur mot à dire...");

        String mail = service.EnvoyerMessageConsultation(claude, pierre, azGul, consultation);

        System.out.println(mail);

    }

    public static void testerPredictionsAide() {

        System.out.println();
        System.out.println("**** testerPredictionAide() ****");
        System.out.println();

        Service service = new Service();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,1,26);

        Client claude = new Client("Chappe", "Claude", calendar.getTime(), "claude.chappe@insa-lyon.fr",
                "15 rue plolplo", "684318", "mdp1");

        int amour = 3;
        int sante = 3;
        int travail = 3;

        List<String> prediction = null; 
        
        try {
            prediction = service.PredictionsAide(claude.getCouleurBonheur(), claude.getAnimalTotem(), amour, sante, travail);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(prediction);
    }

    /*public static void testerSelectionAutomatiqueEmploye() {

        System.out.println();
        System.out.println("**** testerSelectionAutomatiqueEmploye() ****");
        System.out.println();

        Service service = new Service();

        Employe pierre = new Employe("Michel", "Pierre", "pierre-michel@insa-lyon.fr", "878945", "mdp");

        try {
            service.inscrireEmploye(pierre);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Employe retour = service.SelectionAutomatiqueEmploye();

        afficherEmploye(retour);
    }*/ //obsolete

    public static void testerConsulterProfilEmploye() {

        System.out.println();
        System.out.println("**** testerConsulterProfilEmploye() ****");
        System.out.println();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,1,26);
        Service service = new Service();
        Client claude = null;
        try {
            claude = service.inscrireClient("Chappe", "Claude", calendar.getTime(), "claude.chappe@insa-lyon.fr", "15 rue plolplo", "684318", "mdp1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> profil = null;

        try {
            profil = service.ConsulterProfilEmploye(claude.getMail());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(profil);

    }

    public static void testerConsulterProfilClient() {

        System.out.println();
        System.out.println("**** testerConsulterProfilClient() ****");
        System.out.println();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1996,1,26);
        Service service = new Service();
        Client claude = null;
        try {
            claude = service.inscrireClient("Chappe", "Claude", calendar.getTime(), "claude.chappe@insa-lyon.fr", "15 rue plolplo", "684318", "mdp1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Client client = service.ConsulterProfilClient(claude.getMail());

        afficherClient(client);

    }

    public static void testerListerMediumParType() {
        System.out.println();
        System.out.println("**** testerListerMediumParType() ****");
        System.out.println();

        Service service = new Service();

        List<Medium> listeMediums = null;

        listeMediums = service.listerMediumParType("Astrologue");

        System.out.println("*** Liste des Mediums");

        if (listeMediums != null) {
            for (Medium medium : listeMediums) {
                System.out.println(medium.toString());
            }
        }

        else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerListerTypeMedium() {
        System.out.println();
        System.out.println("**** testerListerTypeMedium() ****");
        System.out.println();

        Service service = new Service();

        List<String> listeTypeMediums = null;

        listeTypeMediums = service.listerTypeMedium();

        System.out.println("*** Liste des Types de Mediums");

        if (listeTypeMediums != null) {
            for (String type : listeTypeMediums) {
                System.out.println(type);
            }
        }

        else {
            System.out.println("=> ERREUR...");
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
