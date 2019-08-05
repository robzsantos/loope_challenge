package com.br.loopechallenge.webservice

import com.google.gson.annotations.SerializedName

/**
 * Created by Robson on 2019-08-05.
 */
data class MoviesResponse(
    @SerializedName("objects")
    val movies: List<MovieInfosResponse>
)

data class MovieInfosResponse(

    val name: String?,

    @SerializedName("bg")
    val video: String?,

    @SerializedName("im")
    val image: String?,

    @SerializedName("sg")
    val sound: String?

)