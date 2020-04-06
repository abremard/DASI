package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class MediumDao {

    // --------------------------CREATE----------------------------    
    public void creer(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(medium);
    }

    // --------------------------READ----------------------------    
    public Medium chercherParId(Long mediumId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Medium.class, mediumId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Medium chercherParMail(String mediumMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT c FROM Medium c WHERE c.mail = :mail", Medium.class);
        query.setParameter("mail", mediumMail); // correspond au paramètre ":mail" dans la requête
        List<Medium> Mediums = query.getResultList();
        Medium result = null;
        if (!Mediums.isEmpty()) {
            result = Mediums.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Medium> listerMediums() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT c FROM Medium c ORDER BY c.nom ASC, c.prenom ASC", Medium.class);
        return query.getResultList();
    }
    // --------------------------UPDATE----------------------------    
    public Medium modifierConsultation(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(medium);
    }

    // --------------------------DELETE----------------------------        
    public void supprimerMedium(Medium medium, String SigneZodiac, String SigneAstro, String CouleurBonheur, String AnimalTotem) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(medium);
    }

}
