package mikolevy.dragonsweb.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import mikolevy.dragonsejb.DragonService;
import mikolevy.dragonslib.entities.Cave;

/**
 *
 * @author Mikolaj
 */

@ViewScoped
@ManagedBean
@Log
public class ViewCave implements Serializable{
    
    @EJB
    private DragonService dragonService;
    
    @Getter
    @Setter
    private int caveId;
    
    @Getter
    @Setter
    private Cave cave;
    
    public void init() {
        if (cave == null) {
            cave = dragonService.findCave(caveId);
        }
        if (cave == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
