package ort.clases.parcial_22a_tp3.ui.search.results

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ort.clases.parcial_22a_tp3.data.FlightRepository
import ort.clases.parcial_22a_tp3.domain.GetBestFlightsUseCase
import ort.clases.parcial_22a_tp3.domain.models.BestFlight
import ort.clases.parcial_22a_tp3.interfaces.OnClickNavigate
import javax.inject.Inject

@HiltViewModel
class SearchResultsViewModel @Inject constructor(
    private val bestFlightsUseCase: GetBestFlightsUseCase

) : ViewModel(), OnClickNavigate {
    val isLoading = MutableLiveData<Boolean>()
    val bestFlightList = MutableLiveData<MutableList<BestFlight>>()
    val navigateToBFDetails = MutableLiveData<BestFlight?>()
    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = bestFlightsUseCase.invoke()
            if (result.isNotEmpty()) {
                bestFlightList.postValue(result.toMutableList())
            }

            isLoading.postValue(false)
        }
    }

    override fun toBestFlight(bestFlight: BestFlight) {
        navigateToBFDetails.value = bestFlight
    }

}