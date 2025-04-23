package vcmsa.projects.prjsharedpreferences

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
private lateinit var name : EditText
private lateinit var age : EditText
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        name = findViewById(R.id.txtName)
        age = findViewById(R.id.txtAge)
        
        
        
    }
    // Fetching the stored data in onResume()
    override fun onResume() {
        super.onResume()
        // fetching the data from shared preferences
        val sh = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val s1 = sh.getString("name", "")
        val s2 = sh.getInt("age", 0)
            //setting the fetched data in the EditTexts
        name.setText(s1)
        age.setText(s2)

    }
    //Storing the data in onPause()
    //when the user closes the application onPause() is called
    override fun onPause() {
        super.onPause()
        // creating a shared pref object with a name "MyPrefs" in private mode
        val sh = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val e1 = sh.edit()
        
        //write all the data entered by the user in shared preferences and apply
        e1.putString("name", name.text.toString())
        e1.putInt("age", Integer.parseInt(age.text.toString()))
        e1.apply()
    }
    
}