package com.example.technifutur_and17_exo01

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val login = MutableLiveData("")
    val password = MutableLiveData("")

    val foodList = mutableStateListOf<FoodModel>()

    fun loadFakeData() {
        foodList.clear()
        foodList.add(
            FoodModel("italian", "La Pizza", "Liège")
        )
        foodList.add(
            FoodModel("japanese", "La Nouille", "Liège")
        )
        foodList.add(
            FoodModel("libanese", "La Mezze", "Liège")
        )
    }

    fun updateLogin(newLogin: String) {
        login.value = newLogin
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }
}