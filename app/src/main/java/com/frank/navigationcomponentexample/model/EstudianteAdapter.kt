package com.frank.navigationcomponentexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frank.navigationcomponentexample.R
import com.frank.navigationcomponentexample.model.EstudianteEntity

class EstudianteAdapter(
    private var estudiantes: List<EstudianteEntity>,
    private val onEditar: (EstudianteEntity) -> Unit,
    private val onEliminar: (EstudianteEntity) -> Unit
) : RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder>() {

    inner class EstudianteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvCarrera: TextView = itemView.findViewById(R.id.tvCarrera)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudianteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_estudiante, parent, false)
        return EstudianteViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstudianteViewHolder, position: Int) {
        val estudiante = estudiantes[position]
        holder.tvNombre.text = estudiante.nombre
        holder.tvCarrera.text = estudiante.carrera

        holder.btnEditar.setOnClickListener {
            onEditar(estudiante)
        }

        holder.btnEliminar.setOnClickListener {
            onEliminar(estudiante)
        }
    }

    override fun getItemCount(): Int = estudiantes.size

    fun updateList(nuevaLista: List<EstudianteEntity>) {
        estudiantes = nuevaLista
        notifyDataSetChanged()
    }
}
