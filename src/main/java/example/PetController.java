package example;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.reactivex.Maybe;

import java.util.List;
import java.util.Optional;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Get(produces = APPLICATION_JSON)
    Maybe<List<Pet>> index() {
        return petService.allPets();
    }

    @Get("/{name}")
    @Produces(APPLICATION_JSON)
    Optional<Pet> findPet(String name) {
        return petService.findPet(name);
    }

    @Post(processes = APPLICATION_JSON)
    @Status(HttpStatus.CREATED)
    void savePet(String name, int age) {
        petService.savePet(new Pet(name, age));
    }

    @Delete("/{name}")
    @Status(HttpStatus.NO_CONTENT)
    void deletePet(String name) {
        petService.deletePet(name);
    }
}
