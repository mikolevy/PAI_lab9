package mikolevy.dragonsweb.view;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import lombok.Delegate;
import mikolevy.dragonsejb.DragonService;
import mikolevy.dragonslib.entities.Cave;
import mikolevy.dragonslib.entities.Dragon;
import mikolevy.dragonslib.events.ChangeOnDragonList;
import mikolevy.dragonslib.events.DragonsChanged;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Mikolaj
 */

@ViewScoped
@ManagedBean
public class ListBest implements Serializable {
    
    @Inject
    @Delegate
    TopDragons topDragons;
    
    
    
    
}
