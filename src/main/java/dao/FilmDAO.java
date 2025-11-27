package dao;

import entity.Actor;
import entity.Film;
import entity.Language;
import entity.SpecialFeature;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class FilmDAO extends AbstractHibernateDao<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film createNewFilm(String title, String description,
                              LocalDate releaseDate, Language language,
                              Language originalLanguage, Byte rentalDuration, BigDecimal rentalRate,
                              Integer length, String rating, List<SpecialFeature> specialFeature) {
        Film film = Film.builder()
                .title(title)
                .description(description)
                .release_year(releaseDate)
                .language(language)
                .originalLanguage(originalLanguage)
                .rental_duration(rentalDuration)
                .rental_rate(rentalRate)
                .length(length)
                .rating(rating)
                .specialFeatures(specialFeature)
                .build();
        Actor actor = new Actor();

        return film;
    }
}
