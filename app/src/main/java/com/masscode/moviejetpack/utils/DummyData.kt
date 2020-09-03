package com.masscode.moviejetpack.utils

import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.data.Movie
import com.masscode.moviejetpack.data.TvShow

object DummyData {

    fun generateMovieList(): List<Movie> {

        val movieList = ArrayList<Movie>()

        movieList.add(
            Movie(
                332562,
                "A Star Is Born",
                "October 3, 2018",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                R.drawable.poster_a_start_is_born,
                "https://youtu.be/nSbzyEJ8X9E"
            )
        )

        movieList.add(
            Movie(
                399579,
                "Alita: Battle Angel",
                "January 31, 2019",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                R.drawable.poster_alita,
                "https://youtu.be/aj8mN_7Apcw"
            )
        )

        movieList.add(
            Movie(
                297802,
                "Aquaman",
                "December 7, 2018",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                R.drawable.poster_aquaman,
                "https://youtu.be/WDkg3h8PCVU"
            )
        )

        movieList.add(
            Movie(
                424694,
                "Bohemian Rhapsody",
                "October 24, 2018",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                R.drawable.poster_bohemian,
                "https://youtu.be/HlRd9Zy25zo"
            )
        )

        movieList.add(
            Movie(
                438650,
                "Cold Pursuit",
                "02/08/2019",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                R.drawable.poster_cold_persuit,
                "https://youtu.be/0phuNQQ_gHI"
            )
        )

        movieList.add(
            Movie(
                480530,
                "Creed II",
                "November 21, 2018",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                R.drawable.poster_creed,
                "https://youtu.be/cPNVNqn4T9I"
            )
        )

        movieList.add(
            Movie(
                338952,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "November 14, 2018",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                R.drawable.poster_crimes,
                "https://youtu.be/5sEaYB4rLFQ"
            )
        )

        movieList.add(
            Movie(
                450465,
                "Glass",
                "January 16, 2019",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                R.drawable.poster_crimes,
                "https://youtu.be/95ghQs5AmNk"
            )
        )

        movieList.add(
            Movie(
                166428,
                "How to Train Your Dragon: The Hidden World",
                "January 3, 2019",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                R.drawable.poster_how_to_train,
                "https://youtu.be/qNGLuCijKZ0"
            )
        )

        movieList.add(
            Movie(
                299536,
                "Avengers: Infinity War",
                "April 25, 2018",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                R.drawable.poster_infinity_war,
                "https://youtu.be/sAOzrChqmd0"
            )
        )

        movieList.add(
            Movie(
                457136,
                "Mary Queen of Scots",
                "December 7, 2018",
                "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
                R.drawable.poster_marry_queen,
                "https://youtu.be/wnqjSgMU36U"
            )
        )

        return movieList

    }

    fun generateTvShowList(): List<TvShow> {

        val tvShowList = ArrayList<TvShow>()

        tvShowList.add(
            TvShow(
                1412,
                "Arrow",
                "October 10, 2012",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                R.drawable.poster_arrow,
                "https://youtu.be/hTv13EjlLNg"
            )
        )

        tvShowList.add(
            TvShow(
                79501,
                "Doom Patrol",
                "February 15, 2019",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                R.drawable.poster_doom_patrol,
                "https://youtu.be/6wtGnnLfTqA"
            )
        )

        tvShowList.add(
            TvShow(
                12609,
                "Dragon Ball",
                "February 26, 1986",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure that would change Goku's life forever. See how Goku met his life long friends Bulma, Yamcha, Krillin, Master Roshi and more. And see his adventures as a boy, all leading up to Dragonball Z and later Dragonball GT.\n",
                R.drawable.poster_dragon_ball,
                "https://youtu.be/y_0APzy4BcU"
            )
        )

        tvShowList.add(
            TvShow(
                46261,
                "Fairy Tail",
                "October 12, 2009",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                R.drawable.poster_fairytail,
                "https://youtu.be/29jsKEZN1ag"
            )
        )

        tvShowList.add(
            TvShow(
                1434,
                "Family Guy",
                "January 31, 1999",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                R.drawable.poster_family_guy,
                "https://youtu.be/t3VtKdoPIYE"
            )
        )

        tvShowList.add(
            TvShow(
                60735,
                "The Flash",
                "October 7, 2014",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                R.drawable.poster_flash,
                "https://youtu.be/Yj0l7iGKh8g"
            )
        )

        tvShowList.add(
            TvShow(
                1399,
                "Game of Thrones",
                "April 17, 2011",
                "leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                R.drawable.poster_god,
                "https://youtu.be/BpJYNVhGf1s"
            )
        )

        tvShowList.add(
            TvShow(
                60708,
                "Gotham",
                "September 22, 2014",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                R.drawable.poster_gotham,
                "https://youtu.be/0d1zpt6k5OI"
            )
        )

        tvShowList.add(
            TvShow(
                1416,
                "Grey's Anatomy",
                "March 27, 2005",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                R.drawable.poster_grey_anatomy,
                "https://youtu.be/q1pcpgREQ5c"
            )
        )

        tvShowList.add(
            TvShow(
                54155,
                "Hanna",
                "March 28, 2019",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                R.drawable.poster_hanna,
                "https://youtu.be/wp6myRLnhAs"
            )
        )

        tvShowList.add(
            TvShow(
                31910,
                "Naruto Shippūden",
                "February 15, 2007",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                R.drawable.poster_naruto_shipudden,
                "https://youtu.be/1WLO0Owi5-A"
            )
        )

        return tvShowList

    }

}