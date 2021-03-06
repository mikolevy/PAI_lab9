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
public class DispatcherView implements Serializable {
    
    public String dispatch(String page, String param) {
        switch(page) {
            case "caves":
                return "list_caves?faces-redirect=true";
            case "cave":
                return "view_cave.xhtml?caveId="+param+"&faces-redirect=true";
            case "first":
                return "index?faces-redirect=true";
        }
        return "";
        
    }
    
}
