package ort.clases.parcial_22a_tp3.ui.search.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ort.clases.parcial_22a_tp3.adapter.BestFlightsAdapterv2
import ort.clases.parcial_22a_tp3.databinding.FragmentSearchResultsBinding
import ort.clases.parcial_22a_tp3.repository.ApiRepository
import ort.clases.parcial_22a_tp3.responses.BestFlightsResponse
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultsFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultsBinding

    @Inject
    lateinit var apiRepository: ApiRepository

    @Inject
    lateinit var flightsAdapterv2: BestFlightsAdapterv2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            progressBar.visibility = View.VISIBLE
            apiRepository.getBestFlights()
                .enqueue(object : retrofit2.Callback<BestFlightsResponse> {
                    override fun onResponse(
                        call: Call<BestFlightsResponse>, response: Response<BestFlightsResponse>
                    ) {
                        progressBar.visibility = View.GONE
                        when (response.code()) {
                            200 -> {
                                response.body().let { itBody ->
                                    if (itBody?.bestFlights!!.isNotEmpty()) {
                                        flightsAdapterv2.differ.submitList(itBody.bestFlights)

                                    }
                                    bestFlightsRecyclev.apply {
                                        layoutManager = LinearLayoutManager(requireContext())
                                        adapter = flightsAdapterv2
                                    }
                                }
                            }

                            400 -> {
                                Toast.makeText(
                                    requireContext(),
                                    "The resource you requested could not be found.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            401 -> {
                                Toast.makeText(
                                    requireContext(),
                                    "Invalid API key: You must be granted a valid key.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }


                    }

                    override fun onFailure(call: Call<BestFlightsResponse>, t: Throwable) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Failure",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }

}