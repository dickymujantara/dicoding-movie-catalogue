package com.sentracreative.dicodingmoviecatalogue.utils

import com.sentracreative.dicodingmoviecatalogue.data.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.TvShowEntity

object DataDummy {
    fun generateMovies() : List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()

        // data-1
        movies.add(
            MovieEntity(
            "mov1",
            "The Shawshank Redemption",
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                "Drama","1994","142 min","R",9.3
            )
        )

        // data-2
        movies.add(
            MovieEntity(
                "mov2",
                "The Godfather",
                "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                "Crime, Drama","1972","175 min","N/A",9.2
            )
        )

        // data-3
        movies.add(
            MovieEntity(
                "mov3",
                "The Dark Knight",
                "The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.",
                "Crime, Drama","1974","202 min","N/A",9.0
            )
        )

        // data-4
        movies.add(
            MovieEntity(
                "mov4",
                "The Dark Knight",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                "Action, Crime, Drama","2008","152 min","PG-13",9.0
            )
        )

        // data-5
        movies.add(
            MovieEntity(
                "mov5",
                "12 Angry Men",
                "A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence.",
                "Crime, Drama","1957","96 min","N/A",9.0
            )
        )

        // data-6
        movies.add(
            MovieEntity(
                "mov6",
                "Schindler's List",
                "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.",
                "Biography, Drama, History","1993","195 min","R",8.9
            )
        )

        // data-7
        movies.add(
            MovieEntity(
                "mov7",
                "The Lord of the Rings: The Return of the King",
                "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
                "Action, Adventure, Drama","2003","201 min","PG-13",8.9
            )
        )

        // data-8
        movies.add(
            MovieEntity(
                "mov8",
                "Pulp Fiction",
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                "Crime, Drama","1994","154 min","17+",8.9
            )
        )

        // data-10
        movies.add(
            MovieEntity(
                "mov01",
                "Fight Club",
                "An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more.",
                "Drama","1966","139 min","R",8.8
            )
        )

        return movies
    }

    fun generateTvShows() : List<TvShowEntity>{
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(TvShowEntity(
            "show1",
            "Planet Earth II",
            "Documentary",
            "David Attenborough returns in this breathtaking documentary showcasing life on Planet Earth.",
            "6 Episodes","2017",9.5
        ))

        tvShows.add(TvShowEntity(
            "show2",
            "Planet Earth",
            "Documentary",
            "Emmy Award-winning, 11 episodes, five years in the making, the most expensive nature documentary series ever commissioned by the BBC, and the first to be filmed in high definition.",
            "11 Episodes","2007",9.4
        ))

        tvShows.add(TvShowEntity(
            "show3",
            "Band of Brothers",
            "Action, Drama, History",
            "The story of Easy Company of the U.S. Army 101st Airborne Division, and their mission in World War II Europe, from Operation Overlord, through V-J Day.",
            "10 Episodes","2001",9.4
        ))

        tvShows.add(TvShowEntity(
            "show4",
            "Breaking Bad",
            "Crime, Drama, Thriller",
            "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
            "62 Episodes","2008-2013",9.4
        ))

        tvShows.add(TvShowEntity(
            "show5",
            "Chernobyl",
            " Drama, History, Thriller",
            "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.",
            "5 Episodes","2019",9.4
        ))

        tvShows.add(TvShowEntity(
            "show6",
            "The Wire",
            " Crime, Drama, Thriller",
            "The Baltimore drug scene, as seen through the eyes of drug dealers and law enforcement.",
            "60 Episodes","2002-2008",9.3
        ))

        tvShows.add(TvShowEntity(
            "show7",
            "Blue Planet II ",
            "Documentary",
            "David Attenborough returns to the world's oceans in this sequel to the acclaimed documentary filming rare and unusual creatures of the deep, as well as documenting the problems our oceans face.",
            "8 Episodes","2017-2018",9.3
        ))

        tvShows.add(TvShowEntity(
            "show8",
            "Our Planet",
            "Documentary",
            "Documentary series focusing on the breadth of the diversity of habitats around the world, from the remote Arctic wilderness and mysterious deep oceans to the vast landscapes of Africa and diverse jungles of South America.",
            "8 Episodes","2019",9.3
        ))

        tvShows.add(TvShowEntity(
            "show9",
            "Cosmos: A Spacetime Odyssey",
            "Documentary",
            "An exploration of our discovery of the laws of nature and coordinates in space and time.",
            "13 Episodes","2014",9.3
        ))

        tvShows.add(TvShowEntity(
            "show10",
            "Cosmos",
            "Documentary",
            "Astronomer Carl Sagan leads us on an engaging guided tour of the various elements and cosmological theories of the universe.",
            "13 Episodes","1980",9.3
        ))

        return tvShows
    }

}