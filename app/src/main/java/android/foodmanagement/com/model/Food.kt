package android.foodmanagement.com.model

import androidx.room.Entity

@Entity(tableName = "food", primaryKeys = ["foodName"])
data class Food(
    val foodName: String,
    val foodPrice: Double
)

