package babinvas.ucanaccesshibernatedialect.exampleuse.repository;

import babinvas.ucanaccesshibernatedialect.exampleuse.entities.AddressPerson;
import babinvas.ucanaccesshibernatedialect.exampleuse.entities.Passport;
import babinvas.ucanaccesshibernatedialect.exampleuse.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class PassportRepositoryManager implements RepositoryManager<Passport> {
	EntityManagerFactory entityManagerFactory;

	public PassportRepositoryManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public void createEntity(Passport passport) {
		EntityManager entityManager = getEntityManager();

		entityManager.persist(passport);
		executeTransaction(entityManager);
	}

	@Override
	public Passport readEntity(long id) {
		EntityManager entityManager = getEntityManager();

		Passport passport = entityManager.find(Passport.class, id);
		AddressPerson addressPerson = entityManager.find(AddressPerson.class, id);

		passport.getOwner().setAddress(addressPerson);

		return passport;
	}

	@Override
	public void updateEntity(Passport passport) {
		EntityManager entityManager = getEntityManager();

		entityManager.merge(passport);
		executeTransaction(entityManager);
	}

	@Override
	public void deleteEntity(long id) {
		EntityManager entityManager = getEntityManager();

		Person person = entityManager.find(Person.class, id);
		Passport passport = new Passport(person);

		entityManager.merge(passport);
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
