package com.example.TSDMHproyectoisaac

import java.io.Serializable

data class Pregunta(
    val textoPregunta: String,
    val opciones: List<String>,
    val respuestaCorrectaIndex: Int = -1, // ✅ Para preguntas de selección única
    val indicesRespuestasMultiples: List<Int>? = null, // ✅ Para selección múltiple
    val esMultiple: Boolean = false // ✅ Indica si la pregunta usa CheckBoxes
) : Serializable