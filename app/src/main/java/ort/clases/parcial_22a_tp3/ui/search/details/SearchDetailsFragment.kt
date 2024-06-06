package ort.clases.parcial_22a_tp3.ui.search.details

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.databinding.FragmentSearchDetailsBinding
import ort.clases.parcial_22a_tp3.domain.models.BestFlight

@AndroidEntryPoint
class SearchDetailsFragment : Fragment() {
    private var _binding: FragmentSearchDetailsBinding? = null
    private val binding get() = _binding!!

    private val searchDetailsViewModel: SearchDetailsViewModel by viewModels()
    private var bestFlight: BestFlight? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        arguments?.let {
            val bestFlightArg = SearchDetailsFragmentArgs.fromBundle(it).BestFlight
            bestFlight = bestFlightArg
        }

        binding.favHeart.setOnClickListener {
            searchDetailsViewModel.favBestFlight(bestFlight!!)
        }

        bestFlight?.let {
            binding.name.text = it.flights.last().arrivalAirport.name
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
