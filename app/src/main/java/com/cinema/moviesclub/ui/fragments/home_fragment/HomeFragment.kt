package com.cinema.moviesclub.ui.fragments.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cinema.moviesclub.R
import com.cinema.moviesclub.databinding.FragmentHomeBinding
import com.cinema.moviesclub.domain.model.Filter
import com.cinema.moviesclub.ui.fragments.home_fragment.adapters.FiltersRVA
import com.cinema.moviesclub.ui.fragments.home_fragment.adapters.MoviesRVA
import com.cinema.moviesclub.ui.fragments.home_fragment.view_model.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val fragmentHomeBinding
        get() = _fragmentHomeBinding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var filters: List<Filter>
    private lateinit var moviesRVA: MoviesRVA
    private lateinit var filtersRVA: FiltersRVA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = fragmentHomeBinding.root

        filters = listOf(Filter("Top Rated", true), Filter("Oscar Awarded"), Filter("Emmy Awarded"))

        setFiltersRVA(view)
        setMoviesRVA("Top Rated", view)

        return view
    }

    private fun setFiltersRVA(view: View) {
        filtersRVA = FiltersRVA(
            filtersList = filters,
            context = view.context,
            topRatedMovies = { homeViewModel.handleRequestGetTopRatedMovies() },
            oscarAwardedMovies = { homeViewModel.handleRequestGetOscarAwardedMovies() },
            emmyAwardedMovies = { homeViewModel.handleRequestGetEmmyAwardedMovies() },
            updateMoviesRVA = { filter -> setMoviesRVA(filter, view) }
        )
        fragmentHomeBinding.filtersRv.setHasFixedSize(true)
        fragmentHomeBinding.filtersRv.adapter = filtersRVA
    }

    private fun setMoviesRVA(filter: String, view: View) {
        when (filter) {
            "Top Rated" -> {
                lifecycleScope.launchWhenCreated {
                    homeViewModel.stateFlow.collectLatest { homeFragmentStateFlow ->

                        if (homeFragmentStateFlow.isLoading) {
                            fragmentHomeBinding.moviesRv.visibility = View.INVISIBLE
                            fragmentHomeBinding.pbBgLayout.visibility = View.VISIBLE
                        } else {
                            fragmentHomeBinding.moviesRv.visibility = View.VISIBLE
                            fragmentHomeBinding.pbBgLayout.visibility = View.INVISIBLE

                            moviesRVA = MoviesRVA(homeFragmentStateFlow.topRatedMovies, view)

                            fragmentHomeBinding.moviesRv.setHasFixedSize(true)
                            fragmentHomeBinding.moviesRv.layoutManager =
                                LinearLayoutManager(context)
                            fragmentHomeBinding.moviesRv.adapter = moviesRVA
                        }
                    }
                }
            }
            "Oscar Awarded" -> {
                lifecycleScope.launchWhenCreated {
                    homeViewModel.stateFlow.collectLatest { homeFragmentStateFlow ->

                        if (homeFragmentStateFlow.isLoading) {
                            fragmentHomeBinding.moviesRv.visibility = View.INVISIBLE
                            fragmentHomeBinding.pbBgLayout.visibility = View.VISIBLE
                        } else {
                            fragmentHomeBinding.moviesRv.visibility = View.VISIBLE
                            fragmentHomeBinding.pbBgLayout.visibility = View.INVISIBLE

                            moviesRVA = MoviesRVA(homeFragmentStateFlow.oscarAwardedMovies, view)

                            fragmentHomeBinding.moviesRv.setHasFixedSize(true)
                            fragmentHomeBinding.moviesRv.layoutManager =
                                LinearLayoutManager(context)
                            fragmentHomeBinding.moviesRv.adapter = moviesRVA
                        }
                    }
                }
            }
            else -> {
                lifecycleScope.launchWhenCreated {
                    homeViewModel.stateFlow.collectLatest { homeFragmentStateFlow ->

                        if (homeFragmentStateFlow.isLoading) {
                            fragmentHomeBinding.moviesRv.visibility = View.INVISIBLE
                            fragmentHomeBinding.pbBgLayout.visibility = View.VISIBLE
                        } else {
                            fragmentHomeBinding.moviesRv.visibility = View.VISIBLE
                            fragmentHomeBinding.pbBgLayout.visibility = View.INVISIBLE

                            moviesRVA = MoviesRVA(homeFragmentStateFlow.emmyAwardedMovies, view)

                            fragmentHomeBinding.moviesRv.setHasFixedSize(true)
                            fragmentHomeBinding.moviesRv.layoutManager =
                                LinearLayoutManager(context)
                            fragmentHomeBinding.moviesRv.adapter = moviesRVA
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentHomeBinding = null
    }
}