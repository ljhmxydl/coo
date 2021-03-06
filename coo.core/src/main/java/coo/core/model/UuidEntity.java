package coo.core.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;

/**
 * UUID实体基类
 */
@MappedSuperclass
public abstract class UuidEntity implements Serializable {
  private static final long serialVersionUID = -2735777080640816548L;
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
  @DocumentId
  protected String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(getId()).hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    UuidEntity other = (UuidEntity) obj;
    return new EqualsBuilder().append(getId(), other.getId()).isEquals();
  }
}
