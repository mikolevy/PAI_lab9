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
public class FrontController implements Serializable {

    public String processRequest(String page, String command, String param) {
        if (!command.equals("")) {
            Helper helper = new Helper();
            helper.processRequest(command);
        }
        
        DispatcherView dispatcher = new DispatcherView();
        return dispatcher.dispatch(page, param);
    }
    
}
