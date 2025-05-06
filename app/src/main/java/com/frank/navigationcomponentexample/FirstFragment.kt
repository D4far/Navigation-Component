package com.frank.navigationcomponentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.frank.navigationcomponentexample.model.EstudianteEntity
import com.frank.navigationcomponentexample.model.NavigationComponentExampleApplication
import kotlinx.coroutines.launch


class FirstFragment : Fragment() {
    private lateinit var etNombre: EditText
    private lateinit var etCarrera: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnNavigate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout
        val root = inflater.inflate(R.layout.fragment_first, container, false)

        // Inicializamos las vistas
        etNombre = root.findViewById(R.id.etNombre)
        etCarrera = root.findViewById(R.id.etCarrera)
        btnGuardar = root.findViewById(R.id.btnGuardar)
        btnNavigate = root.findViewById(R.id.btnNavigate)

        // Guardar estudiante
        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val carrera = etCarrera.text.toString()
            guardarEstudiante(nombre, carrera)
        }

        // Navegar al segundo fragmento (si quieres activar la navegación)
        btnNavigate.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return root
    }

    private fun guardarEstudiante(nombre: String, carrera: String) {
        lifecycleScope.launch {
            NavigationComponentExampleApplication.database
                .estudianteDao()
                .insertar(EstudianteEntity(nombre = nombre, carrera = carrera))
        }
    }

}
