package com.paul.wang.core_logic

import androidx.lifecycle.ViewModel
import com.paul.wang.datarepository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun getMainData(): String = MainRepository().data
}
