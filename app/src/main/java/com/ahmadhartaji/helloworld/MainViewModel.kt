package com.ahmadhartaji.helloworld

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val data = MutableLiveData<List<Hewan>>()

    init {
        retrieveDataFromServer()
    }

    private fun retrieveDataFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = HewanApi.service.getHewan()
                data.postValue(result)
            } catch (e: Exception) {
                Log.d("hewan", "Gagal : ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Hewan>> = data

}