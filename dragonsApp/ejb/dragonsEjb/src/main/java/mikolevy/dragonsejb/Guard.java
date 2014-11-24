/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikolevy.dragonsejb;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import mikolevy.dragonslib.entities.Dragon;

/**
 *
 * @author Mikolaj
 */
@Interceptor
@ControlNewDragons
public class Guard  implements Serializable {
    
    
//    DragonService dragonService = new DragonService();
//    
//    public Guard() {
//        
//    }
//    
//    @AroundInvoke
//    public Object invoke(InvocationContext invocationContext) throws Exception {
//        Object[] params = invocationContext.getParameters();
//        Dragon dragon = (Dragon)params[0];
//        List<Dragon> bestDragonList = dragonService.getBestDragon();
//        if (bestDragonList.size() > 0) {
//            Dragon bestDragon = bestDragonList.get(0);
//            if (dragon.getGold() > bestDragon.getGold())
//                dragon.setGold(bestDragon.getGold());
//        }
//        
////        System.out.println("Entering method: "
////                + invocationContext.getMethod().getName() + " in class "
////                + invocationContext.getMethod().getDeclaringClass().getName());
//
//        return invocationContext.proceed();
//    }
    
    @Inject
    @UserDatabase
    private EntityManager em;
    
    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception{
        
        Object param = context.getParameters()[0];
        if (param instanceof Dragon){
            Dragon dragon = (Dragon)param;
            
            if (dragon.getId() == null){
                int maxGold = (int)em.createQuery("select max(u.gold) from Dragon u").getSingleResult();
                dragon.setGold(maxGold - 100);
            }
        }
        
        Object result = context.proceed();
        return result;
    }
    
}
