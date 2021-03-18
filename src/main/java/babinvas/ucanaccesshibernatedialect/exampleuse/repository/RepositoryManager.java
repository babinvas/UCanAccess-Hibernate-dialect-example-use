package babinvas.ucanaccesshibernatedialect.exampleuse.repository;

public interface RepositoryManager<T> {
	void createEntity(T t);
	T readEntity(long id);
	void updateEntity(T t);
	void deleteEntity(long id);
}
