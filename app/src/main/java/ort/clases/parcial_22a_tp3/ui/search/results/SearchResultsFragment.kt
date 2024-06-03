package ort.clases.parcial_22a_tp3.ui.search.results

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.adapter.BestFlightAdapter
import ort.clases.parcial_22a_tp3.databinding.ActivityMainBinding
import ort.clases.parcial_22a_tp3.databinding.FragmentSearchResultsBinding
import ort.clases.parcial_22a_tp3.domain.models.BestFlight

@AndroidEntryPoint
class SearchResultsFragment : Fragment() {
    private var _binding: FragmentSearchResultsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchResultsViewModel by viewModels()

    private lateinit var bestFlightAdapter: BestFlightAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultsBinding.inflate(inflater, container, false)

        bestFlightAdapter = BestFlightAdapter(mutableListOf(), viewModel)
//        binding.root.setBackgroundColor(resources.getColor(R.color.black))
        viewModel.onCreate()

        binding.bestFlightsRecyclev.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bestFlightAdapter
        }

        viewModel.bestFlightList.observe(viewLifecycleOwner) {
            it?.let {
                bestFlightAdapter.updateList(it)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

//        viewModel.navigateToBFDetails.observe(viewLifecycleOwner) { trip ->
//            trip?.let {
//                println("click")
//
//            }
//        }

        return binding.root
    }
}