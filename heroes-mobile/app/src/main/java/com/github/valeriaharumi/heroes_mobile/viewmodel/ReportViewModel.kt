package com.github.valeriaharumi.heroes_mobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.smartcity.model.Report
import com.example.smartcity.network.ApiService

class ReportViewModel : ViewModel() {
    val categories = mutableStateOf(listOf<String>())

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = ApiService.getCategories()
                categories.value = response
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun submitReport(report: Report) {
        viewModelScope.launch {
            try {
                ApiService.submitReport(report)
                // Handle success
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}