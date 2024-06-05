package ort.clases.parcial_22a_tp3.ui.offers

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.adapter.OffersAdapter
import ort.clases.parcial_22a_tp3.data.models.Offer

class OffersFragment : Fragment() {

    companion object {
        fun newInstance() = OffersFragment()
    }

    private val viewModel: OffersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_offers, container, false)
        setupRecyclerView(view)
        return view
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_offers)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = OffersAdapter(getOffers())
    }

    private fun getOffers(): List<Offer> {
        // Aquí puedes obtener tus datos reales
        return listOf(
            Offer(
                discountText = "Get 20% discount",
                cardType = "Master",
                description = "Use your mastercard with any transaction and get 20% discount instantly!",
                imageRes = R.drawable.tarjeta_mastercard
            ),
            // Agrega más ofertas aquí
        )
    }
}