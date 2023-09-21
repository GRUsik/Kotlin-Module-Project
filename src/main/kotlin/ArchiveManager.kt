import kotlin.system.exitProcess

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