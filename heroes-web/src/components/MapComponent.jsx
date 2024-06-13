import React, { useState, useCallback, useEffect } from 'react';
import { GoogleMap, useLoadScript, Marker } from '@react-google-maps/api';

const mapContainerStyle = {
    width: '100%',
    height: '100%'
};

const defaultCenter = {
    lat: -3.745,
    lng: -38.523
};

const MapComponent = ({ onLocationSelect }) => {
    const { isLoaded, loadError } = useLoadScript({
        googleMapsApiKey: process.env.REACT_APP_GOOGLE_MAPS_API_KEY
    });

    const [selected, setSelected] = useState(null);
    const [center, setCenter] = useState(defaultCenter);

    useEffect(() => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    const lat = position.coords.latitude;
                    const lng = position.coords.longitude;
                    setCenter({ lat, lng });
                },
                () => {
                    console.warn("Unable to retrieve your location");
                }
            );
        }
    }, []);

    const onMapClick = useCallback((event) => {
        const lat = event.latLng.lat();
        const lng = event.latLng.lng();
        setSelected({ lat, lng });
        onLocationSelect({ lat, lng });
    }, [onLocationSelect]);

    if (loadError) return <div>Error loading maps</div>;
    if (!isLoaded) return <div>Loading Maps</div>;

    return (
        <GoogleMap
            mapContainerStyle={mapContainerStyle}
            zoom={12}
            center={center}
            onClick={onMapClick}
        >
            {selected && <Marker position={selected} />}
        </GoogleMap>
    );
};

export default MapComponent;
