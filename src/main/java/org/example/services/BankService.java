package org.example.services;

import org.example.interfaces.IDAO;
import org.example.model.Account;
import org.example.model.Agency;
import org.example.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class BankService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque_bdd");

    private EntityManager em = emf.createEntityManager();

    public BankService() {
        //this.emf = emf;//Persistence.createEntityManagerFactory("banque_bdd");
        //this.em = em;//emf.createEntityManager();
    }

    public void begin() {
        em.getTransaction().begin();
    }

    // Client
    public boolean createClient(Client o) {
        em.persist(o);
        return true;
    }

    public void send() {
        em.getTransaction().commit();
    }

    public void close() {
        em.close();
        emf.close();
    }

    public boolean updateClient(Client o) {
        em.persist(o);
        return true;
    }

    public boolean deleteClient(Client o) {
        em.remove(o);
        return true;
    }

    public Client findClientById(Long id) {
        return em.find(Client.class, id);
    }

    public List<Client> findAllClient() {
        return em.createQuery("select p from Client p").getResultList();
    }

    // Account
    public boolean createAccount(Account o) {
        em.persist(o);
        return true;
    }

    public boolean updateAccount(Account o) {
        em.persist(o);
        return true;
    }

    public boolean deleteAccount(Account o) {
        em.remove(o);
        return true;
    }

    public Account findAccountById(Long id) {
        return em.find(Account.class, id);
    }

    public List<Account> findAllAccount() {
        return em.createQuery("select p from Account p").getResultList();
    }

    // Agency
    public boolean createAgency(Agency o) {
        em.persist(o);
        return true;
    }

    public boolean updateAgency(Agency o) {
        em.persist(o);
        return true;
    }

    public boolean deleteAgency(Agency o) {
        em.remove(o);
        return true;
    }

    public Agency findAgencyById(Long id) {
        return em.find(Agency.class, id);
    }

    public List<Agency> findAllAgency() {
        return em.createQuery("select p from Agency p").getResultList();
    }

}
