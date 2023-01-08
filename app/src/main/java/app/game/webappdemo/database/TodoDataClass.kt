package app.game.webappdemo.database

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

@Entity
data class TodoDataClass(
    @Id var id: Long = 0,
    var title: String? = null,
    var comment: String? = null,
    var date: Date? = null
)
