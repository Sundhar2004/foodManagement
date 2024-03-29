package android.foodmanagement.com.roomDB

import android.foodmanagement.com.model.ClickedItem
import android.foodmanagement.com.model.Food
import android.foodmanagement.com.model.UserRegister
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

    @Insert
    fun insertClickedItem(clickedItem: ClickedItem)

    @Query("SELECT * FROM clicked_item")
    fun getClickedItems(): List<ClickedItem>

    @Insert
    fun insertUserData(userReg: UserRegister)

    @Query("SELECT * FROM userRegister")
    fun getUserData(): List<UserRegister>
}