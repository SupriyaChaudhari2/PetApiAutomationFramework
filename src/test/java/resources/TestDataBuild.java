package resources;

import java.util.Arrays;

import Pojo.AddPet;
import Pojo.Addcategory;
import Pojo.Addtags;

public class TestDataBuild {

	public Pojo.AddPet AddPet(int id , String Name , String Status)
	{
		  AddPet pet = new AddPet();
		   pet.setId(id);
		   pet.setName(Name);
		
		   pet.setStatus(Status);
		   pet.setPhotoUrls(Arrays.asList("string"));
		   
		   Addcategory c = new Addcategory();
		   c.setId(10);
		   c.setName("Dogs");
		   pet.setCategory(c);

		   Addtags t = new Addtags();
		   t.setId(0);
		   t.setName("string");
		   pet.setTags(Arrays.asList(t));
		   return pet;
	}
	
}
