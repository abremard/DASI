package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class EmployeDao {

    // --------------------------CREATE----------------------------    
    public void creer(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(employe);
    }

    // --------------------------READ----------------------------    
    public Employe chercherParId(Long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, employeId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Employe chercherParMail(String employeMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.mail = :mail", Employe.class);
        query.setParameter("mail", employeMail); // correspond au paramètre ":mail" dans la requête
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Employe> listerEmployes() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e ORDER BY e.nom ASC, e.prenom ASC", Employe.class);
        return query.getResultList();
    }

    public Employe SelectionEmployeDisponible(String genre) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.disponible = 1 and e.genre = :genre ORDER BY e.nbConsultation ASC", Employe.class);
        query.setParameter("genre", genre);
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        else{ //si pas d'employe libre du meme genre
            TypedQuery<Employe> altQuery = em.createQuery("SELECT e FROM Employe e WHERE e.disponible = 1 ORDER BY e.nbConsultation ASC", Employe.class);
            List<Employe> employesAlt = altQuery.getResultList();
            result = employesAlt.get(0); 
        }
        return result;     
    }

    // --------------------------UPDATE----------------------------    
    public Employe modifierEmploye(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(employe);
    }

    // --------------------------DELETE----------------------------        
    public void supprimerEmploye(Employe employe, String SigneZodiac, String SigneAstro, String CouleurBonheur, String AnimalTotem) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(employe);
    }

}
