package com.example.demo.entities;
import java.io.Serializable;
import java.util.Objects;

public class Avocat implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nom;
    private String prenom;
    private String adress;
    private String description;
    private String rendez_vous;
    private String specialite;
    private String telephone;
    public Avocat(Integer id, String nom, String prenom, String adress, String description, String rendez_vous, String specialite, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.description = description;
        this.rendez_vous = rendez_vous;
        this.specialite = specialite;
        this.telephone = telephone;
    }
    public Avocat() {
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdress() {
        return adress;
    }

    public String getDescription() {
        return description;
    }

    public String getRendez_vous() {
        return rendez_vous;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRendez_vous(String rendez_vous) {
        this.rendez_vous = rendez_vous;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, adress, description, rendez_vous, specialite, telephone);
    }

    @Override
    public String toString() {
        return "Avocat [id=" + id+ ", nom=" + nom + ", prenom=" + prenom + ", adress=" + adress + ", description=" + description+ ", rendez_vous=" + rendez_vous+ ", specialite=" + specialite+ ", telephone=" + telephone + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avocat avocat = (Avocat) o;
        return Objects.equals(id, avocat.id) &&Objects.equals(nom, avocat.nom) && Objects.equals(prenom, avocat.prenom) && Objects.equals(adress, avocat.adress) && Objects.equals(description, avocat.description) && Objects.equals(rendez_vous, avocat.rendez_vous) && Objects.equals(specialite, avocat.specialite) && Objects.equals(telephone, avocat.telephone);
    }
}


