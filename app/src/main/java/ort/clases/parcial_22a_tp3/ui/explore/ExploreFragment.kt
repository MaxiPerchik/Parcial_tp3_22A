package ort.clases.parcial_22a_tp3.ui.explore

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.adapter.BestFlightsAdaptervDestination
import ort.clases.parcial_22a_tp3.databinding.FragmentExploreBinding
import ort.clases.parcial_22a_tp3.repository.ApiRepository
import ort.clases.parcial_22a_tp3.responses.BestFlightsResponse
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding

    @Inject
    lateinit var apiRepository: ApiRepository

    @Inject
    lateinit var flightsAdaptervDestination: BestFlightsAdaptervDestination

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchFlightsData()
    }

    private fun setupRecyclerView() {
        binding.rvExplore.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = flightsAdaptervDestination
        }
        flightsAdaptervDestination.setOnItemCLickListener {
            val direction = ExploreFragmentDirections.actionNavigationExploreToNavigationDetails(it.departureToken!!)
            findNavController().navigate(direction)
        }
    }

    private fun fetchFlightsData() {
        apiRepository.getBestFlights().enqueue(object : retrofit2.Callback<BestFlightsResponse> {
            override fun onResponse(call: Call<BestFlightsResponse>, response: Response<BestFlightsResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { itBody ->
                        if (itBody.bestFlights?.isNotEmpty() == true) {
                            flightsAdaptervDestination.differ.submitList(itBody.bestFlights)
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BestFlightsResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
