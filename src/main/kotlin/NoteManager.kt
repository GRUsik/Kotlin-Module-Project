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