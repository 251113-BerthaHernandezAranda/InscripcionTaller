package com.utxicotepec.inscripciontaller

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.utxicotepec.inscripciontaller.ui.componentes.AntDialogInfo
import com.utxicotepec.inscripciontaller.ui.theme.InscripcionTallerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InscripcionTallerTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                var isClean by remember { mutableStateOf(false) }
                var openDialog by remember { mutableStateOf(false) }
                var inscritoFilled: Inscrito? = null

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    MainView(
                        modifier = Modifier.padding(innerPadding),
                        isClean = isClean,
                        onCleaned = { isClean = false },
                        onError = { error ->
                            scope.launch {
                                snackbarHostState.showSnackbar(error)
                            }
                        },
                        onSave = { inscrito ->
                            Log.i("Y_S_V", inscrito.toString())
                            inscritoFilled = inscrito
                            openDialog = true
                        }
                    )

                    if (openDialog) {
                        inscritoFilled?.let { inscrito ->
                            AntDialogInfo(
                                info = inscritoFormatter(inscrito),
                                titleRes = R.string.dialog_title,
                                confirmRes = R.string.dialog_clean,
                                onDismissRequest = { clean ->
                                    openDialog = false
                                    isClean = clean
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
