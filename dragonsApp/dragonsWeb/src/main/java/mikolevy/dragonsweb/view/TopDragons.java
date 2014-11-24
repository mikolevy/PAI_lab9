package mikolevy.dragonsweb.view;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import lombok.extern.java.Log;
import mikolevy.dragonsejb.DragonService;
import mikolevy.dragonslib.entities.Dragon;
import mikolevy.dragonslib.events.ChangeOnDragonList;
import mikolevy.dragonslib.events.DragonsChanged;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Mikolaj
 */

@ApplicationScoped
@Log
public class TopDragons  implements Serializable { 
    
    @EJB
    private DragonService dragonService;
    
    private List<Dragon> dragons;
    
    private Future<Void> dragonDeleted;
    
    public void setDragonService(DragonService dragonService) {
        this.dragonService = dragonService;
    }
    
    public List<Dragon> getDragons() {
        if(null == dragons) {
            dragons = dragonService.getTop();
        }
        
        return dragons;
    }
    
    public void update(@Observes @ChangeOnDragonList DragonsChanged event) {
        dragons = dragonService.getTop();
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/notifications", new FacesMessage("", ""));
    }
}
