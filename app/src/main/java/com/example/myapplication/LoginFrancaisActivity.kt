package com.example.myapplication

// LoginFrancaisActivity.kt
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginFrancaisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aplicar el tema guardado
        ThemeUtils.applyTheme(ThemeUtils.loadTheme(this))
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER
        }

        val textView = TextView(this).apply {
            text = "Se connecter"
            textSize = 24f
        }

        val backButton = Button(this).apply {
            text = "Retour"
            setOnClickListener {
                finish()
            }
        }
        val themeButton = Button(this).apply {
            text = "Changer de thème"
            setOnClickListener {
                val currentTheme = ThemeUtils.loadTheme(this@LoginFrancaisActivity)
                val newTheme = if (currentTheme == ThemeUtils.THEME_LIGHT) ThemeUtils.THEME_DARK else ThemeUtils.THEME_LIGHT
                ThemeUtils.saveTheme(this@LoginFrancaisActivity, newTheme)
                ThemeUtils.applyTheme(newTheme)
                recreate() // Recargar la actividad para aplicar el nuevo tema
            }
        }

        linearLayout.addView(textView)
        linearLayout.addView(backButton)
        linearLayout.addView(themeButton)
        setContentView(linearLayout)
    }
}