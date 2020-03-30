package fr.insalyon.dasi.metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-30T16:51:53")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Long> id;
    public static volatile SingularAttribute<Consultation, String> commentaire;
    public static volatile SingularAttribute<Consultation, Date> temps;

}