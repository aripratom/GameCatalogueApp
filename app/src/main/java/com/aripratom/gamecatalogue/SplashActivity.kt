package com.aripratom.gamecatalogue

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null

    companion object {
        private val SPLASHDELAY: Long = 1000
    }

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            startActivity<MainActivity>()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mDelayHandler = Handler(mainLooper)

        mDelayHandler?.postDelayed(mRunnable, SPLASHDELAY)
    }

    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler?.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}