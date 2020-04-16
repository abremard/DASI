/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Iyad
 */
@Entity
public class Astrologue extends Medium implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String formation;
    private String promotion;

    public Astrologue() {
    }

    public Astrologue(String formation, String promotion) {
        this.formation = formation;
        this.promotion = promotion;
    }

    public Astrologue(String formation, String promotion, String type, String denomination, String genre, String presentation) {
        super(type, denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Long getId() {
        return id;
    }

    public String getFormation() {
        return formation;
    }

    public String getPromotion() {
        return promotion;
    }
    
}
