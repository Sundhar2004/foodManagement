package android.foodmanagement.com

import android.content.Intent
import android.foodmanagement.com.adapter.FoodItemAdapter
import android.foodmanagement.com.databinding.ActivityHomeBinding
import android.foodmanagement.com.model.Food
import android.foodmanagement.com.roomDB.FoodDB
import android.foodmanagement.com.roomDB.FoodDao
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: FoodItemAdapter
    private lateinit var foodDao: FoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Initialize RecyclerView and its adapter */
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        foodDao = FoodDB.getDatabase(application).foodDao()

        GlobalScope.launch(Dispatchers.IO){
            val items = foodDao.getAllItem()
            productAdapter = FoodItemAdapter(items)

            productAdapter.deletedItem(object : FoodItemAdapter.OnItemClick{
                override fun deleteItem(item: Food) {
                    GlobalScope.launch(Dispatchers.IO){
                        foodDao.deleteItem(item)

                        val updateItem = foodDao.getAllItem()
                        withContext(Dispatchers.Main){
                            productAdapter.updateData(updateItem)
                        }
                    }
                }

            })
            recyclerView.adapter = productAdapter
        }

        binding.btnAddItem.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}