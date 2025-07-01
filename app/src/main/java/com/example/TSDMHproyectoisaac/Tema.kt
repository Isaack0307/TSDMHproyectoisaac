package com.example.TSDMHproyectoisaac

import java.io.Serializable

data class Tema(
    val id: String,
    val titulo: String,
    val contenido: String,
    val esTituloPrincipal: Boolean = false
) : Serializable // <-- ASEGÚRATE DE ESTO