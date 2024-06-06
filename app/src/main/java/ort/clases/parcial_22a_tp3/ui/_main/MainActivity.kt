package ort.clases.parcial_22a_tp3.ui._main

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    // Donde aparece el menu hambuirguesa
    private val fragmentsNavigation = setOf(
        R.id.navigation_explore,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        // extiende el layout
//        window?.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
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
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun fragmentBehaviour() {
        setFragmentTitles()
        removeToolBarImageView()
        removeToolBar()
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
                R.id.navigation_details
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
