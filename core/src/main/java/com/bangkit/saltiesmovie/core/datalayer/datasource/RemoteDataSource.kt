package com.bangkit.saltiesmovie.core.datalayer.datasource

import com.bangkit.saltiesmovie.core.datalayer.model.MoviePageDataMod
import okhttp3.OkHttpClient
import okhttp3.Request

class RemoteDataSource(val client: OkHttpClient) {
    fun getMovieDetail(id: Int): String{
        val myUrl = "https://api.themoviedb.org/3/movie/$id?api_key=57edc4527407374afc9edbc89cc508e6&language=en-US"
        val request = Request.Builder().url(myUrl)
        client.newCall(request.build()).execute()
            .use { response -> return response.body!!.string()  }
    }

    fun getDiscoverPage(page: Int): String{
        val myUrl = "https://api.themoviedb.org/3/discover/movie?api_key=57edc4527407374afc9edbc89cc508e6&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=$page&with_watch_monetization_types=flatrate"
        val request = Request.Builder().url(myUrl)
        client.newCall(request.build()).execute()
            .use { response -> return response.body!!.string()  }
    }
}