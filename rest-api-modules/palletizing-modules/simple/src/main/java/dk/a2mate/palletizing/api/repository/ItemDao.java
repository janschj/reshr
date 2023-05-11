package dk.a2mate.palletizing.api.repository;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity(name="item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDao {

	@Id
	private String id;
	private String name;
	private Integer length;
	private Integer width;
	private Integer height;
	private Integer weight;
	
	
	public ItemDao() {
		
	}
	public ItemDao(String id, String name, Integer length, Integer width, Integer height, Integer weight) {
		super();
		this.id = id;
		this.name = name;
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
