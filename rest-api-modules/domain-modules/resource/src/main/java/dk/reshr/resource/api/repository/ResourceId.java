package dk.reshr.resource.api.repository;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable 
public class ResourceId  implements Serializable {
    private Integer accountId;

    private Integer id;

    public ResourceId() {
    }

    public ResourceId(Integer accountId, Integer id) {
        this.accountId = accountId;
        this.id = id;
    }

    // equals() and hashCode()
}