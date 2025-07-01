package com.example.TSDMHproyectoisaac

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar // Importar Toolbar
import androidx.core.view.GravityCompat

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar // Referencia a la Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        // ¡Configurar la Toolbar como ActionBar!
        toolbar = findViewById(R.id.toolbar) // Obtener referencia a la Toolbar
        setSupportActionBar(toolbar) // Establecerla como la barra de acción de la actividad

        // Configurar el toggle (icono de hamburguesa) para abrir/cerrar el drawer
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar, // ¡Pasar la Toolbar aquí!
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState() // ¡Asegúrate de que esto se llame!

        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BienvenidaFragment())
                .commit()
            // Si quieres que el elemento "Temario" aparezca seleccionado en el Drawer al inicio
            navigationView.setCheckedItem(R.id.nav_temario)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_temario -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, TemarioFragment())
                    .commit()
                supportActionBar?.title = "Temario" // Cambia el título de la Toolbar
            }
            R.id.nav_examen -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ExamenFragment())
                    .commit()
                supportActionBar?.title = "Examen" // Cambia el título de la Toolbar
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}