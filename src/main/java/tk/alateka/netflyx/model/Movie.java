package tk.alateka.netflyx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private int id;
    
    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name = "description", length = 500)
    private String description;

    @Getter @Setter @Column(name = "poster", length = 150)
    private String poster;

    @Getter @Setter @Column(name = "url", length = 150)
    private String url;

    public Movie() {
    }
}
