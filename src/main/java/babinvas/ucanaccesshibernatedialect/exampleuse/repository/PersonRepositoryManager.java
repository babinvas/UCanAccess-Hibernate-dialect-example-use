package babinvas.ucanaccesshibernatedialect.exampleuse.repository;

import babinvas.ucanaccesshibernatedialect.exampleuse.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PersonRepositoryManager implements RepositoryManager<Person> {
	EntityManager entityManager;


	public PersonRepositoryManager(EntityManager entityManager) {
		this.entityManager = entityManager;
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

	@Override
	public void createEntity(Person person) {
		entityManager.persist(person);
		executeTransaction();
	}

	@Override
	public Person readEntity(long id) {
		return null;
	}

	@Override
	public void updateEntity(long id, Person person) {
	}

	@Override
	public void deleteEntity(long id) {
	}
}
