/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikolevy.dragonsweb.view;

import mikolevy.dragonslib.events.ChangeOnDragonList;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import mikolevy.dragonsejb.ControlNewDragons;
import mikolevy.dragonsejb.DragonService;
import mikolevy.dragonslib.entities.Cave;
import mikolevy.dragonslib.entities.Color;
import mikolevy.dragonslib.entities.Dragon;
import mikolevy.dragonslib.events.DragonsChanged;

/**
 *
 * @author Mikolaj
 */

//@ViewScoped
//@ManagedBean
@ConversationScoped
@Named
@Log
public class EditDragon implements Serializable{
    
    @EJB
    private DragonService dragonService;
    
    @Getter
    @Setter
    private Dragon dragon;
    
    @Getter
    @Setter
    private Cave cave;
    
    @Getter
    @Setter
    private int dragonId;
    
    @Inject
    Conversation conv;
    
    @Inject
    @ChangeOnDragonList
    Event<DragonsChanged> dragonsChanged;
    
    private List<SelectItem> colorSelectItems;
    private List<SelectItem> caveSelectItems;
    
    public void init() {
        if (dragon == null && dragonId != 0) {
            dragon = dragonService.findDragon(dragonId);
        } else if (dragon == null && dragonId == 0) {
            dragon = new Dragon();
        }
        if (dragon == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        } else {
            if (conv.isTransient()) {
                conv.begin();
            }
        }
        if (null == dragon.getCave())
            cave = dragonService.findAllCaves().get(0);
        else 
            cave = dragonService.findCave(dragon.getCave().getId());
    }
    
    public List<SelectItem> getColorsAsSelectItems() {
        if (null == colorSelectItems) {
            colorSelectItems = this.initColorsSelectItemsList();
        }
        return colorSelectItems;
    }
    
    private List<SelectItem> initColorsSelectItemsList() {
        List<SelectItem> colorsAsSelectItems = new ArrayList<>();
        colorsAsSelectItems.add(new SelectItem(null, "---"));

        for (Color color : Color.values()) {
            colorsAsSelectItems.add(new SelectItem(color, color.name()));
        }
        return colorsAsSelectItems;
    }
    
    public List<SelectItem> getCavesAsSelectItems() {
        if (caveSelectItems == null) {
            caveSelectItems = this.initCavesSelectItemsList();
        }
        return caveSelectItems;
    }
    
    private List<SelectItem> initCavesSelectItemsList() {
        List<SelectItem> caveAsSelectItems = new ArrayList<>();
        for (Cave cave : dragonService.findAllCaves()) {
            caveAsSelectItems.add(new SelectItem(cave, Integer.toString(cave.getSurface())));
        }
        return caveAsSelectItems;
    }
    
    //@ControlNewDragons
    public String saveDragon() {
        dragon.setCave(cave);
        dragonService.saveDragon(dragon);
        DragonsChanged changed = new DragonsChanged();
        dragonsChanged.fire(changed);
        return "list_caves?faces-redirect=true";
    }
    
    public String getCID() {
        return conv.getId();
    }
}
