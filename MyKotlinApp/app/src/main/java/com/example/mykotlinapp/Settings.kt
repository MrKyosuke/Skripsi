package com.example.mykotlinapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        // Masih test terkait yg volumenya, nyoba" pakai metode ini
        val volumeSeekBar: SeekBar = findViewById(R.id.volume_seekbar)
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Toast.makeText(this@Settings, "Volume: $progress%", Toast.LENGTH_SHORT).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // BGM Switchnya seperti ini
        val bgmSwitch: Switch = findViewById(R.id.bgm_switch)
        bgmSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "BGM Enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "BGM Disabled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
