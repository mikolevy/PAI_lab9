package mikolevy.dragonsweb.view;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
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

@ViewScoped
@ManagedBean
public class ListCaves implements Serializable {
    
    @EJB
    private DragonService dragonService;
    
    private List<Cave> caves;
    
    private Future<Void> dragonDeleted;
    
    @Inject
    @ChangeOnDragonList
    Event<DragonsChanged> dragonsChanged;
    
    public void setDragonService(DragonService dragonService) {
        this.dragonService = dragonService;
    }
    
    public List<Cave> getCaves() {
        if(null == caves) {
            caves = dragonService.findAllCaves();
        }
        
        return caves;
    }
    
    public String removeCave(Cave cave) {
        dragonService.removeCave(cave);
        return "list_caves?faces-redirect=true";
    }
    
    public void removeDragon(Dragon dragon) {
        dragonDeleted = dragonService.removeDragon(dragon);
        DragonsChanged changed = new DragonsChanged();
        dragonsChanged.fire(changed);
        //return "list_caves?faces-redirect=true";
    }
    
    public boolean isRemoveDragonDone() {
        return dragonDeleted != null && dragonDeleted.isDone();
    }

    public void updateDragons() {
        if (isRemoveDragonDone()) {
            caves = dragonService.findAllCaves();
        }
    }
    
}
