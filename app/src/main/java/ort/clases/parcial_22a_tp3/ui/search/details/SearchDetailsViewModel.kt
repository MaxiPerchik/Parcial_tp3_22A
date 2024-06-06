package ort.clases.parcial_22a_tp3.ui.search.details

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.domain.GetBestFlightsUseCase
import ort.clases.parcial_22a_tp3.domain.models.BestFlight
import javax.inject.Inject

@HiltViewModel
class SearchDetailsViewModel @Inject constructor(
    private val getbFlightsUseCase: GetBestFlightsUseCase,
) : ViewModel() {

    fun favBestFlight(bestFlight: BestFlight) {
        viewModelScope.launch {
            getbFlightsUseCase.favourite(bestFlight)
        }
    }
}
