package com.frank.navigationcomponentexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frank.navigationcomponentexample.adapter.EstudianteAdapter
import com.frank.navigationcomponentexample.model.EstudianteEntity
import com.frank.navigationcomponentexample.model.NavigationComponentExampleApplication
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class SecondFragment : Fragment() {

    private lateinit var rvEstudiantes: RecyclerView
    private lateinit var adapter: EstudianteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        // Inicializa RecyclerView
        rvEstudiantes = view.findViewById(R.id.rvEstudiantes)
        rvEstudiantes.layoutManager = LinearLayoutManager(requireContext())

        // Crea el adapter con lista vacía por ahora
        adapter = EstudianteAdapter(listOf(),
            onEditar = { estudiante ->
                // Aquí puedes mostrar un diálogo de edición
                println("Editar: ${estudiante.nombre}")
            },
            onEliminar = { estudiante ->
                eliminarEstudiante(estudiante)
            }
        )

        rvEstudiantes.adapter = adapter

        // Cargar estudiantes de la base de datos
        cargarEstudiantes()

        return view
    }

    private fun cargarEstudiantes() {
        lifecycleScope.launch {
            // obtenemos el Flow<List<EstudianteEntity>>
            NavigationComponentExampleApplication.database
                .estudianteDao()
                .obtenerTodos()
                .collect { listaDeEstudiantes ->
                    // por cada emisión, actualizamos el adapter con la lista real
                    adapter.updateList(listaDeEstudiantes)
                }
        }
    }

    private fun eliminarEstudiante(estudiante: EstudianteEntity) {
        lifecycleScope.launch {
            NavigationComponentExampleApplication.database
                .estudianteDao()
                .eliminar(estudiante)
            cargarEstudiantes() // Refrescar la lista
        }
    }
}
