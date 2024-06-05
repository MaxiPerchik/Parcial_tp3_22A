package ort.clases.parcial_22a_tp3.ui._main

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.R.id
import ort.clases.parcial_22a_tp3.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatDelegate;

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: MaterialToolbar
    private lateinit var logoImage: ImageView
    private lateinit var includeProfileBackground: View
    private lateinit var toolbarTitle: TextView


    // IDs de navegación
    private val fragmentsNavigation = setOf(
        id.navigation_explore,
        id.navigation_search,
        id.navigation_offers,
        id.navigation_profiles,
        id.navigation_notification,
        id.navigation_get_help,
        id.navigation_calendar,
        id.navigation_settings,
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
            .findFragmentById(id.nav_host_fragment_activity_main) as NavHostFragment
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

        toolbar = findViewById(id.custom_toolbar)
        logoImage = findViewById(id.logoImage)
        includeProfileBackground = findViewById(id.includeProfileBackground)
        toolbarTitle = findViewById(id.toolbarTitle)

        fragmentBehaviour()

        // Set the night mode to follow the system mode (day/night)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * Cambia el icono de la hamburguesa, y te lleva al fragmento principal.
     */
    private fun navigateToMainNavFragment(toolbar: MaterialToolbar) {
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.back_arrow)
        toolbar.setNavigationOnClickListener {
            navController.navigate(id.navigation_explore)
        }
    }


    /**
     * la funcion navigate_to_main_fragment sobreescribe el comportambiento
     * aca lo vuelvo a activar TODO: fix
     */
    private fun activateDrawerLayout(toolbar: MaterialToolbar) {
        toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    /**
     * Muetra u oculta elementos de la tool bar
     */
    private fun showHideElements(
        toolbar: MaterialToolbar,
        toolbarVisible: Int = View.VISIBLE,
        text: String = "",
        logoImageVisibility: Int = View.VISIBLE,
        includeProfileBackgroundVisibility: Int = View.VISIBLE
    ) {
        toolbar.visibility = toolbarVisible
        toolbar.title = ""
        toolbarTitle.text = text
        logoImage.visibility = logoImageVisibility
        includeProfileBackground.visibility = includeProfileBackgroundVisibility
    }

    /**
     * Configura comportamiento según Fragmento
     */
    private fun fragmentBehaviour() {
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {
                id.navigation_explore -> {
                    showHideElements(toolbar)
                    activateDrawerLayout(toolbar)
                }

                id.navigation_search -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_search),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }

                id.navigation_offers -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_offers),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }

                id.navigation_profiles -> {
                    toolbar.visibility = View.GONE
                }

                id.navigation_notification -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_notification),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }

                id.navigation_get_help -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_get_help),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }

                id.navigation_calendar -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_calendar),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }

                id.navigation_settings -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_settings),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }

                else -> {
                    showHideElements(toolbar)
                }
            }
        }
    }
}
