package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-08T15:16:24")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> signeZodiaque;
    public static volatile ListAttribute<Client, Consultation> HistoriqueConsultations;
    public static volatile SingularAttribute<Client, String> mail;
    public static volatile SingularAttribute<Client, String> animalTotem;
    public static volatile SingularAttribute<Client, String> telephone;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> signeAstro;
    public static volatile SingularAttribute<Client, Date> dateDeNaissance;
    public static volatile SingularAttribute<Client, String> motDePasse;
    public static volatile SingularAttribute<Client, String> couleurBonheur;
    public static volatile SingularAttribute<Client, String> adresse;
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> prenom;

}