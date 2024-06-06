package ort.clases.parcial_22a_tp3.ui.search.details

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.databinding.FragmentSearchDetailsBinding
import ort.clases.parcial_22a_tp3.repository.ApiRepository
import ort.clases.parcial_22a_tp3.responses.BestFlightsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class SearchDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSearchDetailsBinding

    private val args: SearchDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var apiRepository: ApiRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.departureToken

        binding.apply {

            apiRepository.getBestFlights().enqueue(object : Callback<BestFlightsResponse> {
                override fun onResponse(
                    call: Call<BestFlightsResponse>,
                    response: Response<BestFlightsResponse>
                ) {
                    when (response.code()) {
                        200 -> {
                            response.body()?.let { bestFlightsResponse ->
                                val matchingFlight = bestFlightsResponse.bestFlights?.find {
                                    it?.departureToken == args.departureToken
                                }
                                matchingFlight?.let { bestFlight ->
                                    name.text = bestFlight.flights?.get(0)?.airline
                                } ?: run {
                                    Toast.makeText(
                                        requireContext(),
                                        "Flight not found",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }

                        404 -> {
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
