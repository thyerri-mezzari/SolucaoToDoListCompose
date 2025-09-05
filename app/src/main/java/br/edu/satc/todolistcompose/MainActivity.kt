package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.lifecycleScope
import br.edu.satc.todolistcompose.data.AppDatabase
import br.edu.satc.todolistcompose.data.TaskData
import br.edu.satc.todolistcompose.ui.screens.HomeScreen
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var taskList = mutableStateListOf<TaskData>()

        lifecycleScope.launch {
            db = AppDatabase.getInstance(this@MainActivity)
            taskList.addAll(db.taskDao().getAll())
            println("Total de tarefas no banco: ${taskList.size}")
        }

        setContent {
            ToDoListComposeTheme {
                HomeScreen(
                    taskList,
                    onNewTask = {
                        db.taskDao().insertAll(it)
                        taskList.add(it)
                    },
                    onTaskUpdated = {
                        db.taskDao().update(it)
                    }
                )
            }
        }
    }
}

var mockTaskData = mutableStateListOf(
    TaskData(1, "Comprar pão", "Comprar pão na padaria", false),
    TaskData(2, "Estudar Kotlin", "Estudar Kotlin para o curso de Android", true),
    TaskData(3, "Ler um livro", "Ler o livro 'Clean Code'", false),
    TaskData(4, "Fazer exercícios", "Fazer exercícios físicos por 30 minutos", true),
    TaskData(5, "Assistir série", "Assistir a série 'Stranger Things'", false),
    TaskData(6, "Cozinhar", "Cozinhar o jantar para a família", false)
)