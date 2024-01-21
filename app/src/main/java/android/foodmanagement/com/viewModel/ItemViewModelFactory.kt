package android.foodmanagement.com.viewModel

import android.foodmanagement.com.roomDB.FoodRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemViewModelFactory(private val repository: FoodRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            return FoodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}