package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class ConsultationDao {
    
    public void creer(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(consultation);
    }
    
    public Consultation chercherParId(Long consultationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Consultation.class, consultationId); // renvoie null si l'identifiant n'existe pas
    }
    
    public void setCommentaire(Consultation consultation, String commentaire) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(consultation);
        consultation.setCommentaire(commentaire);
    }

    public Consultation modifierConsultation(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(consultation);
    }
    
    public void supprimerConsultation(Consultation consultation, String SigneZodiac, String SigneAstro, String CouleurBonheur, String AnimalTotem) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(consultation);
    }

    public List<Consultation> listerConsultations() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c ORDER BY c.nom ASC, c.prenom ASC", Consultation.class);
        return query.getResultList();
    }

    // Voir avec ORM
    public List<Consultation> ConsulterHistoriqueConsultation(String mail) { // QQ comment trouver la liste des consultations d'un client precis?
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.mail = :mail", Consultation.class);
        query.setParameter("mail", mail);
        return query.getResultList();
    }


}
