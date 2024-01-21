package android.foodmanagement.com

import android.annotation.SuppressLint
import android.content.Intent
import android.foodmanagement.com.databinding.ActivityLoginBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var loginType: String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginType = intent.extras!!.get("loginID")as String
        Log.e("LoginType", loginType.toString())

        if (loginType.equals("student")){
            binding.loginType.text = "Student Login"
        } else if (loginType.equals("staff")){
            binding.loginType.text = "Staff Login"
        } else if (loginType.equals("coordinator")){
            binding.loginType.text = "Coordinator Login"
        }else{

        }


        binding.btnLogin.setOnClickListener {
            try {
                validation()
                if (loginType.equals("coordinator") && binding.usernameEt.text.toString() == "admin@gmail.com" && binding.passwordEt.text.toString() == "12345"){
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else if (loginType.equals("student") && binding.usernameEt.text.toString() == "student@gmail.com" && binding.passwordEt.text.toString() == "12345"){
                    val intent = Intent(this, StudentMenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }else if (loginType.equals("staff") && binding.usernameEt.text.toString() == "staff@gmail.com" && binding.passwordEt.text.toString() == "12345"){
                    val intent = Intent(this, StudentMenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Please Enter valid credentials", Toast.LENGTH_SHORT).show()
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


    private fun validation(){
        if (binding.usernameEt.text.toString().isNullOrEmpty()){
            Toast.makeText(this,"Please Enter username", Toast.LENGTH_SHORT).show()
        }else if(binding.passwordEt.text.toString().isNullOrEmpty()){
            Toast.makeText(this,"Please Enter Password", Toast.LENGTH_SHORT).show()
        }else{

        }
    }
}