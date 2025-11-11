/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.com.ifba.infrastructure.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.persistence.PersistenceManager;
import javax.persistence.EntityManager;
import java.util.List;

public class CursoService {

    public void salvar(Curso curso) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void atualizar(Curso curso) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void remover(Curso curso) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Curso c = em.find(Curso.class, curso.getId());
            if (c != null) em.remove(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Curso> listarTodos() {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
        } finally {
            em.close();
        }
    }
}