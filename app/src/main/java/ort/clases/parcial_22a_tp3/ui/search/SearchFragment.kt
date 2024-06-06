package ort.clases.parcial_22a_tp3.ui.search

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ort.clases.parcial_22a_tp3.R

class SearchFragment : Fragment() {
    lateinit var v: View

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()

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

        return v
    }
}