package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
class RecipeReactiveRepositoryIT {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @BeforeEach
    public void setUp() throws Exception {

        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void recipeSaveTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setDescription("Good");

        recipeReactiveRepository.save(recipe).block();

        Long count = recipeReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }
}