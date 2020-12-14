// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingsample.R
import com.example.codingsample.data.LocationDetail
import com.example.codingsample.databinding.FragmentLocationListBinding
import com.example.codingsample.ui.main.add_city.DialogAddNewCity


/**
 * A MovieList fragment containing a recyclerview view.
 */
class LocationListFragment : Fragment() {

    private val mViewModel by viewModels<LocationViewModel>()
    private val listAdapter: LocationListAdapter by lazy {
        LocationListAdapter { item, isDefault ->
            onListItemClicked(item, isDefault)
        }
    }

    private lateinit var mViewBinding: FragmentLocationListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = FragmentLocationListBinding.inflate(inflater, container, false).apply {
            viewModel = mViewModel
        }
        mViewBinding.lifecycleOwner = viewLifecycleOwner
        initUI()
        return mViewBinding.root
    }

    private fun initUI() {
        mViewBinding.efabAddManualCity.setOnClickListener {
            DialogAddNewCity(requireContext()) {
                mViewModel.addLocation(locationDetail = it)
                mViewBinding.rvLocation.scrollToPosition(listAdapter.itemCount - 1)
            }.show()
        }
        mViewBinding.efabAddMapCity.setOnClickListener {
            findNavController().navigate(R.id.addCityMapFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
    }


    private fun onListItemClicked(item: LocationDetail, isDelete: Boolean) {
        if (isDelete) {
            mViewModel.deleteLocation(item)
        } else {
            findNavController().navigate(
                R.id.cityDetailFragment,
                CityDetailFragment.getBundleExtras(item.id)
            )
        }
    }

    private fun initialize() {
        mViewBinding.rvLocation.layoutManager = LinearLayoutManager(requireContext())
        mViewBinding.rvLocation.adapter = listAdapter
    }
}