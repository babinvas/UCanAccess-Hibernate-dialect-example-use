package babinvas.ucanaccesshibernatedialect.exampleuse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public AddressPerson() {
	}

	public AddressPerson(Person person) {
		this.id = person.getId();
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

	@Override
	public String toString() {
		return "AddressPerson{" +
				"id=" + id +
				", registrationAddress='" + registrationAddress + '\'' +
				", correspondenceAddress='" + correspondenceAddress + '\'' +
				'}';
	}
}
