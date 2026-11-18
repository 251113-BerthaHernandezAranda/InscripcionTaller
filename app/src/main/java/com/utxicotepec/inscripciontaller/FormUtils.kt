package com.utxicotepec.inscripciontaller
import android.content.Context
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun convertMillisToDate(millis: Long): String {
    val dateFormatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.SHORT)
        .withLocale(Locale.getDefault())

    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(millis),
        ZoneId.of("UTC")
    ).format(dateFormatter)
}

fun foundErrors(context: Context,
                name: String,
                surname: String,
                height: String): String? {
    return if (name.isBlank() || surname.isBlank() ||
        (height.toIntOrNull() ?: 0) < context.resources.getInteger(R.integer.height_min_value)) {
        context.getString(R.string.form_invalid)
    } else {
        return null
    }
}

fun inscritoFormatter(inscrito: Inscrito): String {
    val result = StringBuilder()
    result.append("Nombre: ${inscrito.nombre} ${inscrito.apellidos}\n")
    result.append("Edad: ${inscrito.edad} años\n")
    result.append("Fecha de nacimiento: ${convertMillisToDate(inscrito.fechaNacimiento)}\n")
    result.append("Disciplina: ${inscrito.disciplina}\n")
    result.append("Padecimientos o alergias: ${inscrito.padecimientos}")
    return result.toString()
}
