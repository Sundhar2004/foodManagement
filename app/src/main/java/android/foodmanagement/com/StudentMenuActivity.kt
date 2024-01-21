package android.foodmanagement.com

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.foodmanagement.com.adapter.FoodItemAdapter
import android.foodmanagement.com.adapter.StudentFoodAdapter
import android.foodmanagement.com.databinding.ActivityHomeBinding
import android.foodmanagement.com.databinding.ActivityStudentMenuBinding
import android.foodmanagement.com.model.Food
import android.foodmanagement.com.roomDB.FoodDB
import android.foodmanagement.com.roomDB.FoodDao
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentMenuActivity : AppCompatActivity() {

    lateinit var binding: ActivityStudentMenuBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: StudentFoodAdapter
    private lateinit var foodDao: FoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Initialize RecyclerView and its adapter */
        recyclerView = findViewById(R.id.student_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        foodDao = FoodDB.getDatabase(application).foodDao()

        GlobalScope.launch(Dispatchers.IO){
            val item = foodDao.getAllItem()
            productAdapter = StudentFoodAdapter(item)

            /** Selected slots remainder*/
            productAdapter.itemListener(object : StudentFoodAdapter.OnItemClickListener{
                override fun onItemClick(item: Food, newQty: Int) {
                 showAlert(item, newQty)
                }
            })

            recyclerView.adapter = productAdapter

        }
    }

    fun showAlert(selectedItem: Food, qty: Int){
        val price = selectedItem.foodPrice * qty

        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Your Orders")
            .setMessage("Name: ${selectedItem.foodName}\nPrice: ${price}\nQty: $qty")
            .setPositiveButton("Place order") { dialog, _ ->
                dialog.dismiss()
                successAlert()
            }

            .setNegativeButton("No"){ dialog, _ ->
            dialog.dismiss()
            }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun successAlert(){

        /** New Alert dialog*/
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.success_alert, null)
        dialogView.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.show()
    }



    override fun onBackPressed() {
        super.onBackPressed()

        /*val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Alert")
        alertDialog.setMessage("Are you sure, you want to exit?")
        alertDialog.setPositiveButton("Yes") { dialog, which ->
            finish()
        }

        alertDialog.setNegativeButton("No") { dialog, which ->
            dialog.cancel()
        }

        alertDialog.show()*/

    }
}