package babinvas.ucanaccesshibernatedialect.exampleuse.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Passport")
public class Passport {
	@Id
	@Column(name = "employee_id", nullable = false)
	private long employeeId;

	private int series;
	private int number;
	private String authority;

	@Column(name = "issued_date")
	private Date issuedDate;

	@OneToOne(optional = false, mappedBy = "passport")
	private Employee owner;

	public Passport() {
	}

	public Passport(Employee employee) {
		this.employeeId = employee.getId();
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employee) {
		this.employeeId = employee.getId();
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

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}
}
