package com.frank.navigationcomponentexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.frank.navigationcomponentexample.model.EstudianteEntity
import com.frank.navigationcomponentexample.model.NavigationComponentExampleApplication
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        crearEstudiante("Juan", "Ingeniería")
    }
    fun crearEstudiante(nombre: String = "", carrera: String = "") {
        lifecycleScope.launch {
            NavigationComponentExampleApplication.database.estudianteDao().insertar(EstudianteEntity(nombre = nombre, carrera = carrera))
        }
    }
}