package com.br.loopechallenge.movies.detail

import android.content.Context
import android.content.Intent
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.Surface
import android.view.TextureView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.br.loopechallenge.R
import com.br.loopechallenge.uidata.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.progress_bar_loading.*


/**
 * Created by Robson on 2019-08-06
 */
class MovieDetailsActivity : AppCompatActivity(), TextureView.SurfaceTextureListener {

    private var movie: Movie? = null

    private var videoCurrentPosition: Int = 0

    private var audioCurrentPosition: Int = 0

    private var videoMediaPlayer: MediaPlayer? = null

    private var audioMediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)

        movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie

        constraintlayout_loading_frame.visibility = View.VISIBLE

        videoMediaPlayer = MediaPlayer()

        audioMediaPlayer = MediaPlayer()

        texture_view_movie.surfaceTextureListener = this
    }

    override fun onPause() {
        super.onPause()

        videoMediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                videoCurrentPosition = it.currentPosition
            }
        }
        audioMediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                audioCurrentPosition = it.currentPosition
            }
        }
    }

    override fun onResume() {
        super.onResume()

        videoMediaPlayer?.let {
            if (videoCurrentPosition > 0) {
                it.seekTo(videoCurrentPosition)
            } else {
                it.seekTo(0)
            }
            it.start()
        }
        audioMediaPlayer?.let {
            if (audioCurrentPosition > 0) {
                it.seekTo(audioCurrentPosition)
            } else {
                it.seekTo(0)
            }
            it.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        releasePlayer()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        releasePlayer()
    }

    private fun setupPlayer(surface: Surface) {

        if (videoMediaPlayer?.currentPosition ?: 0 <= 0) {
            audioMediaPlayer?.setDataSource(this, getMedia(movie?.sound ?: ""))
            videoMediaPlayer?.setDataSource(this, getMedia(movie?.video ?: ""))

            audioMediaPlayer?.prepareAsync()
            videoMediaPlayer?.prepareAsync()

            videoMediaPlayer?.setOnPreparedListener {
                constraintlayout_loading_frame.visibility = View.INVISIBLE

                it.isLooping = true

                it.start()

                audioMediaPlayer?.start()
            }
            audioMediaPlayer?.setOnCompletionListener {
                videoMediaPlayer?.stop()
            }
        }

        videoMediaPlayer?.setSurface(surface)
    }

    private fun releasePlayer() {
        videoMediaPlayer?.stop()
        videoMediaPlayer?.release()
        videoMediaPlayer = null

        audioMediaPlayer?.stop()
        audioMediaPlayer?.release()
        audioMediaPlayer = null
    }

    private fun getMedia(mediaPath: String) = Uri.parse(mediaPath)

    override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture?, p1: Int, p2: Int) {}

    override fun onSurfaceTextureUpdated(p0: SurfaceTexture?) {}

    override fun onSurfaceTextureAvailable(p0: SurfaceTexture?, p1: Int, p2: Int) {
        val surface = Surface(p0)

        setupPlayer(surface)
    }

    override fun onSurfaceTextureDestroyed(p0: SurfaceTexture?): Boolean = false

    companion object {

        private const val EXTRA_MOVIE = "extra_movie"

        fun newInstance(context: Context, movie: Movie): Intent =
            Intent(context, MovieDetailsActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                putExtras(Bundle().apply {
                    this.putSerializable(EXTRA_MOVIE, movie)
                })
            }
    }
}