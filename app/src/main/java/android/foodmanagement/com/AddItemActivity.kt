package android.foodmanagement.com

import android.content.Intent
import android.foodmanagement.com.adapter.FoodItemAdapter
import android.foodmanagement.com.databinding.ActivityAddItemBinding
import android.foodmanagement.com.model.Food
import android.foodmanagement.com.roomDB.FoodDB
import android.foodmanagement.com.roomDB.FoodRepository
import android.foodmanagement.com.viewModel.FoodViewModel
import android.foodmanagement.com.viewModel.ItemViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding
    private lateinit var productAdapter: FoodItemAdapter


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productAdapter = FoodItemAdapter(emptyList())

        binding.btnAddFood.setOnClickListener {
            if (binding.foodNameEt.text.toString() == ""){
                Toast.makeText(this,"Please add Food Name", Toast.LENGTH_SHORT).show()
            } else if (binding.foodPriceEt.text.toString() == ""){
                Toast.makeText(this,"Please add Food Price", Toast.LENGTH_SHORT).show()
            }else {
                val food = binding.foodNameEt.text.toString()
                val foodPrice = binding.foodPriceEt.text.toString()

                GlobalScope.launch(Dispatchers.IO) {
                    val foodItem = Food(food, foodPrice.toDouble())
                    FoodDB.getDatabase(this@AddItemActivity).foodDao().insertItem(foodItem)
                    Log.e("foodDB", foodItem.toString())

                    val allData = FoodDB.getDatabase(this@AddItemActivity).foodDao().getAllItem()
                    Log.e("foodDB", allData.toString())

                    withContext(Dispatchers.Main){
                        productAdapter.updateData(allData)
                    }
                }
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this,"Item Added Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}