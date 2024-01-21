package android.foodmanagement.com.roomDB

import android.foodmanagement.com.model.Food

class FoodRepository(private val userDao: FoodDao) {
    suspend fun getAllFoodItems(): List<Food> = userDao.getAllItem()
    suspend fun insertFoodItems(food: Food) = userDao.insertItem(food)
    suspend fun updateFoodItems(food: Food) = userDao.updateItem(food)
    suspend fun deleteFoodItems(food: Food) = userDao.deleteItem(food)
}