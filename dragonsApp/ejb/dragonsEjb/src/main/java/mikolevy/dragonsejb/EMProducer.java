/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikolevy.dragonsejb;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mikolaj
 */
@Singleton
public class EMProducer {
    
    @Produces
    @UserDatabase
    @PersistenceContext
    private EntityManager em;
}
