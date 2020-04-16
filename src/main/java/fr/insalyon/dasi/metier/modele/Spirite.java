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
public class Spirite extends Medium implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String support;

    public Spirite() {
    }

    public Spirite(String support) {
        this.support = support;
    }

    public Spirite(String support, String type, String denomination, String genre, String presentation) {
        super(type, denomination, genre, presentation);
        this.support = support;
    }

    public Long getId() {
        return id;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
    
}
