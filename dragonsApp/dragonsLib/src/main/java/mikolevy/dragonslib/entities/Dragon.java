package mikolevy.dragonslib.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Mikolaj
 */
@ToString(of = "name")
@EqualsAndHashCode(exclude = "cave")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dragons")
@NamedQueries({
    @NamedQuery(name = "Dragon.findAll", query = "SELECT d FROM Dragon d"),
    @NamedQuery(name = "Dragon.podatek", query = "UPDATE Dragon d SET d.gold = d.gold - ?1"),
    @NamedQuery(name = "Dragon.getTop", query = "SELECT d FROM Dragon d ORDER BY d.gold DESC")
})
public class Dragon implements Serializable{
    
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String name;
    
    @Column
    private Color color = Color.GREEN;
    
    @Column
    private int gold = 100;
    
    @ManyToOne
    @JoinColumn(name = "cave", referencedColumnName = "id")
    private Cave cave;

}
