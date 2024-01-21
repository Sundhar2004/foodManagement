package android.foodmanagement.com

import android.content.Intent
import android.foodmanagement.com.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStudent.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("loginID", "student")
            startActivity(intent)
            finish()
        }

        binding.btnStaff.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("loginID", "staff")
            startActivity(intent)
            finish()
        }

        binding.btnCoordinator.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("loginID", "coordinator")
            startActivity(intent)
            finish()
        }
    }
}