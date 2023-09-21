import kotlin.system.exitProcess

data class Archive(val name: String, val notes: MutableList<Note> = mutableListOf())
data class Note(val title: String, val content: String)
fun main(args: Array<String>) {
    val archiveManager = ArchiveManager()
    archiveManager.displayArchiveMenu()
    archiveManager.openArchiveList()
}
