package mikolevy.dragonsweb.view;

import java.io.IOException;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import mikolevy.dragonsejb.DragonService;
import mikolevy.dragonslib.entities.Dragon;

/**
 *
 * @author Mikolaj
 */

@ViewScoped
@ManagedBean
@Log
public class ViewDragon {
    
    @EJB
    private DragonService dragonService;
    
    @Getter
    @Setter
    private Dragon dragon;
    
    @Getter
    @Setter
    private int dragonId;
    
    public void init() {
        if (dragon == null) 
            dragon = dragonService.findDragon(dragonId);
        if (dragon == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }
}
