package whattocook.repositories;

import org.springframework.data.repository.CrudRepository;
import whattocook.models.Item;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<Item> findByName(String name);
}
