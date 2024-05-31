package ort.clases.parcial_22a_tp3.ui._main

import android.os.Bundle
import android.view.View
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
import ort.clases.parcial_22a_tp3.databinding.ActivityMainBinding

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

        toolbar = findViewById(R.id.custom_toolbar)
        logoImage = findViewById(R.id.logoImage)
        includeProfileBackground = findViewById(R.id.includeProfileBackground)
        toolbarTitle = findViewById(R.id.toolbarTitle)

        fragmentBehaviour()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * Cambia el icono de la habnurguesa, y te lleva al fragmento principal.
     */
    private fun navigateToMainNavFragment(toolbar: MaterialToolbar) {
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.back_arrow)
        toolbar.setNavigationOnClickListener {
            navController.navigate(R.id.navigation_explore)
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
                R.id.navigation_explore -> {
                    showHideElements(toolbar)
                    activateDrawerLayout(toolbar)
                }

                R.id.navigation_search -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_search),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }

                R.id.navigation_offers -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_offers),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }

                R.id.navigation_profiles -> {
                    toolbar.visibility = View.GONE
                }

                R.id.navigation_notification -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_notification),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }

                R.id.navigation_get_help -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_get_help),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }

                R.id.navigation_calendar -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_calendar),
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
