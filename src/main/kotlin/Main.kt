import kotlin.system.exitProcess

data class Archive(val name: String, val notes: MutableList<Note> = mutableListOf())
  data class Note(val title: String, val content: String)
class ArchiveManager {
    private val archiveList: MutableList<Archive> = mutableListOf()

    fun displayArchiveMenu() {
        while (true) {
            println("Список архивов:")
            println("0. Создать архив")
            println("1. Это мой уже созданный архив")
            println("2. Выход")

            print("Выберите пункт: ")
            val input = readLine()

            if (input.isNullOrEmpty() || !input.matches(Regex("\\d+"))) {
                println("Пожалуйста, введите цифру.")
                continue
            }

            val choice = input.toInt()

            when (choice) {
                0 -> {

                    createArchive()
                }

                1 -> {
                    openArchiveList()
                }

                2 -> {
                    println("Выход из программы.")
                    exitProcess(0)
                }

                else -> {
                    println("Неверный выбор. Пожалуйста, выберите корректный пункт.")
                }
            }
        }
    }
    fun createArchive() {
        print("Введите название нового архива: ")
        val archiveName = readLine()

        if (archiveName.isNullOrEmpty()) {
            println("Название архива не может быть пустым.")
            return
        }

        val newArchive = Archive(archiveName)
        archiveList.add(newArchive)
        println("Архив '$archiveName' успешно создан и добавлен в список архивов.")
    }

    fun openArchiveList() {
        if (archiveList.isEmpty()) {
            println("У вас нет созданных архивов.")
            return
        }

        println("Список существующих архивов:")
        for (i in archiveList.indices) {
            println("$i. ${archiveList[i].name}")
        }

        print("Введите номер архива для открытия: ")
        val input = readLine()

        if (input.isNullOrEmpty() || !input.matches(Regex("\\d+"))) {
            println("Пожалуйста, введите цифру.")
            return
        }

        val choice = input.toInt()

        if (choice < 0 || choice >= archiveList.size) {
            println("Неверный выбор архива. Пожалуйста, выберите корректный номер.")
            return
        }

        val selectedArchive = archiveList[choice]
        println("Архив '${selectedArchive.name}' успешно открыт.")
        val noteManager = NoteManager()
        noteManager.displayNoteMenu(selectedArchive)
    }
}

class NoteManager {
    fun displayNoteMenu(archive: Archive) {
        while (true) {
            println("Меню заметок в архиве '${archive.name}':")
            println("0. Создать заметку")
            println("1. Это моя уже созданная заметка")
            println("2. Выход")

            print("Выберите пункт: ")
            val input = readLine()

            if (input.isNullOrEmpty() || !input.matches(Regex("\\d+"))) {
                println("Пожалуйста, введите цифру.")
                continue
            }

            val choice = input.toInt()

            when (choice) {
                0 -> {
                    createNoteInsideArchive(archive)
                }

                1 -> {
                    if (archive.notes.isEmpty()) {
                        println("Нет существующих заметок в архиве '${archive.name}'.")
                    } else {
                        println("Список существующих заметок в архиве '${archive.name}':")
                        for ((index, note) in archive.notes.withIndex()) {
                            println("$index. ${note.title}")
                        }
                        println("Введите номер заметки для просмотра или 'b' для возврата в меню:")
                        val noteChoice = readLine()
                        if (noteChoice == "b") {
                            continue
                        }
                        val noteNumber = noteChoice?.toIntOrNull()
                        if (noteNumber != null && noteNumber >= 0 && noteNumber < archive.notes.size) {
                            val selectedNote = archive.notes[noteNumber]
                            println("Содержание заметки '${selectedNote.title}':")
                            println(selectedNote.content)

                            while (true) {
                                println("Меню заметки '${selectedNote.title}':")
                                println("0. Открыть заметку")
                                println("1. Выход")

                                print("Выберите пункт: ")
                                val innerInput = readLine()

                                if (innerInput.isNullOrEmpty() || !innerInput.matches(Regex("\\d+"))) {
                                    println("Пожалуйста, введите цифру.")
                                    continue
                                }

                                val innerChoice = innerInput.toInt()

                                when (innerChoice) {
                                    0 -> {

                                        println(selectedNote.content)
                                    }

                                    1 -> {
                                        println("Выход в меню архива.")
                                        return
                                    }

                                    else -> {
                                        println("Неверный выбор. Пожалуйста, выберите корректный пункт.")
                                    }
                                }
                            }
                        } else {
                            println("Неверный номер заметки. Пожалуйста, выберите корректный номер.")
                        }
                    }
                }

                2 -> {
                    println("Выход в меню архива.")
                    return
                }

                else -> {
                    println("Неверный выбор. Пожалуйста, выберите корректный пункт.")
                }
            }
        }
    }

    fun createNoteInsideArchive(archive: Archive) {
        print("Введите название заметки: ")
        val noteTitle = readLine()

        print("Введите содержимое заметки: ")
        val noteContent = readLine()

        if (noteTitle.isNullOrEmpty() || noteContent.isNullOrEmpty()) {
            println("Название и содержимое заметки не могут быть пустыми. Заметка не создана.")
            return
        }

        val newNote = Note(noteTitle, noteContent)
        archive.notes.add(newNote)
        println("Заметка '${newNote.title}' успешно создана и добавлена в архив '${archive.name}'.")
    }
}

fun main(args: Array<String>) {
    val archiveManager = ArchiveManager()
    archiveManager.displayArchiveMenu()
    archiveManager.openArchiveList()
}
