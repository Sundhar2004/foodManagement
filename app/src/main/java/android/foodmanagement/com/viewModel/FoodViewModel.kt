package android.foodmanagement.com.viewModel

import android.foodmanagement.com.model.Food
import android.foodmanagement.com.roomDB.FoodRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
//import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FoodViewModel(private val repository: FoodRepository): ViewModel() {

    val allFoodItems: LiveData<List<Food>> = liveData {
        emit(repository.getAllFoodItems())
    }

    fun insertFoodItem(user: Food) = viewModelScope.launch {
        repository.insertFoodItems(user)
    }

    fun updateFoodItem(item: Food) = viewModelScope.launch {
        repository.updateFoodItems(item)
    }

    fun deleteFoodItem(item: Food) = viewModelScope.launch {
        repository.deleteFoodItems(item)
    }
}