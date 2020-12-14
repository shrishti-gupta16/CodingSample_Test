// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingsample.R
import com.example.codingsample.databinding.FragmentCityDetailBinding
import com.example.codingsample.utils.ConnectionLiveData
import com.example.codingsample.utils.TextViewFactory
import com.example.codingsample.utils.isConnected
import java.lang.String
import java.util.*


/**
 * A MovieList fragment containing a recyclerview view.
 */
class CityDetailFragment : Fragment() {

    private val mViewModel by viewModels<CityViewModel>()
    private val listAdapter: WeatherForecastAdapter by lazy {
        WeatherForecastAdapter {
            mViewModel.selectedWeatherForecastLiveData.value = it
        }
    }
    private val dayListAdapter: WeatherForecastDayAdapter by lazy { WeatherForecastDayAdapter() }
    private val connectionLiveData by lazy { ConnectionLiveData(requireContext()) }

    private lateinit var mBinding: FragmentCityDetailBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCityDetailBinding.inflate(inflater, container, false).apply {
            viewModel = mViewModel
        }
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.cityId = arguments?.getInt(CITY_ID) ?: mViewModel.cityId
        connectionLiveData.observe(this) {
            mViewModel.isNetworkAvailable.value = it
        }
        mViewModel.isNetworkAvailable.value = context?.isConnected
        mViewModel.loadWeatherForecast()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
    }

    private fun initialize() {
        mBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        mBinding.recyclerView.adapter = listAdapter

        mBinding.dayForecastRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        mBinding.dayForecastRV.adapter = dayListAdapter
    }


    companion object {
        private const val CITY_ID = "city_id"

        fun getBundleExtras(cityId: Int) = bundleOf(CITY_ID to cityId)
    }
}