package guru.springframework.repositories.reactive;

import guru.springframework.Spring5MongoRecipeAppApplication;
import guru.springframework.domain.UnitOfMeasure;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= Spring5MongoRecipeAppApplication.class)
@DataMongoTest
@Ignore
public class UnitOfMeasureReactiveRepositoryIT extends TestCase {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void findByDescriptionTest() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("Big");

        unitOfMeasureReactiveRepository.save(unitOfMeasure).then().block();

        UnitOfMeasure unitOfMeasure1 = unitOfMeasureReactiveRepository.findByDescription("Big").block();

        assertNotNull(unitOfMeasure1.getId());
    }

    @Test
    public void saveTest() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("Big");

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        Long count = unitOfMeasureReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }
}