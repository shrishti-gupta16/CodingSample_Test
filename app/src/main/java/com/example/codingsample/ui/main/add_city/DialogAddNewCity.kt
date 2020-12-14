package com.example.codingsample.ui.main.add_city

import android.app.Dialog
import android.content.Context
import android.text.InputFilter
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.codingsample.R
import com.example.codingsample.data.LocationDetail
import com.example.codingsample.databinding.DialogAddNewCityBinding
import com.example.codingsample.utils.Utils

class DialogAddNewCity(
    mContext: Context,
    val onAddClicked: (location: LocationDetail) -> Unit
) : Dialog(mContext, R.style.BottomPickerDialog) {
    private var mBinding = DialogAddNewCityBinding.inflate(LayoutInflater.from(mContext))

    init {
        setButtonListeners()
        setContentView(mBinding.root)
        window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setGravity(Gravity.BOTTOM)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
        }

    }

    private fun setButtonListeners() {
        mBinding.btnSave.setOnClickListener {
            if (isDataValid()){
                val latitude = Utils.formatDoubleValue(mBinding.etLatitude.text.toString())
                val longitude = Utils.formatDoubleValue(mBinding.etLongitude.text.toString())
                val cityName = mBinding.etCityName.text?.toString() ?: ""
                val address = mBinding.etAddress.text?.toString() ?: ""
                val isDefault = if (mBinding.cbDefault.isChecked)  1 else 0
                onAddClicked(LocationDetail(latitude, longitude, cityName, address, isDefault))
                dismiss()
            }
        }
    }

    private fun isDataValid(): Boolean {
        var isValid = true
        if (mBinding.etCityName.text.isNullOrEmpty()) {
            mBinding.tilCityName.error = "Please Enter City Name"
            isValid = false
        }
        if (mBinding.etAddress.text.isNullOrEmpty()) {
            mBinding.tilAddress.error = "Please Enter Address"
            isValid = false
        }
        if (mBinding.etLatitude.text.isNullOrEmpty()) {
            mBinding.tilLatitude.error = "Please Enter Latitude"
            isValid = false
        }
        if (mBinding.etLongitude.text.isNullOrEmpty()) {
            mBinding.tilLongitude.error = "Please Enter Longitude"
            isValid = false
        }
        return isValid
    }

}