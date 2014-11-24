package mikolevy.dragonsweb.view;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import mikolevy.dragonsejb.DragonService;
import mikolevy.dragonslib.entities.Cave;
import mikolevy.dragonslib.entities.Dragon;
import mikolevy.dragonslib.events.ChangeOnDragonList;
import mikolevy.dragonslib.events.DragonsChanged;

/**
 *
 * @author Mikolaj
 */

@ApplicationScoped
@ManagedBean
public class Helper implements Serializable {
    
//    @EJB
//    private DragonService dragonService;

    public void processRequest(String command) {
        switch(command) {
            case "podatek":
                 //DragonService dragonService = new DragonService();
                 //dragonService.pobierzPodatek();
                System.out.println("Pobrano podatek od smoków");
        }
    }
    
}
