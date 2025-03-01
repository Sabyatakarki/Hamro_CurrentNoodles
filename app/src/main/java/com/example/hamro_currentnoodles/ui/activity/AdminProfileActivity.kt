import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.databinding.ActivityAdminProfileBinding

class AdminProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Assuming you have a button with id saveButton in your XML layout
        binding.save.setOnClickListener {
            saveUserInfo()
        }
    }

    private fun saveUserInfo() {
        // Your logic to save user info (Firebase, SharedPreferences, etc.)

        // Show toast message
        Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show()
    }
}
