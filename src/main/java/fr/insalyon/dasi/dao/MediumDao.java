package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Medium;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;


/**
 *
 * @author DASI Team
 */
public class MediumDao {

    public void creer(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(medium);
    }
    
    public Medium chercherParId(Long mediumId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Medium.class, mediumId); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Medium> listerMediums() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT c FROM Medium c ORDER BY c.denomination ASC", Medium.class);
        return query.getResultList();
    }

    public List<Medium> listerMediumsParType(String leType) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT DISTINCT c FROM Medium c WHERE c.type = :leType ORDER BY c.denomination ASC", Medium.class);
        query.setParameter("leType", leType); // correspond au paramètre ":leType" dans la requête
        List<Medium> listeMediums = query.getResultList();
        return listeMediums;
    }
    public List<String> listerTypeMedium() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Query query = em.createQuery("SELECT DISTINCT (c.type) FROM Medium c ORDER BY c.type ASC");
        List<String> listeType = query.getResultList();
        return listeType;
    }
    
    public List<Medium> listerMediumsPourStats() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT e FROM Medium e ORDER BY e.nbConsultation DESC, e.denomination ASC", Medium.class);
        return query.getResultList();
    }
    
     public Medium modifierMedium(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(medium);
    }
    
    public void supprimerMedium(Medium medium, String SigneZodiac, String SigneAstro, String CouleurBonheur, String AnimalTotem) { //pourquoi besoin des autres parametres? QQ
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(medium);
    }

}

