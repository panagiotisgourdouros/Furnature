package team.groupproject.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "products_images")
@NamedQueries({
    @NamedQuery(name = "ProductsImages.findAll", query = "SELECT p FROM ProductsImages p"),
    @NamedQuery(name = "ProductsImages.findById", query = "SELECT p FROM ProductsImages p WHERE p.id = :id"),
    @NamedQuery(name = "ProductsImages.findByImagePath", query = "SELECT p FROM ProductsImages p WHERE p.imagePath = :imagePath")})

public class ProductsImages implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "image_path")
    private String imagePath;

    public ProductsImages() {
    }

    public ProductsImages(Integer id) {
        this.id = id;
    }

    public ProductsImages(Integer id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    public ProductsImages(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductsImages)) {
            return false;
        }
        ProductsImages other = (ProductsImages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "team.groupproject.entity.ProductsImages[ id=" + id + " ]";
    }

}
