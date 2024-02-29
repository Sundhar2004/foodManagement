package android.foodmanagement.com

import android.foodmanagement.com.databinding.ActivityLoginBinding
import android.foodmanagement.com.databinding.ActivityMyProfileBinding
import android.foodmanagement.com.model.ClickedItem
import android.foodmanagement.com.model.UserRegister
import android.foodmanagement.com.roomDB.FoodDB
import android.foodmanagement.com.roomDB.FoodDao
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProfileBinding
    private lateinit var foodDao: FoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodDao = FoodDB.getDatabase(application).foodDao()

        val nameTXT = binding.nameEt.text.toString()
        val dobTXT = binding.dobEt.text.toString()
        val addressTXT = binding.addressEt.text.toString()
        val cityTXT = binding.cityEt.text.toString()
        val stateTXT = binding.StateEt.text.toString()
        val pinTXT = binding.pincodeEt.text.toString()

        binding.submitBtn.setOnClickListener {

            if (nameTXT.equals(null)){
                Toast.makeText(this,"Please Enter name", Toast.LENGTH_SHORT).show()
            }else if (dobTXT.equals(null)){
                Toast.makeText(this,"Please Enter DOB", Toast.LENGTH_SHORT).show()
            }else if(addressTXT.equals(null)){

            }else if(cityTXT.equals(null)){

            }else if(stateTXT.equals(null)){

            }else if(pinTXT.equals(null)){

            }else{
                val userdata = UserRegister(name = nameTXT, dob = dobTXT, address = addressTXT, city = cityTXT, state = stateTXT, pincode = pinTXT)
                GlobalScope.launch {
                    foodDao.insertUserData(userdata)

                    Log.e("user", userdata.toString())
                }
                finish()
            }
        }
    }
}