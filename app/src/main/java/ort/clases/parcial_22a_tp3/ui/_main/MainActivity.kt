package ort.clases.parcial_22a_tp3.ui._main

import android.content.Context
import android.content.Intent
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
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.databinding.ActivityMainBinding
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.preference.PreferenceManager // IMPORTADO PARA GESTIONAR PREFERENCIAS
import androidx.appcompat.app.AppCompatDelegate // IMPORTADO PARA GESTIONAR MODOS DE TEMA
import ort.clases.parcial_22a_tp3.ui.splashscreen.SplashActivity


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    // Donde aparece el menu hambuirguesa
    private val fragmentsNavigation = setOf(
        R.id.navigation_explore,
//        R.id.navigation_search,
//        R.id.navigation_offers,
//        R.id.navigation_profiles,
//        R.id.navigation_notification,
//        R.id.navigation_get_help,
//        R.id.navigation_calendar,

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

        // Configura la NavigationView (menu habmurgeuesa) con NavController
        binding.navigationView.setupWithNavController(navController)

        // Configura BottomNavigationView con NavController
        binding.navView.setupWithNavController(navController)


        fragmentBehaviour()


    // CARGAR EL TEMA SEGÚN LA PREFERENCIA GUARDADA
    val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
    val isNightModeOn = sharedPref.getBoolean("NIGHT_MODE", false)
    if (isNightModeOn) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    } else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun fragmentBehaviour() {
        setFragmentTitles()
        removeToolBarImageView()
        removeToolBar()
    }
    
    override fun onPause() {
        super.onPause()
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        sharedPref.edit().putBoolean("NIGHT_MODE", false).apply()
    }

    /**
     * Cambia el icono de la hamburguesa, y te lleva al fragmento principal.
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


    private fun setFragmentTitles() {
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {

                R.id.navigation_search -> {
                    binding.contentMainInclude.toolbarTitle.text =
                        getString(R.string.title_search)
                }
                R.id.navigation_offers -> {

                    binding.contentMainInclude.toolbarTitle.text =
                        getString(R.string.title_offers)
                }
                R.id.navigation_notification -> {
                    binding.contentMainInclude.toolbarTitle.text =
                        getString(R.string.title_notification)
                }
                R.id.navigation_get_help -> {

                    binding.contentMainInclude.toolbarTitle.text =
                        getString(R.string.title_get_help)
                }

                R.id.navigation_calendar,
                -> {
                    binding.contentMainInclude.toolbarTitle.text =
                        getString(R.string.title_calendar)
                }

                else -> {
                    binding.contentMainInclude.customToolbar.title = ""
                    binding.contentMainInclude.toolbarTitle.text = ""
                }
            }
        }
    }

    private fun removeToolBarImageView() {
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {
                R.id.navigation_search,
                R.id.navigation_offers,
                R.id.navigation_notification,
                R.id.navigation_get_help,
                R.id.navigation_calendar
                -> {
                    binding.contentMainInclude.customToolbar.title = ""
                    binding.contentMainInclude.logoImage.visibility = ImageView.GONE
                }
                R.id.navigation_settings -> {
                    showHideElements(
                        toolbar,
                        text = getString(R.string.title_settings),
                        logoImageVisibility = View.GONE,
                        includeProfileBackgroundVisibility = View.GONE
                    )
                    navigateToMainNavFragment(toolbar)
                }
                else -> {
                    binding.contentMainInclude.customToolbar.title = ""
                    binding.contentMainInclude.logoImage.visibility = ImageView.VISIBLE
                }
            }
        }

    }

    private fun removeToolBar() {
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {
                R.id.navigation_profiles,
                -> {
                    binding.contentMainInclude.customToolbar.visibility = MaterialToolbar.GONE
                }

                else -> {
                    binding.contentMainInclude.customToolbar.visibility = MaterialToolbar.VISIBLE
                }
            }
        }

    }

}
