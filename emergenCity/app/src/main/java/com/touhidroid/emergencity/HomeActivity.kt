package com.touhidroid.emergencity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.touhidroid.emergencity.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_home)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener {
            make_call()
        }
    }

    private fun make_call() {
        val permissionCheck =
            ContextCompat.checkSelfPermission(
                this@HomeActivity,
                Manifest.permission.CALL_PHONE
            )

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this@HomeActivity, arrayOf(Manifest.permission.CALL_PHONE),
                1230
            )
        } else {
            val intent = Intent(Intent.ACTION_CALL)

            intent.data = Uri.parse("tel:" + resources.getString(R.string.emergency_call_number))
            this@HomeActivity.startActivity(intent)

            val myHashCall = HashMap<String, String>()
            myHashCall[TextToSpeech.Engine.KEY_PARAM_STREAM] =
                AudioManager.STREAM_VOICE_CALL.toString()
            val t1 = TextToSpeech(this@HomeActivity) {
                /*if (status != TextToSpeech.ERROR) {
                    t1.language = Locale.UK
                }*/
            }
            t1.speak(
                "Hello from an emergency situation!",
                TextToSpeech.QUEUE_FLUSH, myHashCall
            )

            // if (!t1.isSpeaking) {
            val audioManager = this@HomeActivity.getSystemService(AUDIO_SERVICE) as AudioManager
            audioManager.isSpeakerphoneOn = false
            // }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}