package com.example.TSDMHproyectoisaac

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.Gravity


// La clase TemarioAdapter que extiende RecyclerView.Adapter
// y requiere una lista de Temas y un listener para los clics.
class TemarioAdapter(private val temas: List<Tema>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<TemarioAdapter.TemaViewHolder>() { // Asegúrate de que esta línea esté correcta

    // Interfaz para manejar los clics en los elementos del RecyclerView
    interface OnItemClickListener {
        fun onItemClick(tema: Tema)
    }


    inner class TemaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloTextView: TextView = itemView.findViewById(R.id.text_view_tema_titulo)

        fun bind(tema: Tema) {
            tituloTextView.text = tema.titulo
            tituloTextView.gravity = Gravity.CENTER_HORIZONTAL

            if (tema.esTituloPrincipal) {
                tituloTextView.textSize = 20f
                tituloTextView.setTypeface(null, Typeface.BOLD)
                tituloTextView.setPadding(
                    convertDpToPx(itemView.context, 16),
                    convertDpToPx(itemView.context, 12),
                    convertDpToPx(itemView.context, 16),
                    convertDpToPx(itemView.context, 6)
                )
            } else {
                tituloTextView.textSize = 22f
                tituloTextView.setTypeface(null, Typeface.NORMAL)
                tituloTextView.setPadding(
                    convertDpToPx(itemView.context, 32),
                    convertDpToPx(itemView.context, 4),
                    convertDpToPx(itemView.context, 16),
                    convertDpToPx(itemView.context, 4)
                )
            }

            itemView.setOnClickListener {
                listener.onItemClick(tema)
            }
        }
    } // CIERRE CORRECTO DE TemaViewHolder

    // Métodos obligatorios de RecyclerView.Adapter

    // Crea nuevos ViewHolders (y sus vistas) cuando el RecyclerView necesita uno nuevo
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tema, parent, false)
        return TemaViewHolder(view)
    }

    // Reemplaza el contenido de una vista existente con nuevos datos
    override fun onBindViewHolder(holder: TemaViewHolder, position: Int) {
        holder.bind(temas[position])
    }

    // Devuelve el número total de elementos en la lista de datos
    override fun getItemCount(): Int {
        return temas.size
    }

    // Función auxiliar para convertir dp a píxeles
    private fun convertDpToPx(context: android.content.Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

} // CIERRE CORRECTO DE TemarioAdapter