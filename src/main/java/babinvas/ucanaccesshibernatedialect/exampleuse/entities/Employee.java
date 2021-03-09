package babinvas.ucanaccesshibernatedialect.exampleuse.entities;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	private String surname;
	private String name;
	private String patronymic;

	@Column(name = "abbreviation_to_whom")
	private String abbreviationToWhom;

	@Column(name = "respectful_word_ending")
	private String respectfulWordEnding;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "passport_id")
	private Passport passport;

	private AddressEmployee address;

	private String email;

	@Column(name = "email_2")
	private String email2;

	public Employee() {
	}

	public Employee(long id, String surname, String name, String patronymic) {
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
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

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getAbbreviationToWhom() {
		return abbreviationToWhom;
	}

	public void setAbbreviationToWhom(String abbreviationToWhom) {
		this.abbreviationToWhom = abbreviationToWhom;
	}

	public String getRespectfulWordEnding() {
		return respectfulWordEnding;
	}

	public void setRespectfulWordEnding(String respectfulWordEnding) {
		this.respectfulWordEnding = respectfulWordEnding;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public AddressEmployee getAddress() {
		return address;
	}

	public void setAddress(AddressEmployee address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}
}
