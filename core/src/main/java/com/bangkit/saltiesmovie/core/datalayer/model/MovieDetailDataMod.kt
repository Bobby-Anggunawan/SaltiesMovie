package com.bangkit.saltiesmovie.core.datalayer.model

data class MovieDetailDataMod(
    val adult: Boolean = false,
    val backdrop_path: String = "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
    val belongs_to_collection: Any? = null, //todo gak tahu tipe datanya apa
    val budget: Int = 0,
    val genres: ArrayList<Genre> = arrayListOf(),
    val homepage: String = "https://movies.disney.com/cruella",
    val id: Int = 337404,
    val imdb_id: String = 	"tt3228774",
    val original_language: String = "en",
    val original_title: String = "Cruella",
    val overview: String = "null",
    val popularity: Double = 1.6,
    val poster_path: String = "/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
    val production_companies: ArrayList<Companies> = arrayListOf(),
    val production_countries: ArrayList<Countries> = arrayListOf(),
    val release_date: String = "2021-05-26",
    val revenue: Int = 0,
    val runtime: Int = 134,
    val spoken_languages: ArrayList<Languages> = arrayListOf(),
    val status: String = "Released",
    val tagline: String = "Hello Cruel World",
    val title: String = "Cruella",
    val video: Boolean = false,
    val vote_average: Double = 8.8,
    val vote_count: Int = 123
){
    data class Genre(
        val id: Int = 35,
        val name: String = "Comedy"
    )

    data class Companies(
        val id: Int = 2,
        val logo_path: String = "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
        val name: String = 	"Walt Disney Pictures",
        val origin_country: String = "US"
    )

    data class Countries(
        val iso_3166_1: String = "US",
        val name: String = "United States of America"
    )

    data class Languages(
        val english_name: String = "English",
        val iso_639_1: String = "en",
        val name: String = "English"
    )
}