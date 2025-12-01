package service;

import dao.ActorDAO;
import dao.CategoryDAO;
import dao.FilmDAO;
import dao.LanguageDAO;
import entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmService {
    private final FilmDAO filmDAO;
    private final ActorDAO actorDAO;
    private final CategoryDAO categoryDAO;
    private final LanguageDAO languageDAO;

    public FilmService(FilmDAO filmDAO, ActorDAO actorDAO,
                       CategoryDAO categoryDAO, LanguageDAO languageDAO) {
        this.filmDAO = filmDAO;
        this.actorDAO = actorDAO;
        this.categoryDAO = categoryDAO;
        this.languageDAO = languageDAO;
    }

    public Film createNewFilm(String title, String description,
                              Integer releaseDate, String language,
                              String originalLanguage, Integer rentalDuration, BigDecimal rentalRate,
                              Integer length, String rating, List<SpecialFeature> specialFeature,
                              Set<Actor> actors, Set<String> categoriesName) {

        Set<Actor> actorsInDB = getActorsFromFilm(actors);
        Set<Category> categories = getCategoryFromFilm(categoriesName);

        Language languageInFilm = getLanguageFromFilm(language);
        Language originalLanguageInFilm = getLanguageFromFilm(originalLanguage);

        Film film =  Film.builder()
                .title(title)
                .description(description)
                .release_year(releaseDate)
                .language(languageInFilm)
                .originalLanguage(originalLanguageInFilm)
                .rental_duration(rentalDuration)
                .rental_rate(rentalRate)
                .length(length)
                .rating(rating)
                .specialFeatures(specialFeature)
                .actors(actorsInDB)
                .category(categories)
                .build();

        filmDAO.create(film);
        return film;
    }

    private Set<Actor> getActorsFromFilm(Set<Actor> actors) {
        Set<Actor> actorsInDB = new HashSet<>();

        for (Actor actor : actors) {
            Actor actorInBD = actorDAO.findActorByFullName(actor.getFirstName(), actor.getLastName());
            if (actorInBD == null) {
                actorInBD = actorDAO.create(actor);
            }
            actorsInDB.add(actorInBD);
        }
        return actorsInDB;
    }

    private Set<Category> getCategoryFromFilm(Set<String> categoriesName) {
        Set<Category> categoriesInDB = new HashSet<>();
        for (String categoryName : categoriesName) {
            Category category = categoryDAO.findByName(categoryName);
            if (category == null) {
                Category cat = Category.builder()
                        .name(categoryName)
                        .build();
                category = categoryDAO.create(cat);
            }
            categoriesInDB.add(category);
        }
        return categoriesInDB;
    }

    private Language getLanguageFromFilm(String language) {
        Language languageInFilm = languageDAO.findByName(language);
        if (languageInFilm == null) {
            languageInFilm = languageDAO.create(Language.builder().name(language).build());
        }
        return languageInFilm;
    }


}
