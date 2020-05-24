package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Statut;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    
    public Consultation chercherParId(Long id) {
        // EntityManager em = JpaUtil.obtenirContextePersistance();
        EntityManager em = Persistence.createEntityManagerFactory("ProjetDASIPersistenceUnit").createEntityManager();
        return em.find(Consultation.class, id);
        // return em.find(Consultation.class, id); // renvoie null si l'identifiant n'existe pas
    }
    
    public void setCommentaire(Consultation consultation, String commentaire) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        consultation.setCommentaire(commentaire);
        em.persist(consultation);
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
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c ORDER BY c.nom ASC, c.prenom ASC BY c.temps DESC", Consultation.class);
        return query.getResultList();
    }

    public Consultation getConsultationEmploye(Long id) {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetDASIPersistenceUnit").createEntityManager();
        Statut pending = Statut.PENDING;
        Statut started = Statut.STARTED;
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.employe.id= :id and (c.statut = :pending or c.statut = :started)", Consultation.class);
        query.setParameter("id", id);
        query.setParameter("pending", pending);
        query.setParameter("started", started);
        return query.getResultList().get(0);
    }

    public List<Consultation> ConsulterHistoriqueConsultationClient(Long id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.client.id= :id ORDER BY c.temps DESC", Consultation.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public List<Consultation> ConsulterHistoriqueConsultationEmploye(Long id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.employe.id= :id ORDER BY c.temps DESC", Consultation.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    


}
