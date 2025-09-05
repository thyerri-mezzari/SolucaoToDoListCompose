package br.edu.satc.todolistcompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTask(taskId: Int): TaskData?

    @Query("SELECT * FROM tasks")
    fun getAll(): List<TaskData>

    @Query("SELECT * FROM tasks WHERE title LIKE :search OR description LIKE :search")
    fun loadBySearch(search: String): List<TaskData>

    @Insert
    fun insertAll(vararg tasks: TaskData)

    @Update
    fun update(task: TaskData)

    @Delete
    fun delete(task: TaskData)
}