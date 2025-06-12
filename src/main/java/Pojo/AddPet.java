package Pojo;

import java.util.List;

public class AddPet {

	private int id ; 
	private String name;
	private String status ; 
	private Addcategory category;
	private List<Addtags> tags;
	
	private List<String> photoUrls;
	
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Addcategory getCategory() {
		return category;
	}
	public void setCategory(Addcategory category) {
		this.category = category;
	}
	public List<Addtags> getTags() {
		return tags;
	}
	public void setTags(List<Addtags> tags) {
		this.tags = tags;
	}
	public List<String> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(List<String> string) {
		this.photoUrls = string;
	}

	
}
