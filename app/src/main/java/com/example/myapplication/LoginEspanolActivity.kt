package com.example.myapplication
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class LoginEspanolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aplicar el tema guardado
        ThemeUtils.applyTheme(ThemeUtils.loadTheme(this))

        setContentView(R.layout.activity_login_espanol)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val backButton: Button = findViewById(R.id.button_back)
        backButton.setOnClickListener {
            finish()
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
                val newTheme = if (currentTheme == ThemeUtils.THEME_LIGHT) ThemeUtils.THEME_DARK else ThemeUtils.THEME_LIGHT
                ThemeUtils.saveTheme(this, newTheme)
                ThemeUtils.applyTheme(newTheme)
                recreate() // Recargar la actividad para aplicar el nuevo tema
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}