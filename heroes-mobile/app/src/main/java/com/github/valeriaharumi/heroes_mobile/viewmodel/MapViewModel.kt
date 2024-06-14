package com.github.valeriaharumi.heroes_mobile.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapViewModel : ViewModel() {
    private val _latitude = MutableStateFlow(0.0)
    val latitude: StateFlow<Double> = _latitude

    private val _longitude = MutableStateFlow(0.0)
    val longitude: StateFlow<Double> = _longitude

    fun updateLocation(lat: Double, lng: Double) {
        _latitude.value = lat
        _longitude.value = lng
    }
}