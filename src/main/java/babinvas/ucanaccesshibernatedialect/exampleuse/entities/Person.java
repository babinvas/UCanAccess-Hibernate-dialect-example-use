package babinvas.ucanaccesshibernatedialect.exampleuse.entities;

import javax.persistence.*;

@Entity
@Table(name = "Person")
public class Person {
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	private String surname;
	private String name;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "passport_id")
	private Passport passport;

	@Transient
	private AddressPerson address;

	private String email;

	public Person() {
	}

	public Person(long id, String surname, String name) {
		this.id = id;
		this.surname = surname;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public AddressPerson getAddress() {
		return address;
	}

	public void setAddress(AddressPerson address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", surname='" + surname + '\'' +
				", name='" + name + '\'' +
				", Passport{" +
				"series=" + passport.getSeries() +
				", number=" + passport.getNumber() +
				", authority='" + passport.getAuthority() + '\'' +
				", issuedDate=" + passport.getIssuedDate() +
				'}' +
				", AddressPerson{" +
				"registrationAddress='" + address.getRegistrationAddress() + '\'' +
				", correspondenceAddress='" + address.getCorrespondenceAddress() + '\'' +
				'}' +
				", email='" + email + '\'' +
				'}';
	}
}
