package com.multimedialab.mfunzi

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.multimedialab.mfunzi.ui.theme.ButtonBlue
import com.multimedialab.mfunzi.ui.theme.DeepRed
import com.multimedialab.mfunzi.ui.theme.MfunziTheme
import kotlinx.coroutines.delay

@Composable
fun GreetingSplash() {

    //Local variables for Login form
    val context = LocalContext.current
    val email = remember { mutableStateOf(TextFieldValue()) }
    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val passwordVisibility = remember { mutableStateOf(true) }
    val enabledProgress  = remember{ mutableStateOf(false) }
    val progress = remember{ mutableStateOf(0.1f) }
    val animatedProgress = animateFloatAsState(targetValue = progress.value )

    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mfunziapp_logo),
                    contentDescription = "Mfunzi Logo",
                    modifier = Modifier
                        .width(120.dp)
                        .clip(CircleShape)
                )
                Text(
                    "Mfunzi Login",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                    ),
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
                Spacer(Modifier.size(16.dp))
                //Email Outline Field
                OutlinedTextField(
                    value = email.value,
                    onValueChange = {
                        if (emailErrorState.value) {
                            emailErrorState.value = false
                        }
                        email.value = it
                    },
                    isError = emailErrorState.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    label = {
                        Text(text = "Enter Email*")
                    },
                )
                if (emailErrorState.value) {
                    Text(text = "Required", color = DeepRed)
                }
                Spacer(Modifier.size(16.dp))

                OutlinedTextField(
                    value = password.value,
                    onValueChange = {
                        if (passwordErrorState.value) {
                            passwordErrorState.value = false
                        }
                        password.value = it
                    },
                    isError = passwordErrorState.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    label = {
                        Text(text = "Enter Password*")
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                painter = painterResource(id = if (passwordVisibility.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                                contentDescription = "visibility",
                                tint = ButtonBlue
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
                )
                if (passwordErrorState.value) {
                    Text(text = "Required", color = DeepRed)
                }
                Spacer(Modifier.size(16.dp))

                Button(
                    onClick = {
                        when {
                            email.value.text.isEmpty() -> {
                                emailErrorState.value = true
                            }
                            password.value.text.isEmpty() -> {
                                passwordErrorState.value = true
                            }
                            else -> {
                                passwordErrorState.value = false
                                emailErrorState.value = false

                                //Show Loader
                                enabledProgress != enabledProgress
                                Toast.makeText(
                                    context,
                                    "Logged in successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    },
                    content = {
                        Text(text = "Login", color = Color.White)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBlue)
                )
                Spacer(Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = {

                    }) {
                        Text(text = "Register ?", color = DeepRed)
                        Spacer(Modifier.size(16.dp))
                        Text(text = "Forgot Password", color = DeepRed)
                    }

                }
                Column{
                    //Launched Effect
                    LaunchedEffect(enabledProgress){

                        while (progress.value < 1 && enabledProgress.value) {
                            progress.value += 0.005f
                            delay(10)
                        }
                        if (progress.value >= 1f) {
                            enabledProgress.value = false
                        }

                    }
                    LinearProgressIndicator(
                        progress = animatedProgress.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp)
                            .height(3.dp),
                        backgroundColor = ButtonBlue,
                        color = DeepRed ,//progress color,

                    )

                }

            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    MfunziTheme {
        //SplashScreen()
        GreetingSplash()
    }
}