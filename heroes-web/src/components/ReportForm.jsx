import React, { useState, useEffect } from 'react';
import MapComponent from './MapComponent';
import { submitReport } from '../controllers/reportController';
import '../styles/ReportForm.css';
import axios from 'axios';

const ReportForm = () => {
    const [formData, setFormData] = useState({
        description: '',
        latitude: null,
        longitude: null,
        status: 'pending',
        city: '',
        category: ''
    });

    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await axios.get('http://localhost:5087/api/Categories'); // Altere para a URL correta da sua API
                setCategories(response.data);
            } catch (error) {
                console.error('Error fetching categories:', error);
            }
        };

        fetchCategories();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({ ...prevState, [name]: value }));
    };

    const handleLocationSelect = (location) => {
        setFormData(prevState => ({
            ...prevState,
            latitude: location.lat,
            longitude: location.lng
        }));

        fetch(`https://maps.googleapis.com/maps/api/geocode/json?latlng=${location.lat},${location.lng}&key=${process.env.REACT_APP_GOOGLE_MAPS_API_KEY}`)
            .then(response => response.json())
            .then(data => {
                const addressComponents = data.results[0].address_components;
                const cityComponent = addressComponents.find(component => component.types.includes('locality'));
                const city = cityComponent ? cityComponent.long_name : '';
                setFormData(prevState => ({ ...prevState, city }));
            });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await submitReport(formData);
        alert('Report submitted successfully');
    };

    return (
        <form onSubmit={handleSubmit} className='form'>
            <div className='form-text'>
                <div className='form-text-input'>
                    <label>Descrição</label>
                    <textarea type="text" name="description" value={formData.description} onChange={handleChange} required />
                </div>
                <div className='form-text-input'>
                    <label htmlFor="category">Categoria</label>
                    <select name="category" id="category" value={formData.category} onChange={handleChange} required>
                        <option value="">Selecionar</option>
                        {categories.map(category => (
                            <option key={category.id} value={category.id}>{category.name}</option>
                        ))}
                    </select>
                </div>
                <button type="submit">Enviar</button>
            </div>
            <div className='form-map'>
                <label>Localização</label>
                <MapComponent onLocationSelect={handleLocationSelect} />
            </div>
        </form>
    );
};

export default ReportForm;
