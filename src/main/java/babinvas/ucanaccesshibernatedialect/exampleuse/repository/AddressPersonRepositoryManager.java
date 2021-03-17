package babinvas.ucanaccesshibernatedialect.exampleuse.repository;

import babinvas.ucanaccesshibernatedialect.exampleuse.entities.AddressPerson;
import babinvas.ucanaccesshibernatedialect.exampleuse.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class AddressPersonRepositoryManager implements RepositoryManager<AddressPerson> {
	EntityManager entityManager;

	public AddressPersonRepositoryManager(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void createEntity(AddressPerson addressPerson) {
		entityManager.persist(addressPerson);
		executeTransaction();
	}

	@Override
	public AddressPerson readEntity(long id) {
		AddressPerson addressPerson = entityManager.find(AddressPerson.class, id);
		executeTransaction();

		return addressPerson;
	}

	@Override
	public void updateEntity(AddressPerson addressPerson) {
		entityManager.merge(addressPerson);
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
