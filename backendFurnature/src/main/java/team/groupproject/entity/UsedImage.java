package team.groupproject.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "used_images")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsedImage.findAll", query = "SELECT u FROM UsedImage u"),
    @NamedQuery(name = "UsedImage.findById", query = "SELECT u FROM UsedImage u WHERE u.id = :id"),
    @NamedQuery(name = "UsedImage.findByImagePath", query = "SELECT u FROM UsedImage u WHERE u.imagePath = :imagePath")})
public class UsedImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "image_path")
    private String imagePath;

    @JoinColumn(name = "used_product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UsedProduct usedProductId;

    public UsedImage() {
    }

    public UsedImage(Integer id) {
        this.id = id;
    }

    public UsedImage(Integer id, String imagePath) {
        this.id = id;
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

    public UsedProduct getUsedProductId() {
        return usedProductId;
    }

    public void setUsedProductId(UsedProduct usedProductId) {
        this.usedProductId = usedProductId;
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
        if (!(object instanceof UsedImage)) {
            return false;
        }
        UsedImage other = (UsedImage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "team.groupproject.entity.UsedImages[ id=" + id + " ]";
    }

}
