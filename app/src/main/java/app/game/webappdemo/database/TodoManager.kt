package app.game.webappdemo.database

import io.objectbox.Box

class TodoManager {
    private var objectBox: Box<TodoDataClass> =
        ObjectBox.getBoxStore().boxFor(TodoDataClass::class.java)

    fun getAllTodos() = objectBox.all

    fun addToDatabase(todoDataClass: TodoDataClass) = objectBox.put(todoDataClass)

    fun removeFromTodo(todoDataClass: TodoDataClass) = objectBox.remove(todoDataClass)

    fun getTodo(todoDataClass: TodoDataClass) = objectBox.get(todoDataClass.id)

    fun removeAllTodos() = objectBox.removeAll()
}