package com.bangkit.saltiesmovie.core.domainlayer.model

data class MoviePageDomainMod(
    val adult: Boolean? = false,
    val backdrop_path: String? = "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
    val genre_ids: ArrayList<Int>? = arrayListOf(),
    val id: Int? = 337404,
    val original_language: String? = "en",
    val original_title: String? = "Cruella",
    val overview: String? = "null",
    val popularity: Double? = 6821.402,
    val poster_path: String? = "/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
    val release_date: String? = "",
    val title: String? = "Cruella",
    val video: Boolean? = false,
    val vote_average: Double? = 8.8,
    val vote_count: Int? = 1560
)