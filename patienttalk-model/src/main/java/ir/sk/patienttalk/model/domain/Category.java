package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.persistence.jpa.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/11/2017.
 */
@Entity
@Table(name = "TBL_PT_CATEGORY", schema = "", catalog = "PATIENTTALK")
public class Category extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_CATEGORY_ID", unique = true, nullable = false)
    private long id;

    @Column(name = "TITLE", nullable = false, length = 50)
    @Basic
    @Size(max = 50, message="{Size.Category.title.validation}")
    private String title;

    @Size(max = 50, message="{Size.Category.code.validation}")
    @Column(name = "CODE", nullable = false, length = 50, unique = true)
    @Basic
    private String code;

    @Column(name = "COUNT", nullable = false, insertable = false, updatable = false)
    @Basic
    private int count;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Transient
    private List<Category> children;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    // A convenience method simplifies relationship management
    public void addCategory(Category category) {
        if (category == null)
            throw new NullPointerException("Can't add null Category"); // Be defensive
        if (category.getChildren() != null)
            throw new IllegalStateException("Category is already assigned to an Category");
        getChildren().add(category);
        category.setParentId(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        if (count != category.count) return false;
        if (title != null ? !title.equals(category.title) : category.title != null) return false;
        if (code != null ? !code.equals(category.code) : category.code != null) return false;
        if (parentId != null ? !parentId.equals(category.parentId) : category.parentId != null) return false;
        return children != null ? children.equals(category.children) : category.children == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + count;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", count=" + count +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
