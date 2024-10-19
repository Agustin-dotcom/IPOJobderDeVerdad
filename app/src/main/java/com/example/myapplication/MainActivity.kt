package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private  lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE)
        if(sharedPreferences.getBoolean("isDarkTheme",false)){
            setTheme(R.style.Theme_MyApplication_Dark)
        }else{
            setTheme(R.style.Theme_MyApplication_Light)
        }
        super.onCreate(savedInstanceState)

        // Aplicar el tema guardado
        //ThemeUtils.applyTheme(ThemeUtils.loadTheme(this))


        setContentView(R.layout.activity_main)

        var themeToggleButton: Button

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val buttonEspanol: Button = findViewById(R.id.button_espanol)
        val buttonEnglish: Button = findViewById(R.id.button_english)
        val buttonFrancais: Button = findViewById(R.id.button_francais)

        buttonEspanol.setOnClickListener {
            val intent = Intent(this, LoginEspanolActivity::class.java)
            startActivity(intent)
        }

        buttonEnglish.setOnClickListener {
            val intent = Intent(this, LoginEnglishActivity::class.java)
            startActivity(intent)
        }

        buttonFrancais.setOnClickListener {
            val intent = Intent(this, LoginFrancaisActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_toggle_theme -> {
                val currentTheme = ThemeUtils.loadTheme(this)
                val newTheme = if (currentTheme == ThemeUtils.THEME_LIGHT) {
                    ThemeUtils.THEME_DARK
                } else {
                    ThemeUtils.THEME_LIGHT
                }
                ThemeUtils.saveTheme(this, newTheme)
                ThemeUtils.applyTheme(newTheme)
                recreate() // Recargar la actividad para aplicar el nuevo tema
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun toggleTheme() {
        val isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false)
        Log.d("MainActivity", "Current theme isDarkTheme: $isDarkTheme")

        val editor = sharedPreferences.edit()
        editor.putBoolean("isDarkTheme", !isDarkTheme)
        editor.apply()

        Log.d("MainActivity", "New theme isDarkTheme: ${!isDarkTheme}")

        recreate()
    }
}