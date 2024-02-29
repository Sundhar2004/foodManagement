package android.foodmanagement.com

import android.foodmanagement.com.databinding.ActivityLoginBinding
import android.foodmanagement.com.databinding.ActivityMyProfileBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {
            finish()
        }

    }
}