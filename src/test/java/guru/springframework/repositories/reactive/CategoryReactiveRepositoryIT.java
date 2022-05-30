package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@SpringBootTest
public class CategoryReactiveRepositoryIT extends TestCase {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void saveTest() throws Exception {
        Category category = new Category();
        category.setDescription("nom nom");

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void findByDescriptionTest() throws Exception {
        Category category = new Category();
        category.setDescription("Example");

        Category categoryMono = categoryReactiveRepository.findByDescription("Example").block();

        assertNotNull(categoryMono.getId());
    }
}