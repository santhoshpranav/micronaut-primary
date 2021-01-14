package example;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import example.PetService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import io.reactivex.Maybe;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
class PetService {
    private final Map<String, Pet> pets = new ConcurrentHashMap<>();
    @Inject
    private HTTPClientBuilder httpClientBuilder;

    public PetService() {
        pets.put("Dino", new Pet("Dino", 12));
        pets.put("Bobcat", new Pet("Bobcat", 8));
    }

    Maybe<List<Pet>> allPets() {
        return httpClientBuilder.getPets();
    }

    Optional<Pet> findPet(@NotBlank String name) {
        return Optional.ofNullable(pets.get(name));
    }

    void savePet(@NotNull @Valid Pet pet) {
    	BitTableWrite.writeSimple();
    }

    void deletePet(@NotBlank String name) {
        pets.remove(name);
    }
}
