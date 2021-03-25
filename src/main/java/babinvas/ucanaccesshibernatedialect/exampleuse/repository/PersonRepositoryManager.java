package babinvas.ucanaccesshibernatedialect.exampleuse.repository;

import babinvas.ucanaccesshibernatedialect.exampleuse.entities.AddressPerson;
import babinvas.ucanaccesshibernatedialect.exampleuse.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class PersonRepositoryManager implements RepositoryManager<Person> {
	EntityManagerFactory entityManagerFactory;

	public PersonRepositoryManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public void createEntity(Person person) {
		EntityManager entityManager = getEntityManager();

		entityManager.persist(person);
		executeTransaction(entityManager);
	}

	@Override
	public Person readEntity(long id) {
		EntityManager entityManager = getEntityManager();

		Person person = entityManager.find(Person.class, id);
		AddressPerson addressPerson = entityManager.find(AddressPerson.class, id);

		person.setAddress(addressPerson);

		return person;
	}

	@Override
	public void updateEntity(Person person) {
		EntityManager entityManager = getEntityManager();

		entityManager.merge(person);
		executeTransaction(entityManager);
	}

	@Override
	public void deleteEntity(long id) {
		EntityManager entityManager = getEntityManager();

		Person person = entityManager.find(Person.class, id);

		entityManager.remove(person);
		executeTransaction(entityManager);
	}

	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	private void executeTransaction(EntityManager entityManager) {
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

	private void close(EntityManager entityManager) {
		entityManager.getEntityManagerFactory().close();
		entityManager.close();
	}
}
