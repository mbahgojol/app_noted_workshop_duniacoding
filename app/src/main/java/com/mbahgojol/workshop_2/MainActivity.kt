package com.mbahgojol.workshop_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mbahgojol.workshop_2.ui.Noted
import com.mbahgojol.workshop_2.ui.theme.Workshop_2Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val listNote = remember {
                mutableStateListOf<Noted>()
            }

            var showDialog by remember {
                mutableStateOf(false)
            }

            var title by remember {
                mutableStateOf("")
            }

            var deskripsi by remember {
                mutableStateOf("")
            }

            if (showDialog) {
                Dialog(onDismissRequest = {
                    showDialog = false
                }) {
                    Card(
                        Modifier.fillMaxWidth().wrapContentHeight(),
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            TextField(
                                value = title,
                                label = {
                                    Text(text = "Title")
                                },
                                placeholder = {
                                    Text(text = "Title")
                                },
                                onValueChange = { v ->
                                    title = v
                                },
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            TextField(
                                value = deskripsi,
                                label = {
                                    Text(text = "Deskripsi")
                                },
                                placeholder = {
                                    Text(text = "Deskripsi")
                                },
                                onValueChange = { v ->
                                    deskripsi = v
                                },
                            )

                            Spacer(modifier = Modifier.height(20.dp))
                            Row(Modifier.fillMaxWidth()) {
                                Button(onClick = {
                                    showDialog = false
                                }) {
                                    Text(text = "Cancel")
                                }

                                Spacer(modifier = Modifier.width(20.dp))
                                Button(onClick = {
                                    showDialog = false
                                    val note = Noted(
                                        title,
                                        deskripsi,
                                    )

                                    listNote.add(note)

                                    title = ""
                                    deskripsi = ""
                                }) {
                                    Text(text = "Save")
                                }
                            }
                        }
                    }
                }
            }

            Workshop_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Scaffold(
                        floatingActionButton = {
                            FloatingActionButton(onClick = {
                                showDialog = true
                            }) {
                                Icon(Icons.Default.Add, contentDescription = null)
                            }
                        },
                    ) {
                        Column(
                            Modifier.padding(it).padding(16.dp),
                        ) {
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                items(listNote.size, key = { it }) { index ->
                                    Card(
                                        Modifier.fillMaxWidth()
                                            .clickable {
                                                showDialog = true

                                                title = listNote[index].title
                                                deskripsi = listNote[index].deskripsi
                                            },
                                    ) {
                                        Row(Modifier.fillMaxWidth()) {
                                            Column(
                                                Modifier.padding(8.dp)
                                                    .weight(1f),
                                            ) {
                                                Text(text = listNote[index].title)
                                                Spacer(modifier = Modifier.height(16.dp))
                                                Text(text = listNote[index].deskripsi)
                                            }
                                            Spacer(modifier = Modifier.width(5.dp))
                                            IconButton(onClick = {
                                                listNote.removeAt(index)
                                            }) {
                                                Icon(
                                                    Icons.Default.Delete,
                                                    contentDescription = null,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
