package com.example.attemptatautentification

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
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.Subplan
import com.example.attemptatautentification.possumLib.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.StringBuilder
import java.time.LocalDate
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var login_: String = ""
    var password_: String = ""
    var authManager: AuthManager = AuthManager()
    var visitedSecond: Boolean = false

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
        updateUI(authManager.currentUser)
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

        authManager.signIn(login_, password_)
        if (authManager.currentUser?.email.equals(login_)) {
            updateUI(authManager.currentUser)
        } else {
            updateUI("Не получилось войти в аккаунт")
        }
    }

    //дефолтно обновляем интерфейс для юзера
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateUI(user: FirebaseUser?) {
      //  if (user != null) {

                ///TODO USER FROM SERVER
            val category = Category("common")
            val subplan = Subplan("s1")
            val subplan2 = Subplan("s2")
            val slist :ArrayList<Subplan> = arrayListOf<Subplan>()
            slist.add(subplan)
            slist.add(subplan2)
            val new_list: ArrayList<Plan> = arrayListOf<Plan>()
            new_list.add(Plan("time to", false, Category("newting")))
            val new_user: User = User("Dead", "token?", new_list)
            new_user.plans.add(Plan("what", false, category, "test",2, LocalDate.of(2000,2,2), LocalDate.of(2021, 5, 4), slist))
            new_user.plans.add(Plan("test actually!", false, category, "another test",3))


//        if (user != null) {
//            input.text = StringBuilder().append("Вы авторизированы, ваш емаил: ").append(user.email)
//                    .toString()
//        }
            if (!visitedSecond) {
                val randomIntent = Intent(this, BottomNavigationScreen::class.java)
                randomIntent.putExtra("user_with_deadlines", new_user)
                startActivity(randomIntent)
                visitedSecond = true
            }
//        } else {
//            updateUI("Нет пользователя")
//        }
        input.refreshDrawableState()
    }

    //дефолтно обновляем интерфейс для юзера
    fun updateUI(warning: String) {
        input.text = warning
        input.refreshDrawableState()
    }

}
