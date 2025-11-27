package dao;

import entity.*;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmDAO extends AbstractHibernateDao<Film> {
    private SessionFactory sessionFactory;

    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Film createNewFilm(String title, String description,
                              LocalDate releaseDate, String language,
                              String originalLanguage, Byte rentalDuration, BigDecimal rentalRate,
                              Integer length, String rating, Set<SpecialFeature> specialFeature,
                              Set<Actor> actors, Set<String> categoriesName) {

        ActorDAO actorDAO = new ActorDAO(sessionFactory);

        for(Actor actor : actors){
            List<Actor> actorListInBD = actorDAO.findActorByName(actor.getLastName());
            if(actorListInBD.isEmpty()){
                actorDAO.create(actor);
            } else if(actorListInBD.size() > 1 ){
                for(int i = 0; i < actorListInBD.size(); i++){
                   if(actorListInBD.get(i).getLastName().equals(actor.getLastName())){
                       break;
                   } else if(!actorListInBD.get( actorListInBD.size() -1 ).getLastName().equals(actor.getLastName())){
                       actorDAO.create(actor);
                   }
                }
            }
        }

        CategoryDAO categoryDAO = new CategoryDAO(sessionFactory);
        Set<Category> categories = new HashSet<Category>();

        for(String categoryName : categoriesName){
            Category category = categoryDAO.findAByName(categoryName);
            if(category != null){
                Category cat =Category.builder()
                        .name(categoryName)
                        .build();
                categoryDAO.create(cat);
            }
            categories.add(category);
        }


        Film film = Film.builder()
                .title(title)
                .description(description)
                .release_year(releaseDate)
//                .language(language)
//                .originalLanguage(originalLanguage)
                .rental_duration(rentalDuration)
                .rental_rate(rentalRate)
                .length(length)
                .rating(rating)
                .specialFeatures(specialFeature)
                .actors(actors)
                .category(categories)
                .build();

        return film;
    }
}
