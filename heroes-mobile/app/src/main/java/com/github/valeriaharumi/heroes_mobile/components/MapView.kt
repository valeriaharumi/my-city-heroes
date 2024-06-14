package com.github.valeriaharumi.heroes_mobile.components

import androidx.compose.runtime.Composable
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.LatLng
import androidx.compose.ui.Modifier
import com.github.valeriaharumi.heroes_mobile.viewmodel.MapViewModel

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    onLocationSelect: (LatLng) -> Unit,
    viewModel: MapViewModel
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(viewModel.latitude, viewModel.longitude), 10f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        onMapClick = {
            onLocationSelect(it)
            viewModel.updateLocation(it.latitude, it.longitude)
        }
    ) {
        Marker(position = LatLng(viewModel.latitude, viewModel.longitude))
    }
}