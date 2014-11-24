package mikolevy.dragonsejb;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.extern.java.Log;
import mikolevy.dragonslib.entities.Cave;
import mikolevy.dragonslib.entities.Dragon;
import mikolevy.dragonslib.events.ChangeOnDragonList;

/**
 *
 * @author Mikołaj
 */
//@Stateless
@LocalBean
@ConversationScoped
@Stateful
@Log
public class DragonService implements Serializable {
    
    @Inject
    @UserDatabase
    EntityManager entityManager;

    public void DragonService() {
    }
    
    public List<Cave> findAllCaves() {
        return entityManager.createNamedQuery("Cave.findAll").getResultList();
    }
    
   public List<Dragon> getTop() {
       return entityManager.createNamedQuery("Dragon.getTop").setMaxResults(2).getResultList();
   }
    
    public Cave findCave(int caveId) {
        return entityManager.find(Cave.class, caveId);
    }
    
    public Cave findCaveBySurface(int surface) {
        List<Cave> caves = this.findAllCaves();
        for (Cave cave : caves) 
            if (cave.getSurface() == surface)
                return cave;
        return null;
    }
    
    public void saveCave(Cave cave) {
        if (null == cave.getId())
            entityManager.persist(cave);
        else
            entityManager.merge(cave);
    }
    
    public void removeCave(Cave cave) {
        entityManager.remove(entityManager.merge(cave));
    }
    
    public Dragon findDragon(int dragonId) {
        return entityManager.find(Dragon.class, dragonId);
    }
    
    @ControlNewDragons
    public void saveDragon(Dragon dragon) {
        if (dragon.getId() == null)
            newDragon(dragon);
        else
            entityManager.merge(dragon);
    }
    
    @Asynchronous
    public Future<Void> removeDragon(Dragon dragon) {
        entityManager.remove(entityManager.merge(dragon));
        return new AsyncResult<>(null);
    }
    
    
    public void newDragon(Dragon dragon) {
        entityManager.persist(dragon);
    }
    
    public List<Dragon> getBestDragon() {
        return entityManager.createNamedQuery("Dragon.getTop").setMaxResults(1).getResultList();
    }
    
    public void pobierzPodatek() {
        Query query = entityManager.createNamedQuery("Dragon.podatek");
        query.setParameter(1, 10);
        query.executeUpdate();
    }

}
