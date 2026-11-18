package com.utxicotepec.inscripciontaller

data class Inscrito(
    val nombre: String,
    val apellidos: String,
    val edad: Int,
    val fechaNacimiento: Long,
    val disciplina: String,
    val padecimientos: String
)
