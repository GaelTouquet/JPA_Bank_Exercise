package org.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false, length = 27)
    private String iban;

    @Column(precision = 10, scale = 2)
    private double credit;

    @ManyToMany
    @JoinTable(
            name = "ACCOUNT_CLIENT",
            joinColumns = @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "CLIENT_ID", referencedColumnName = "id")
    )
    private Collection<Client> clientList;

    @Override
    public String toString() {
        String out = "";
        out += String.format("*----------- Account id:%d ---------*\n", id);
        out += String.format("label : %s\n", label);
        out += String.format("iban : %s\n", iban);
        out += String.format("credit : %10.2f\n", credit);
        out += String.format("agency : %s\n", agency.toString());
        out += "clientList : \n";
        for (Client client : clientList
             ) {
            out += String.format("\t%s\n", client.toString());
        }
        out += "*---------------------------*";
        return out;
    }

    public Account() {
        this.clientList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Collection<Client> getClientList() {
        return clientList;
    }

    public void setClientList(Collection<Client> clientList) {
        this.clientList = clientList;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @ManyToOne
    @JoinColumn(name = "id_AGENCY")
    private Agency agency;
}
