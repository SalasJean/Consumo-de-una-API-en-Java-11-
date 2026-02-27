package modelos;

public class User {
	private int id;
	private String name;
	private String username;
	private String email;
	private Address address; //recuerda que este addres es un objeto anidado si? dentro de este mismo objeto tenemos tenemos otro objeto recuerda
	private String phone;
	private String website;
	private Company company;//a este objeto tambien sele llama anidado porque dentro de si tiene datos recuerda
	
	//no entiendo porque el ide me da solo algunos metodos acaso es no es una mala practcica? recuerda
	
	public User() {
		
	}

	/**
	 * @param id
	 * @param name
	 * @param username
	 * @param email
	 * @param address
	 * @param phone
	 * @param website
	 * @param company
	 */
	public User(int id, String name, String username, String email, Address address, String phone, String website,
			Company company) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.website = website;
		this.company = company;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", address="
				+ address + ", phone=" + phone + ", website=" + website + ", company=" + company + "]";
	}
	//otra forma de declarar el metood toString es tambien con un objeto recuerda si?
	
	

}
