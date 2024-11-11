package com.example.cuentos

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var videoView: VideoView
    private lateinit var videoThumbnail: ImageView
    private lateinit var playButton: Button
    private lateinit var changeVideoButton: Button
    private lateinit var restartButton: Button
    private lateinit var curtain: View
    private var currentVideo = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        curtain = findViewById(R.id.curtain)
        videoView = findViewById(R.id.videoView)
        videoThumbnail = findViewById(R.id.videoThumbnail)
        playButton = findViewById(R.id.playButton)
        changeVideoButton = findViewById(R.id.changeVideoButton)
        restartButton = findViewById(R.id.restartButton)

        loadVideo(currentVideo)
        playButton.setOnClickListener {
            handleVideoPlayback()
        }
        restartButton.setOnClickListener {
            videoView.seekTo(0)
            handleVideoPlayback()
        }

        changeVideoButton.setOnClickListener {
            slideCurtainDownAndChangeVideo()
        }

    }
    private fun slideCurtainDownAndChangeVideo() {
        curtain.visibility = View.VISIBLE
        val curtainHeight = resources.displayMetrics.heightPixels.toFloat()
        val animator = ObjectAnimator.ofFloat(curtain, "translationY", -curtainHeight, 0f)
        animator.duration = 1000
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                loadVideo(currentVideo)
                val resetAnimator = ObjectAnimator.ofFloat(curtain, "translationY", 0f, -curtainHeight)
                resetAnimator.duration = 1000
                resetAnimator.start()
            }
        })

        animator.start()
    }
    private fun loadVideo(videoNumber: Int) {
        val videoPath = "android.resource://${packageName}/raw/video${videoNumber}"
        currentVideo = (currentVideo % 7) + 1
        videoView.setVideoURI(Uri.parse(videoPath))
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(this, Uri.parse(videoPath))
        val previewFrame: Bitmap? = retriever.getFrameAtTime(20000000)
        if (previewFrame != null) {
            videoThumbnail.setImageBitmap(previewFrame)
        }
        retriever.release()
        videoThumbnail.visibility = View.VISIBLE
        videoView.visibility = View.GONE
    }

    private fun handleVideoPlayback() {
        if (!videoView.isPlaying) {
            videoThumbnail.visibility = View.GONE
            videoView.visibility = View.VISIBLE
            videoView.start()
            playButton.visibility = View.GONE
            restartButton.visibility = View.GONE
            changeVideoButton.visibility = View.GONE
        } else {
            videoView.pause()
            playButton.visibility = View.VISIBLE
            restartButton.visibility = View.VISIBLE
            changeVideoButton.visibility = View.VISIBLE
            playButton.requestFocus()
        }
    }

    override fun onPause() {
        super.onPause()
        if (videoView.isPlaying) {
            videoView.stopPlayback()
        }
    }

    override fun onStop() {
        super.onStop()
        videoView.stopPlayback()
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.stopPlayback()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            if (videoView.isPlaying) {
                videoView.pause()
                playButton.visibility = View.VISIBLE
                restartButton.visibility = View.VISIBLE
                changeVideoButton.visibility = View.VISIBLE
                playButton.requestFocus()
            } else {
                videoView.start()
                playButton.visibility = View.GONE
                restartButton.visibility = View.GONE
                changeVideoButton.visibility = View.GONE
            }
            return true
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            if (videoView.isPlaying) {

                playButton.clearFocus()
                restartButton.clearFocus()
                changeVideoButton.clearFocus()

                val currentPosition = videoView.currentPosition
                val newPosition = if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                    (currentPosition - 10000).coerceAtLeast(0)
                } else {
                    (currentPosition + 10000).coerceAtMost(videoView.duration)
                }

                videoView.seekTo(newPosition)
                return true
            }
        }

        return super.onKeyDown(keyCode, event)
        }
    }