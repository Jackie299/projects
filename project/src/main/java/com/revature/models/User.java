package com.revature.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 * <li>First Name</li>
 * <li>Last Name</li>
 * <li>Email</li>
 * <li>Phone Number</li>
 * <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {

	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private long phone;

	public User() {
		super();
	}

	/**
	 * This includes the minimum parameters needed for the
	 * {@link com.revature.models.AbstractUser} class. If other fields are needed,
	 * please create additional constructors.
	 */
	public User(int id, String username, String password, Role role) {
		super(id, username, password, role);
	}

	public User(String firstName, String lastName, String email, String address, long phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", address=" + address
				+ ", phone=" + phone + ", id=" + getId() + ", username=" + getUsername() + ", password="
				+ getPassword() + ", role=" + getRole() + "]";
	}

	
}
