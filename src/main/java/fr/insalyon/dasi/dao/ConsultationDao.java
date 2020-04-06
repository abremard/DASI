package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Statut;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class ConsultationDao {

    // --------------------------CREATE----------------------------
    public void creer(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(consultation);
    }

    // --------------------------READ----------------------------    
    public Consultation chercherParId(Long consultationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Consultation.class, consultationId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Consultation chercherParMail(String consultationMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.mail = :mail", Consultation.class);
        query.setParameter("mail", consultationMail); // correspond au paramètre ":mail" dans la requête
        List<Consultation> Consultations = query.getResultList();
        Consultation result = null;
        if (!Consultations.isEmpty()) {
            result = Consultations.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Consultation> listerConsultations() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c ORDER BY c.nom ASC, c.prenom ASC", Consultation.class);
        return query.getResultList();
    }

    // --------------------------UPDATE----------------------------
    public Consultation modifierConsultation(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(consultation);
    }

    // --------------------------DELETE----------------------------
    public void supprimerConsultation(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(consultation);
    }

}
