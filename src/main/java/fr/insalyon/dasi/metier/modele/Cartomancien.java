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
public class Cartomancien extends Medium implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Cartomancien() {
    }

    public Cartomancien(String type, String denomination, String genre, String presentation) {
        super(type, denomination, genre, presentation);
    }

    public Long getId() {
        return id;
    }
    
}
