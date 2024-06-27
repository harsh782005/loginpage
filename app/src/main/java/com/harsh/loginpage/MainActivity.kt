package com.harsh.loginpage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.harsh.loginpage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null
    var appBarConfiguration: AppBarConfiguration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navController = findNavController(R.id.host)
        appBarConfiguration = navController?.graph?.let {
            AppBarConfiguration(it)
        }
        enableEdgeToEdge()
       // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupActionBarWithNavController(navController!!, appBarConfiguration!!)
        navController?.addOnDestinationChangedListener { navController, destination, arguments ->
            when (destination.id) {
                R.id.firstFragment -> supportActionBar?.title =
                    resources.getString(R.string.login_page)

                R.id.secondFragment -> supportActionBar?.title = resources.getString(R.string.otp_page)
                R.id.thirdFragment -> supportActionBar?.title =
                    resources.getString(R.string.password_page)
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController!!.popBackStack()
    }
}