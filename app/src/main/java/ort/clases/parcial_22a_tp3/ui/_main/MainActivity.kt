package ort.clases.parcial_22a_tp3.ui._main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    // IDs de navegación
    private val fragmentsNavigation = setOf(
        R.id.navigation_explore,
        R.id.navigation_search,
        R.id.navigation_offers,
        R.id.navigation_profiles,
        R.id.navigation_notification,
        R.id.navigation_get_help,
        R.id.navigation_calendar,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa la vista con ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura la Toolbar
        setSupportActionBar(binding.contentMainInclude.customToolbar)

        // Configura el NavController y NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        // Configuración del AppBarConfiguration
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = fragmentsNavigation,
            drawerLayout = binding.drawerLayout
        )

        // Configura la barra de acción con el controlador de navegación
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Configura NavigationView con NavController
        binding.navigationView.setupWithNavController(navController)

        // Configura BottomNavigationView con NavController
        binding.navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
