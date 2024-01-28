package android.foodmanagement.com.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food", primaryKeys = ["foodName"])
data class Food(
    val foodName: String,
    val foodPrice: Double
)


@Entity(tableName = "clicked_item", primaryKeys = ["foodName"])
class ClickedItem (
    val foodName: String,
    val foodPrice: Double,
    val quantity: Int = 0
    )