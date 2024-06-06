package ort.clases.parcial_22a_tp3.ui.search

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ort.clases.parcial_22a_tp3.R
import SearchAdapter
import androidx.recyclerview.widget.RecyclerView
import ort.clases.parcial_22a_tp3.domain.models.SearchOfferItem

class SearchFragment : Fragment() {
    lateinit var v: View

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchOffersAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_search, container, false)

        val btn = v.findViewById<Button>(R.id.go_to_results)

        btn.setOnClickListener {
            v.findNavController()
                .navigate(SearchFragmentDirections.actionNavigationSearchToSearchResultsFragment())
        }
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        setupRecyclerView(view)
        return view

    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.offersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val offers = listOf(
            SearchOfferItem(getString(R.string.search_title_text_mastercard), getString(R.string.search_offer_text), R.drawable.tarjeta_mastercard),
            SearchOfferItem(getString(R.string.search_title_text_visa), getString(R.string.search_offer_text), R.drawable.tarjeta_visa)
        )

        searchOffersAdapter = SearchAdapter(offers)
        recyclerView.adapter = searchOffersAdapter
    }
}