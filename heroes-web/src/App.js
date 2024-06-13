import React from 'react';
import './styles/App.css';
import ReportForm from './components/ReportForm';

function App() {
  return (
    <div className="App">
      <header>
        <p>City Heroes Report</p>
      </header>
      <ReportForm />
    </div>
  );
}

export default App;
