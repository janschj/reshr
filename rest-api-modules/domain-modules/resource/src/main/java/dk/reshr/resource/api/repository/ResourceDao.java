package dk.reshr.resource.api.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name="resource")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ResourceId.class)
public class ResourceDao {

	@Id
	private Integer accountId;
	@Id
	private Integer id;
	
	private String name;
		
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

}
