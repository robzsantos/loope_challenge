package com.br.loopechallenge.uidata

import com.br.loopechallenge.webservice.MovieInfosResponse

/**
 * Created by Robson on 2019-08-05.
 */
class Movie {

    var name: String? = null

    var image: String? = null

    var video: String? = null

    var sound: String? = null

    companion object {

        fun from(response: MovieInfosResponse): Movie =
            Movie().apply {
                this.name = response.name
                this.image = response.image
                this.sound = response.sound
                this.video = response.video
            }
    }
}