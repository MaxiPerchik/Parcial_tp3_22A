package ort.clases.parcial_22a_tp3.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import ort.clases.parcial_22a_tp3.ui._main.MainActivity
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.ui.theme.FlightBlue

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // cambia el color la status bar
        window.statusBarColor = FlightBlue.toInt()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 ms = 3 segundos
    }
}