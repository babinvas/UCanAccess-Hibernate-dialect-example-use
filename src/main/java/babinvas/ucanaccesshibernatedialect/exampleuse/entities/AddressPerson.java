package babinvas.ucanaccesshibernatedialect.exampleuse.entities;

import javax.persistence.*;

@Entity
@Table(name = "Person")
public class AddressPerson {
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "registration_address")
	private String registrationAddress;

	@Column(name = "correspondence_address")
	private String correspondenceAddress;

	@Transient
	private Person tenant;

	public AddressPerson() {
	}

	public AddressPerson(Person tenant) {
		this.id = tenant.getId();
		this.tenant = tenant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegistrationAddress() {
		return registrationAddress;
	}

	public void setRegistrationAddress(String registrationAddress) {
		this.registrationAddress = registrationAddress;
	}

	public String getCorrespondenceAddress() {
		return correspondenceAddress;
	}

	public void setCorrespondenceAddress(String correspondenceAddress) {
		this.correspondenceAddress = correspondenceAddress;
	}

	public Person getTenant() {
		return tenant;
	}

	public void setTenant(Person tenant) {
		this.tenant = tenant;
	}

	@Override
	public String toString() {
		return "AddressPerson{" +
				"id=" + id +
				", registrationAddress='" + registrationAddress + '\'' +
				", correspondenceAddress='" + correspondenceAddress + '\'' +
				", Person{" +
				"surname='" + tenant.getSurname() + '\'' +
				", name='" + tenant.getName() + '\'' +
				", Passport{" +
				"series=" + tenant.getPassport().getSeries() +
				", number=" + tenant.getPassport().getNumber() +
				", authority='" + tenant.getPassport().getAuthority() + '\'' +
				", issuedDate=" + tenant.getPassport().getIssuedDate() +
				'}' +
				", email='" + tenant.getEmail() + '\'' +
				'}' +
				'}';
	}
}
