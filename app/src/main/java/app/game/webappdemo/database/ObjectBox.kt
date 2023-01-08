package app.game.webappdemo.database

import android.content.Context
import io.objectbox.BoxStore

object ObjectBox {
    private lateinit var boxStore: BoxStore

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }

    fun getBoxStore() = boxStore
}