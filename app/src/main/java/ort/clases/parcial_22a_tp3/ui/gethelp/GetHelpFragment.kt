package ort.clases.parcial_22a_tp3.ui.gethelp

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ort.clases.parcial_22a_tp3.R

class GetHelpFragment : Fragment() {

    companion object {
        fun newInstance() = GetHelpFragment()
    }

    private val viewModel: GetHelpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_get_help, container, false)
    }
}