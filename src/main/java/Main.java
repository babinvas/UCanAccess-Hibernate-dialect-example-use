import babinvas.ucanaccesshibernatedialect.exampleuse.entities.AddressPerson;
import babinvas.ucanaccesshibernatedialect.exampleuse.entities.Person;
import babinvas.ucanaccesshibernatedialect.exampleuse.entities.Passport;
import babinvas.ucanaccesshibernatedialect.exampleuse.repository.AddressPersonRepositoryManager;
import babinvas.ucanaccesshibernatedialect.exampleuse.repository.PassportRepositoryManager;
import babinvas.ucanaccesshibernatedialect.exampleuse.repository.PersonRepositoryManager;
import babinvas.ucanaccesshibernatedialect.exampleuse.repository.RepositoryManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	private final static EntityManagerFactory entityManagerFactory =
			Persistence.createEntityManagerFactory("UCanAccess-Hibernate-dialect-example-use");
	private static RepositoryManager<Person> personRepositoryManager;
	private static RepositoryManager<Passport> passportRepositoryManager;
	private static RepositoryManager<AddressPerson> addressPersonRepositoryManager;

	public static void main(String[] args) {
		createFirstEntity();

		personRepositoryManager = new PersonRepositoryManager(getEntityManagerFactory());
		passportRepositoryManager = new PassportRepositoryManager(getEntityManagerFactory());
		addressPersonRepositoryManager = new AddressPersonRepositoryManager(getEntityManagerFactory());

		// Creating instances of entity classes
		// Создание экземпляров классов сущностей
		Person person = getPerson(2L, "Testov_2", "Test_2", "test_2@gmail.com");
		Passport passport = getPassport(person, 2345, 234567, "Perm", "05.01.2001");

		passport.setOwner(person);
		person.setPassport(passport);

		AddressPerson addressPerson = getAddressPerson(person, "London, the palace", "Paris, under the bridge");

		personRepositoryManager.createEntity(person);
		addressPersonRepositoryManager.updateEntity(addressPerson);

		// Reading instances of classes from the database
		// Чтение экземпляров классов из базы данных
		readAndPrint(1L);
		readAndPrint(2L);

		// Updating instances of classes to the database
		// Обновление экземпляров классов в базе данных
		Person person1 = personRepositoryManager.readEntity(1L);
		Passport passport1 = passportRepositoryManager.readEntity(1L);
		AddressPerson addressPerson1 = addressPersonRepositoryManager.readEntity(1L);

		person1.setEmail("changed_test_1@gmail.com");
		personRepositoryManager.updateEntity(person1);

		passport1.setSeries(1111);
		passportRepositoryManager.updateEntity(passport1);

		addressPerson1.setCorrespondenceAddress("Emerald City, the palace");
		addressPersonRepositoryManager.updateEntity(addressPerson1);

		readAndPrint(1L);

		// Deleting instances of classes from the database
		// Удаление экземпляров классов из базы данных
		passportRepositoryManager.deleteEntity(1L);
		addressPersonRepositoryManager.deleteEntity(1L);
		personRepositoryManager.deleteEntity(2L);

		readAndPrint(1L);
	}

	private static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	private static Person getPerson(long id, String surname, String name, String email) {
		Person person = new Person(id, surname, name);
		person.setEmail(email);

		return person;
	}

	private static Passport getPassport(Person person, int series, int number, String authority, String issuedDate) {
		Passport passport = new Passport(person);
		passport.setSeries(series);
		passport.setNumber(number);
		passport.setAuthority(authority);

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			Date date = simpleDateFormat.parse(issuedDate);
			passport.setIssuedDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return passport;
	}

	private static AddressPerson getAddressPerson(Person person, String registrationAddress, String correspondenceAddress) {
		AddressPerson addressPerson = new AddressPerson(person);
		addressPerson.setRegistrationAddress(registrationAddress);
		addressPerson.setCorrespondenceAddress(correspondenceAddress);

		return addressPerson;
	}

	private static void readAndPrint(long id) {
		Person person = personRepositoryManager.readEntity(id);
		Passport passport = passportRepositoryManager.readEntity(id);
		AddressPerson addressPerson = addressPersonRepositoryManager.readEntity(id);

		/*
		System.out.println("\n" + person.getPassport().getSeries() + " " +
				person.getPassport().getNumber() + " " +
				person.getPassport().getOwner().getSurname() + " = "
				+ person.getSurname() + "\n");
		 */

		System.out.println(person + "\n");
		System.out.println(passport + "\n");
		System.out.println(addressPerson + "\n\n");
	}

	private static void createFirstEntity() {
		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("UCanAccess-Hibernate-dialect-example-use");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();

		Person person = new Person(1L, "Testov_1", "Test_1");
		person.setEmail("test_1@gmail.com");

		Passport passport = new Passport(person);
		passport.setSeries(1234);
		passport.setNumber(123456);
		passport.setAuthority("Moscow");

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			Date issuedDate = simpleDateFormat.parse("01.11.2000");
			passport.setIssuedDate(issuedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		passport.setOwner(person);
		person.setPassport(passport);

		entityManager.persist(person);

		if (entityTransaction.isActive()) {
			entityTransaction.commit();
		}

		AddressPerson addressPerson = entityManager.find(AddressPerson.class, person.getId());
		addressPerson.setRegistrationAddress("Moscow, the Kremlin");
		addressPerson.setCorrespondenceAddress("Oz, the rickety shack");

		entityTransaction.begin();
		entityManager.persist(addressPerson);

		if (entityTransaction.isActive()) {
			entityTransaction.commit();
		}

		entityManager.getEntityManagerFactory().close();
		entityManager.close();
	}
}
