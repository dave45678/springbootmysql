package com.example.springbootmysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.HashSet;
import java.util.Set;


@Controller
public class HomeController {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String index(Model model){
        // First let's create an actor
        Actor actor = new Actor();
        actor.setName("Sandra Bullock");
        actor.setRealname("Sandra Mae Bullowski");

        // movie
        Movie movie = new Movie();
        movie.setTitle("Emoji Movie");
        movie.setYear(2017);
        movie.setDescription("About Emojis...");

        movieRepository.save(movie);

        // add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        // add list of movies to the actor's movie list
        actor.setMovies(movies);

        // save actor to database
        actorRepository.save(actor);

        // Grad all the actors from the database and send them to the template
        model.addAttribute("actors", actorRepository.findAll());
        return "index";



    }
}