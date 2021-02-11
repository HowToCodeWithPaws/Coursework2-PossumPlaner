package com.example.attemptatautentification

import com.example.attemptatautentification.auth.AuthManager;

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var login_: String = ""
    var password_: String = ""
    var accounts = mutableMapOf("12345" to "12345")
    var authManager : AuthManager = AuthManager()

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

    //что это Наташа
    fun getAnswer(
            login_: String, password_: String
    ) {
        if (accounts.containsKey(login_) && accounts[login_] == password_) {
            fromFile.text = "Hello, ${login_}! You successfully logged in"
            input.refreshDrawableState()
        } else {
            if (!accounts.containsKey(login_)) {
                accounts.put(login_, password_)
                fromFile.text = "Hello, ${login_}! You successfully signed in"
                input.refreshDrawableState()
            } else {
                fromFile.text = "${login_}, your password is wrong"
                input.refreshDrawableState()
            }
        }
    }

    /* fun Confirm(view: View) {
         login_ = login.text.toString()
         password_ = password.text.toString()

         input.text = "\n${login_}\n${password_}\n"
         input.refreshDrawableState()
                 //TODO разобраться с файлами?????
         //TODO возможно мы хотим отправлять запросы на сервер
         //  file.writeText("блять")
         //   file.appendText("\n" + login_ + "\n" + password_ + "\n")
         //   fromFile.text = "ршшшш"
         getAnswer(login_, password_)
     }
 */
    //Кнопочка зарегаться
    fun LogIn(view: View) {
        //logIn.setBackgroundColor(android.graphics.Color.rgb(103, 58, 183));
        //signIn.setBackgroundColor(android.graphics.Color.rgb(98, 0, 238));
        login_ = login.text.toString()
        password_ = password.text.toString()
        if (authManager.createNewAccount(login_, password_))
        {
            updateUI(authManager.currentUser)
        }
        else{
            updateUI("Не получилось создать аккаунт")
        }
    }

    //Кнопочка войти
    fun SignIn(view: View) {
        //signIn.setBackgroundColor(android.graphics.Color.rgb(103, 58, 183));
        //logIn.setBackgroundColor(android.graphics.Color.rgb(98, 0, 238));
        login_ = login.text.toString()
        password_ = password.text.toString()
        if (authManager.signIn(login_, password_)){
            updateUI(authManager.currentUser)
        }
        else{
            updateUI("Не получилось войти в аккаунт")
        }
    }

    //дефолтно обновляем интерфейс для юзера
    fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            input.text = user.email
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