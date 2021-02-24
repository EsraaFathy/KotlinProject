package com.example.kotlinproject.home

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.model.ModelCurent
import com.example.kotlinproject.dataLayer.online.ApiClient
import com.example.kotlinproject.dataLayer.online.Repository
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class HomeViewModel : ViewModel() {
    private var dateLiveData :MutableLiveData<String> =MutableLiveData<String>()
    private var progress :MutableLiveData<Int> =MutableLiveData<Int>()
    private var timeLiveData :MutableLiveData<String> =MutableLiveData<String>()
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel()
    fun loadOnlineData(lat: String,lon: String,lang: String, appid: String): LiveData<ModelCurent> {
        progress.value=View.INVISIBLE
       return dataSourceViewModel.loadOnlineData(lat,lon,lang,appid)
    }


    @RequiresApi(Build.VERSION_CODES.O)
     fun loadDate(){
        val date= LocalDate.now()
        dateLiveData.value= date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun loadTime(){
        val currentTime = LocalTime.now()
        timeLiveData.value= currentTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
    }

    fun getTime():LiveData<String>{
        return timeLiveData
    }
    fun getDate():LiveData<String>{
        return dateLiveData
    }
    fun getProgress():LiveData<Int>{
        return progress
    }
}