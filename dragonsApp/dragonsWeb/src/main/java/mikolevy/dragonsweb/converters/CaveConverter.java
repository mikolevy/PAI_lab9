package mikolevy.dragonsweb.converters;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import mikolevy.dragonsejb.DragonService;
import mikolevy.dragonslib.entities.Cave;

/**
 *
 * @author Mikolaj
 */

@ManagedBean
@RequestScoped
public class CaveConverter implements Converter {
    
    @EJB
    private DragonService dragonService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (("---").equals(value))
            return null;
        
        Cave cave = dragonService.findCaveBySurface(Integer.parseInt(value));
        return cave;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (null == value)
            return "---";
        return ((Cave) value).getSurface() + "";
    }
}
