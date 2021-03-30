package com.example.attemptatautentification

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.attemptatautentification.possumLib.User
import com.google.android.material.bottomnavigation.BottomNavigationView

//class ProductDetailsFragment : Fragment() {
//
//    val args by navArgs<ProductDetailsArgs>()
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val productId = args.productId
//    }
//}
class BottomNavigationScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_screen)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        ///TODO PASS INFO TO FRAGMENTS
        var arguments = intent.extras
        val new_user = arguments!!["user_with_deadlines"] as User
        if (savedInstanceState != null) {
            savedInstanceState.putSerializable("user_with_deadlines", new_user)
        }else{
            print("da fuck?")
        }
        print(new_user.name+"\n"+new_user.plans[0].title+"\n"+new_user.plans[1].title)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_list,
                R.id.navigation_calendar,
                R.id.navigation_graph,
                R.id.navigation_settings
            )
        )

//        if (savedInstanceState == null) {
//            val fragment = ProductDetailsFragment()
//
//            // Intent extras and Fragment Args are both of type android.os.Bundle.
//            fragment.arguments = intent.extras
//
//            supportFragmentManager
//                    .beginTransaction()
//                    .add(R.id.main_content, fragment)
//                    .commit()
//        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}