package android.foodmanagement.com.adminPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.foodmanagement.com.R
import android.foodmanagement.com.adapter.ClickedItemAdapter
import android.foodmanagement.com.adapter.FoodItemAdapter
import android.foodmanagement.com.databinding.ActivityInvoiceBinding
import android.foodmanagement.com.roomDB.FoodDB
import android.foodmanagement.com.roomDB.FoodDao
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InvoiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInvoiceBinding
    private lateinit var foodDao: FoodDao
    private lateinit var clickedItemAdapter: ClickedItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Initialize RecyclerView and its adapter */
        binding.invoiceRecyclerView.layoutManager = LinearLayoutManager(this)
        foodDao = FoodDB.getDatabase(application).foodDao()

        GlobalScope.launch(Dispatchers.IO) {
            clickedItemAdapter = ClickedItemAdapter(foodDao.getClickedItems())
            binding.invoiceRecyclerView.adapter = clickedItemAdapter

        }
    }
}