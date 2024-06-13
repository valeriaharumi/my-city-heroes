import axios from 'axios';

export const submitReport = async (reportData) => {
    try {
        const response = await axios.post('http://localhost:5087/api/ProblemReport', reportData);
        return response.data;
    } catch (error) {
        console.error('Error submitting report:', error);
        throw error;
    }
};
