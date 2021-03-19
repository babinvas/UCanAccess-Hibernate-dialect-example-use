package babinvas.ucanaccesshibernatedialect.exampleuse.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Passport")
public class Passport {
	@Id
	@Column(name = "person_id", nullable = false)
	private long personId;

	private int series;
	private int number;
	private String authority;

	@Column(name = "issued_date")
	private Date issuedDate;

	@OneToOne(optional = false, mappedBy = "passport")
	private Person owner;

	public Passport() {
	}

	public Passport(Person person) {
		this.personId = person.getId();
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Passport{" +
				"personId=" + personId +
				", series=" + series +
				", number=" + number +
				", authority='" + authority + '\'' +
				", issuedDate=" + issuedDate +
				", Person{" +
				"surname='" + owner.getSurname() + '\'' +
				", name='" + owner.getName() + '\'' +
				", AddressPerson{" +
				"registrationAddress='" + owner.getAddress().getRegistrationAddress() + '\'' +
				", correspondenceAddress='" + owner.getAddress().getCorrespondenceAddress() + '\'' +
				'}' +
				", email='" + owner.getEmail() + '\'' +
				'}';
	}
}
