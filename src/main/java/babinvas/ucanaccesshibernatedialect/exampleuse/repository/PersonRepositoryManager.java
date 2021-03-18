package babinvas.ucanaccesshibernatedialect.exampleuse.repository;

import babinvas.ucanaccesshibernatedialect.exampleuse.entities.AddressPerson;
import babinvas.ucanaccesshibernatedialect.exampleuse.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PersonRepositoryManager implements RepositoryManager<Person> {
	EntityManager entityManager;


	public PersonRepositoryManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void createEntity(Person person) {
		entityManager.persist(person);
		executeTransaction();
	}

	@Override
	public Person readEntity(long id) {
		Person person = entityManager.find(Person.class, id);

		AddressPersonRepositoryManager addressPersonRepositoryManager = new AddressPersonRepositoryManager(entityManager);
		AddressPerson addressPerson = addressPersonRepositoryManager.readEntity(id);
		person.setAddress(addressPerson);

		executeTransaction();

		return person;
	}

	@Override
	public void updateEntity(Person person) {
		entityManager.merge(person);
		executeTransaction();
	}

	@Override
	public void deleteEntity(long id) {
		Person person = entityManager.find(Person.class, id);
		entityManager.remove(person);

		executeTransaction();
	}

	private void executeTransaction() {
		EntityTransaction entityTransaction = entityManager.getTransaction();

		try {
			entityTransaction.begin();

			if (entityTransaction.isActive()) {
				entityTransaction.commit();
			}
		} catch (RuntimeException e) {
			entityTransaction.rollback();
			throw e;
		}
	}

	private void close() {
		entityManager.getEntityManagerFactory().close();
		entityManager.close();
	}
}
