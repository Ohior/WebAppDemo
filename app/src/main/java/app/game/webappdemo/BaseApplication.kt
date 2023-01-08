package app.game.webappdemo

import android.app.Application
import app.game.webappdemo.database.ObjectBox

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}