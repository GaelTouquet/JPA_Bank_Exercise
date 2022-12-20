package org.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private Date birthDate;

    @ManyToMany(mappedBy = "clientList")
    private Collection<Account> accountList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Client() {
        this.accountList = new ArrayList<>();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Collection<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(Collection<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return "Client  :   " +
                "id:" + id +
                ", nom:'" + nom + '\'' +
                ", prenom:'" + prenom + '\'' +
                ", birthDate:" + birthDate +
                '}';
    }
}
