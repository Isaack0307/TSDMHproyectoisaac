package com.example.TSDMHproyectoisaac

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class ExamenFragment : Fragment() {

    private lateinit var tvNumeroPregunta: TextView
    private lateinit var tvPregunta: TextView
    private lateinit var layoutOpciones: LinearLayout
    private lateinit var btnSiguienteOFinalizar: Button

    private lateinit var listaPreguntas: List<Pregunta>
    private var indicePreguntaActual: Int = 0
    private var respuestasCorrectas: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_examen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvNumeroPregunta = view.findViewById(R.id.text_view_numero_pregunta)
        tvPregunta = view.findViewById(R.id.text_view_pregunta)
        layoutOpciones = view.findViewById(R.id.layout_opciones)
        btnSiguienteOFinalizar = view.findViewById(R.id.btn_siguiente_o_finalizar)

        listaPreguntas = generarPreguntasExamen()
        cargarPregunta()

        btnSiguienteOFinalizar.setOnClickListener {
            verificarRespuesta()
        }
    }

    private fun generarPreguntasExamen(): List<Pregunta> {
        return listOf(
            // Preguntas sobre IDE y Android Studio
            // Preguntas sobre AVD
            Pregunta(
                textoPregunta = "🧪 ¿Qué es un AVD en Android Studio?",
                opciones = listOf(
                    "📉 Una versión antigua de Android",
                    "🧰 Una herramienta para depurar código",
                    "📲 Un dispositivo virtual que simula un teléfono Android",
                    "📁 Un archivo para actualizar la app"
                ),
                respuestaCorrectaIndex = 2
            ),
            Pregunta(
                textoPregunta = "💡 ¿Cuál de las siguientes es una ventaja de usar un AVD?",
                opciones = listOf(
                    "📤 Es obligatorio para publicar apps en Play Store",
                    "🧩 Te permite usar aplicaciones sin instalarlas",
                    "📱 Permite probar apps sin tener un celular físico",
                    "⚙️ Crea archivos APK automáticamente"
                ),
                respuestaCorrectaIndex = 2
            ),
            Pregunta(
                textoPregunta = "🔍 ¿Qué componente NO puedes configurar al crear un AVD?",
                opciones = listOf(
                    "📱 Modelo del dispositivo",
                    "📲 Versión de Android",
                    "🌡️ Sensor de temperatura corporal",
                    "🖼️ Resolución de pantalla"
                ),
                respuestaCorrectaIndex = 2
            ),
            Pregunta(
                textoPregunta = "🛠️ ¿Cuál es uno de los pasos al crear un AVD en Android Studio?",
                opciones = listOf(
                    "📦 Descargar Java manualmente",
                    "☁️ Cargar imágenes desde la nube",
                    "📲 Seleccionar un dispositivo base",
                    "🔐 Iniciar sesión con cuenta de Gmail"
                ),
                respuestaCorrectaIndex = 2
            ),
            Pregunta(
                textoPregunta = "⛔ ¿Cuál es una limitación de los emuladores AVD?",
                opciones = listOf(
                    "🚫 No permiten probar apps",
                    "❌ No simulan ninguna versión de Android",
                    "🔎 Son demasiado precisos con el hardware real",
                    "🐢 A veces son más lentos que un celular real"
                ),
                respuestaCorrectaIndex = 3
            )
        )
    }
    private fun cargarPregunta() {
        if (indicePreguntaActual >= listaPreguntas.size) return

        val preguntaActual = listaPreguntas[indicePreguntaActual]
        tvNumeroPregunta.text = "Pregunta ${indicePreguntaActual + 1}/${listaPreguntas.size}"
        tvPregunta.text = preguntaActual.textoPregunta

        layoutOpciones.removeAllViews()

        for ((index, textoOpcion) in preguntaActual.opciones.withIndex()) {
            val opcionView = if (preguntaActual.esMultiple) {
                CheckBox(requireContext()).apply {
                    text = textoOpcion
                    textSize = 18f
                    id = index
                    setPadding(16, 12, 16, 12)
                }
            } else {
                RadioButton(requireContext()).apply {
                    text = textoOpcion
                    textSize = 18f
                    id = index
                    setPadding(16, 12, 16, 12)
                }
            }
            layoutOpciones.addView(opcionView)
        }

        // Aplicar lógica de selección única manualmente para CheckBox
        if (preguntaActual.esMultiple) {
            for (i in 0 until layoutOpciones.childCount) {
                val view = layoutOpciones.getChildAt(i)
                if (view is CheckBox) {
                    view.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            // Desmarcar todas las demás
                            for (j in 0 until layoutOpciones.childCount) {
                                val otraView = layoutOpciones.getChildAt(j)
                                if (otraView is CheckBox && otraView != view) {
                                    otraView.isChecked = false
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // Lógica equivalente para RadioButton fuera de RadioGroup
            for (i in 0 until layoutOpciones.childCount) {
                val view = layoutOpciones.getChildAt(i)
                if (view is RadioButton) {
                    view.setOnClickListener {
                        for (j in 0 until layoutOpciones.childCount) {
                            val otraView = layoutOpciones.getChildAt(j)
                            if (otraView is RadioButton && otraView != view) {
                                otraView.isChecked = false
                            }
                        }
                    }
                }
            }
        }

        btnSiguienteOFinalizar.text =
            if (indicePreguntaActual == listaPreguntas.size - 1) "Finalizar Examen" else "Siguiente Pregunta"
    }

    private fun verificarRespuesta() {
        val pregunta = listaPreguntas[indicePreguntaActual]
        val seleccionados = mutableListOf<Int>()

        for (i in 0 until layoutOpciones.childCount) {
            val view = layoutOpciones.getChildAt(i)
            if ((view is RadioButton || view is CheckBox) && view.isChecked) {
                seleccionados.add(view.id)
            }
        }

        if (seleccionados.isEmpty()) {
            Toast.makeText(context, "Selecciona una opción.", Toast.LENGTH_SHORT).show()
            return
        }

        if (seleccionados.size > 1) {
            Toast.makeText(context, "Solo puedes seleccionar una opción.", Toast.LENGTH_SHORT).show()
            return
        }

        val seleccion = seleccionados.first()

        if (!pregunta.esMultiple && seleccion == pregunta.respuestaCorrectaIndex) {
            respuestasCorrectas++
        } else if (pregunta.esMultiple) {
            val correctos = pregunta.indicesRespuestasMultiples ?: emptyList()
            if (correctos.contains(seleccion)) {
                respuestasCorrectas++
            }
        }

        indicePreguntaActual++
        if (indicePreguntaActual < listaPreguntas.size) {
            cargarPregunta()
        } else {
            mostrarCalificacion()
        }
    }

    private fun mostrarCalificacion() {
        val totalPreguntas = listaPreguntas.size
        val calificacion = (respuestasCorrectas.toFloat() / totalPreguntas.toFloat()) * 100

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CalificacionFragment.newInstance(calificacion))
            .addToBackStack(null)
            .commit()
    }
}