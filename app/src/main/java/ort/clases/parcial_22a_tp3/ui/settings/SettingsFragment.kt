package ort.clases.parcial_22a_tp3.ui.settings

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.databinding.FragmentSettingsBinding
import android.view.animation.AnimationUtils


class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private val viewModel: SettingsViewModel by viewModels()

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val isNightModeOn = sharedPref.getBoolean("NIGHT_MODE", false)
        binding.switchDarkMode.isChecked = isNightModeOn

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Aplica la animación de desvanecimiento antes del cambio de tema
            val fadeOut = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)
            val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)

            view.startAnimation(fadeOut)
            fadeOut.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation) {}
                override fun onAnimationRepeat(animation: android.view.animation.Animation) {}

                override fun onAnimationEnd(animation: android.view.animation.Animation) {
                    val mode = if (isChecked) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_NO
                    }
                    AppCompatDelegate.setDefaultNightMode(mode)

                    sharedPref.edit().putBoolean("NIGHT_MODE", isChecked).apply()

                    // Aplica la animación de desvanecimiento después del cambio de tema
                    view.startAnimation(fadeIn)
                }
            })
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}