package resources;

//special class in java which has collection of constant and methods
public enum APIResources {

	
		
	
	AddPetAPI("/pet"),
	GetPetAPI("/pet"),
	DeletePetAPI("/pet"),
	UpdatePetAPI("/pet"); 
	

  
	public String Resource;

APIResources(String Resource )
{
	this.Resource= Resource;

}
public String getResource()
{
	return Resource;
}

}
