/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.com.ifba.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {

     private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("prg03persistenciaPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
