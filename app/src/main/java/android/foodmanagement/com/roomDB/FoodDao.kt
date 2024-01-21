package android.foodmanagement.com.roomDB

import android.foodmanagement.com.model.Food
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getAllItem(): List<Food>

    @Insert
    fun insertItem(foodItem: Food)

    @Update
    fun updateItem(foodItem: Food)

    @Delete
    fun deleteItem(foodItem: Food)
}