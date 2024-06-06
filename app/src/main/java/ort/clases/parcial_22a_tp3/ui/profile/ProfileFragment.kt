package ort.clases.parcial_22a_tp3.ui.profile

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.adapter.PaymentDetailsAdapter
import ort.clases.parcial_22a_tp3.domain.models.PaymentDetail
import ort.clases.parcial_22a_tp3.interfaces.OnPaymentDetailClickListener
import androidx.preference.PreferenceManager // IMPORTADO PARA GESTIONAR PREFERENCIAS
import androidx.appcompat.app.AppCompatDelegate // IMPORTADO PARA GESTIONAR MODOS DE TEMA
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment(), OnPaymentDetailClickListener {
    lateinit var v: View

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // CARGAR EL TEMA SEGÚN LA PREFERENCIA GUARDADA
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val isNightModeOn = sharedPref.getBoolean("NIGHT_MODE", false)
        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_profile, container, false)
        val backButton = v.findViewById<ImageView>(R.id.backButton)
        val editButton = v.findViewById<ImageView>(R.id.editButton)

        backButton.setOnClickListener {
            v.findNavController().navigate(R.id.navigation_explore)
        }

        editButton.setOnClickListener {
            Snackbar.make(v, getString(R.string.feature_not_implemented), Snackbar.LENGTH_LONG)
                .show()
        }

        val paymentDetails = listOf(
            PaymentDetail(R.drawable.credit_card, getString(R.string.payment_details)),
            PaymentDetail(R.drawable.person, getString(R.string.referral_code), true),
            PaymentDetail(R.drawable.settings, getString(R.string.settings)),
            PaymentDetail(R.drawable.log_out, getString(R.string.logout))
        )

        val recyclerView = v.findViewById<RecyclerView>(R.id.payment_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PaymentDetailsAdapter(paymentDetails, this)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // extiende el layout
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // REMOVER LAS FLAGS CUANDO EL FRAGMENTO SE DESTRUYE
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onPaymentDetailClick(paymentDetail: PaymentDetail) {
        when (paymentDetail.text) {
            getString(R.string.settings) -> {
                // NAVEGAR A LA CONFIGURACIÓN
                view?.findNavController()?.navigate(R.id.navigation_settings)

                // ACTUALIZAR LA SELECCIÓN DEL ELEMENTO DEL MENÚ
                activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.menu?.findItem(R.id.navigation_settings)?.isChecked = true
                activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.menu?.findItem(R.id.navigation_profiles)?.isChecked = false
            }
            else -> {
                Toast.makeText(
                    context,
                    getString(R.string.feature_not_implemented),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}