package com.example.attemptatautentification

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import com.example.attemptatautentification.auth.AuthManager;

import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.attemptatautentification.auth.Validator
import com.example.attemptatautentification.possumLib.*
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONStringer
import java.lang.StringBuilder
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.collections.ArrayList
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    var login_: String = ""
    var password_: String = ""
    var authManager: AuthManager = AuthManager()
    var visitedSecond: Boolean = false
    var new_user: User = User()

//    @RequiresApi(Build.VERSION_CODES.O)
//    val file: File = File(Paths.get("").toAbsolutePath().toString() + "info.txt")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //это подрубается лайаут
        setContentView(R.layout.activity_main)
        //всё видненько
        login.isVisible = true
        password.isVisible = true
        signIn.isVisible = true
        logIn.isVisible = true
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
        visitedSecond = false
        login_ = login.text.toString()
        password_ = password.text.toString()

        if (!Validator.isEmail(login_)) {
            updateUI("Это не емаил!")
        } else if (!Validator.strongEnoughPassword(password_)) {
            updateUI("Пароль слишком слабый!")
        } else {
            authManager.createNewAccount(login_, password_)

            if (authManager.currentUser?.email.equals(login_)) {
                updateUI(authManager.currentUser)
            } else {
                updateUI("Не получилось создать аккаунт")
            }
        }
    }

    //Кнопочка войти
    @RequiresApi(Build.VERSION_CODES.O)
    fun SignIn(view: View) {
        visitedSecond = false
        login_ = login.text.toString()
        password_ = password.text.toString()

        if (!login_.isEmpty() && !password_.isEmpty()) {
            authManager.signIn(login_, password_)
            if (authManager.currentUser?.email.equals(login_)) {
                updateUI(authManager.currentUser)
            } else {
                updateUI("Не получилось войти в аккаунт")
            }
        } else {
            updateUI("Логин и пароль не должны быть пустыми")
        }

    }

    //дефолтно обновляем интерфейс для юзера
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            //       if (new_user.name.equals("new user")) {
            exampleUser()
            //     }
            ///TODO USER FROM SERVER


            if (user != null) {
                input.text = StringBuilder().append("Вы авторизированы, ваш емаил: ").append(user.email)
                        .toString()
            }

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
        try {
            new_user = gson.fromJson(sharedPref.getString("User", ""), User::class.java)
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

    fun save() {
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

    override fun onPause() {
        save()
        super.onPause()
    }
}
