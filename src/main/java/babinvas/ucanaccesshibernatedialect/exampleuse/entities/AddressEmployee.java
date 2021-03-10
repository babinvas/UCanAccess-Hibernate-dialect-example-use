package babinvas.ucanaccesshibernatedialect.exampleuse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class AddressEmployee {
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "registration_address")
	private String registrationAddress;

	@Column(name = "correspondence_address")
	private String correspondenceAddress;

	public AddressEmployee() {
	}

	public AddressEmployee(Employee employee) {
		this.id = employee.getId();
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
}
