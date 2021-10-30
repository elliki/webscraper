package io.github.elli.repository;
import org.springframework.data.repository.CrudRepository;
import io.github.elli.models.WebsiteData;

public class WebsiteContent {

// This will be AUTO IMPLEMENTED by Spring into a Bean called WebsiteContentRepository
// CRUD refers Create, Read, Update, Delete

    public interface WebsiteContentRepository extends CrudRepository<WebsiteData, String> {

    }
}
