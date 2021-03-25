package babinvas.ucanaccesshibernatedialect.exampleuse.repository;

import babinvas.ucanaccesshibernatedialect.exampleuse.entities.AddressPerson;
import babinvas.ucanaccesshibernatedialect.exampleuse.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class AddressPersonRepositoryManager implements RepositoryManager<AddressPerson> {
	EntityManagerFactory entityManagerFactory;

	public AddressPersonRepositoryManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public void createEntity(AddressPerson addressPerson) {
		EntityManager entityManager = getEntityManager();

		entityManager.persist(addressPerson);
		executeTransaction(entityManager);
	}

	@Override
	public AddressPerson readEntity(long id) {
		EntityManager entityManager = getEntityManager();

		AddressPerson addressPerson = entityManager.find(AddressPerson.class, id);
		Person person = entityManager.find(Person.class, id);

		addressPerson.setTenant(person);

		return addressPerson;
	}

	@Override
	public void updateEntity(AddressPerson addressPerson) {
		EntityManager entityManager = getEntityManager();

		entityManager.merge(addressPerson);
		executeTransaction(entityManager);
	}

	@Override
	public void deleteEntity(long id) {
		EntityManager entityManager = getEntityManager();

		Person person = entityManager.find(Person.class, id);
		AddressPerson addressPerson = new AddressPerson(person);

		entityManager.merge(addressPerson);
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
