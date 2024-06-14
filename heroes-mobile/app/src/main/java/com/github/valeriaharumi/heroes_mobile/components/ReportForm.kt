package com.github.valeriaharumi.heroes_mobile.components

import android.location.LocationRequest
import android.os.Looper
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.valeriaharumi.heroes_mobile.model.Report
import com.github.valeriaharumi.heroes_mobile.viewmodel.MapViewModel
import com.github.valeriaharumi.heroes_mobile.viewmodel.ReportViewModel
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationServices
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ReportForm(reportViewModel: ReportViewModel = viewModel(), mapViewModel: MapViewModel = viewModel()) {
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCategory by remember { mutableStateOf("") }
    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    val categories by reportViewModel.categories

    val locationRequest = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let {
                location = Pair(it.latitude, it.longitude)
                mapViewModel.updateLocation(it.latitude, it.longitude)
            }
        }
    }

    DisposableEffect(Unit) {
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        onDispose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        CategoryDropdown(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        location?.let { loc ->
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(loc.first, loc.second), 10f)
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = { latLng ->
                    location = Pair(latLng.latitude, latLng.longitude)
                    mapViewModel.updateLocation(latLng.latitude, latLng.longitude)
                }
            ) {
                Marker(position = LatLng(loc.first, loc.second))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            reportViewModel.submitReport(Report(description.text, location?.first ?: 0.0, location?.second ?: 0.0, "pending", "", selectedCategory))
        }) {
            Text("Enviar")
        }
    }
}