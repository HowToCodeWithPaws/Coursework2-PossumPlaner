package com.example.attemptatautentification

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.attemptatautentification.managerLib.AuthManager
import com.example.attemptatautentification.possumLib.*
import com.google.firebase.auth.FirebaseUser
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import java.time.LocalDateTime
import kotlin.collections.ArrayList
import com.example.attemptatautentification.managerLib.DatebaseManager
import com.example.attemptatautentification.managerLib.NotificationManager
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import java.util.*


class MainActivity : AppCompatActivity() {
    var login_: String = ""
    var password_: String = ""
    companion object{
        lateinit var authManager: AuthManager
        lateinit var dateManager: DatebaseManager
        lateinit var notifManager: NotificationManager
    }

    lateinit var a: Objects
    var visitedSecond: Boolean = false
    var new_user: User = User()
    var authed: Boolean = false

//    @RequiresApi(Build.VERSION_CODES.O)
//    val file: File = File(Paths.get("").toAbsolutePath().toString() + "info.txt")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //это подрубается лайаут
        setContentView(R.layout.activity_main)
        //всё видненько
        //login.isVisible = true
       // password.isVisible = true
        signIn.isVisible = true
        //logIn.isVisible = true
        authManager = AuthManager(this)
        dateManager = DatebaseManager(this)
        signIn.setOnClickListener(View.OnClickListener { SignIn() })

    }

    //при запуске приложеньки
    @RequiresApi(Build.VERSION_CODES.O)
    public override fun onStart() {
        super.onStart()
        // если ты уже зареган то обнови это в интерфейсе
        //    updateUI(authManager.currentUser)
    }

    //Кнопочка зарегаться
    @RequiresApi(Build.VERSION_CODES.O)
    fun LogIn(view: View) {
        /*visitedSecond = false
        login_ = login.text.toString()
        password_ = password.text.toString()*/
        SignIn(view)

    }


    //Кнопочка войти
    @RequiresApi(Build.VERSION_CODES.O)
    fun SignIn(view: View) {

        authManager.signIn()
        authManager.existingGoogleAccount()?.let { updateUIError(it) }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun SignIn() {

        authManager.signIn()
        authManager.existingGoogleAccount()?.let { updateUIError(it) }



    }

    //дефолтно обновляем интерфейс для юзера
    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            //       if (new_user.name.equals("new user")) {
            exampleUser()
            //     }
            ///TODO USER FROM SERVER


            input.text = StringBuilder().append("Вы авторизированы, ваш email: ").append(user.email)
                    .toString()

            if (!visitedSecond) {
                passed_user = new_user
                val randomIntent = Intent(this, BottomNavigationScreen::class.java)
                startActivity(randomIntent)
                visitedSecond = true
            }
        } else {
            updateUI("Нет пользователя")
        }
        input.refreshDrawableState()
    }

    //дефолтно обновляем интерфейс для юзера
     fun updateUI(warning: String) {
        input.text = warning
        input.refreshDrawableState()
    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun exampleUser() {
        val clist: ArrayList<Category> = arrayListOf()
        clist.add(Category("учебное"))
        val new_list: ArrayList<Plan> = arrayListOf<Plan>()
        new_user = User("Dead", "token?", new_list, clist)
        new_user.plans.add(Plan("первый план", false, clist[0], "", 2, LocalDateTime.of(2021, 5, 4, 12, 12)))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getSaved(view: View) {
        val sharedPref = this.getSharedPreferences("User_saved", MODE_PRIVATE) ?: return
        val gson = GsonBuilder().create()
     //   try {
            new_user = gson.fromJson(sharedPref.getString("User", ""), User::class.java)
            println("входим " + sharedPref.getString("User", ""))
            println("user  " + new_user.toString())
            if (!visitedSecond) {
                passed_user = new_user
                val randomIntent = Intent(this, BottomNavigationScreen::class.java)
                startActivity(randomIntent)
                visitedSecond = true
            }
    //    } catch (e: NullPointerException) {
//            updateUI("Не получилось получить сохраненные данные")
//            println(e.stackTrace)
//            exampleUser()
//            passed_user = new_user
//            val randomIntent = Intent(this, BottomNavigationScreen::class.java)
//            startActivity(randomIntent)
//            visitedSecond = true
     //   }
    }

    private fun save() {
        val sharedPref = this.getSharedPreferences("User_saved", MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val data = new_user
            val gson = GsonBuilder().create()
            putString("User", gson.toJson(data))
            println("выходим " + gson.toJson(data))
            println("user  " + new_user.toString())
            apply()
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            authManager.handleSignInResult(task)
        }
    }

    override fun onPause() {
        save()
        dateManager.upload(new_user)
        super.onPause()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateUIError(account: GoogleSignInAccount) {
        input.text = account.email
        input.refreshDrawableState()

        dateManager.getUser(account.idToken)
        if (!dateManager.okay()){
            val sharedPref = this.getSharedPreferences("User_saved", MODE_PRIVATE) ?: return
            val gson = GsonBuilder().create()
            try {
                new_user = gson.fromJson(sharedPref.getString("User", ""), User::class.java)
                new_user.token = account.idToken.toString();
                println("входим " + sharedPref.getString("User", ""))
                println("user  " + new_user.toString())
                if (!visitedSecond) {
                    passed_user = new_user
                    val randomIntent = Intent(this, BottomNavigationScreen::class.java)
                    startActivity(randomIntent)
                    visitedSecond = true
                }
            } catch (e: NullPointerException) {
                updateUI("Не получилось получить сохраненные данные")
                println(e.stackTrace)
                exampleUser()
                passed_user = new_user
                val randomIntent = Intent(this, BottomNavigationScreen::class.java)
                startActivity(randomIntent)
                visitedSecond = true
            }

        }
        else{
            dateManager.newUser(account.idToken, account.givenName)
            new_user = dateManager.getUser(account.idToken)

            val randomIntent = Intent(this, BottomNavigationScreen::class.java)
            startActivity(randomIntent)
            visitedSecond = true
        }

    }

}



