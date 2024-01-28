package android.foodmanagement.com.roomDB

import android.content.Context
import android.foodmanagement.com.model.ClickedItem
import android.foodmanagement.com.model.Food
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class, ClickedItem::class], version = 1)
abstract class FoodDB: RoomDatabase(){

    abstract fun foodDao(): FoodDao

    companion object{
        @Volatile
        private var INSTANCE: FoodDB? = null

        fun getDatabase(context: Context): FoodDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDB::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
                }
            }
        }
    }
