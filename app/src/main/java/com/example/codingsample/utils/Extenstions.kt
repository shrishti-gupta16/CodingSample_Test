package com.example.codingsample.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation

/**
 * @method used to navigate from one fragment to other with an animation and bundle data
 * @param target is the id of fragment to be pushed into stack
 * @param bundle is the data container from calling fragment to target
 * @param popUpTo is nullable which is the id of fragment to which pop is required
 * @param isInclusivePop if this is true, popUpTo fragment will be included in pop process
 */
fun Fragment.navigateTo(
    target: Int,
    bundle: Bundle,
    popUpTo: Int? = null,
    isInclusivePop: Boolean = false
) {
    val navOptionBuilder = NavOptions.Builder()

    if (popUpTo != null) {
        navOptionBuilder.setPopUpTo(popUpTo, isInclusivePop)
    }
    val navOptions = navOptionBuilder.build()
    Navigation.findNavController(view?.rootView!!).navigate(
        target,
        bundle,
        navOptions
    )
}