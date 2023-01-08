package app.game.webappdemo.webplugins

import app.game.webappdemo.MainActivity
import app.game.webappdemo.database.TodoDataClass
import app.game.webappdemo.database.TodoManager
import io.ktor.server.application.*
import io.ktor.server.mustache.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import java.util.*


private val todoManager by lazy { TodoManager() }

fun Application.configureRouting(mainActivity: MainActivity) {
    routing {
        get("/") {
            call.respondRedirect("todos")
        }
        route("todos") {
            get {
                call.respond(
                    MustacheContent(
                        "index.hbs",
                        mapOf("todos" to todoManager.getAllTodos())
                    )
                )
            }
            post("add"){
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val comment = formParameters.getOrFail("comment")
                todoManager.addToDatabase(TodoDataClass(
                    title = title,
                    comment = comment,
                    date = Date()
                ))
                call.respondRedirect("/todos")
            }
        }
    }
}
