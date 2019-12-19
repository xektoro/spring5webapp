package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.model.Author;
import org.springframework.data.repository.CrudRepository;

// Author is the entity and its id is of type Long => Long is the second argument
// now Spring Data will see this and wire it into Spring Context => they will be available for use
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
