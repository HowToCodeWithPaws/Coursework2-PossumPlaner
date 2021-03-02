package com.example.attemptatautentification

import android.content.Intent
import com.example.attemptatautentification.auth.AuthManager;

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.attemptatautentification.auth.Validator
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    var login_: String = ""
    var password_: String = ""
    var authManager: AuthManager = AuthManager()
    var visitedSecond: Boolean = true

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
    public override fun onStart() {
        super.onStart()
        // если ты уже зареган то обнови это в интерфейсе
        updateUI(authManager.currentUser)
    }

    //Кнопочка зарегаться
    fun LogIn(view: View) {
        visitedSecond = false
        //logIn.setBackgroundColor(android.graphics.Color.rgb(103, 58, 183));
        //signIn.setBackgroundColor(android.graphics.Color.rgb(98, 0, 238));
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
    fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            //todo Наташа
            input.text = StringBuilder().append("Вы авторизированы, ваш емаил: ").append(user.email)
                .toString()
            if (!visitedSecond) {
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
}
