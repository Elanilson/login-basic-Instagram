package com.apkdoandroid.loginbasicjetpackcompose

import android.app.Activity
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.regex.Pattern

@Composable
fun loginScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)){
        header(Modifier.align(Alignment.TopEnd))
        body(Modifier.align(Alignment.Center))
        foorter(Modifier.align(Alignment.BottomCenter))
    }
}
@Composable
fun foorter(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        singUp()
        Spacer(modifier = Modifier.size(24.dp))
    }
}
@Composable
fun singUp() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Não tem conta ?",
            fontSize = 12.sp,
            color = Color(0xFFB5B5B5))
        Text(text = "inscrever-se",
            Modifier.padding( horizontal = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0XFF4EA8E9))
        
    }
}

@Composable
fun body(modifier: Modifier) {
    var email by rememberSaveable{ mutableStateOf("") }
    var password by rememberSaveable{ mutableStateOf("")}
    var habilitarLogin by rememberSaveable{ mutableStateOf(false)}

    Column(modifier = modifier) {
        imagemLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        email(email, {
            email = it
            habilitarLogin =   enableLogin(email, password)
        })
        Spacer(modifier = Modifier.size(4.dp))
        password(password) {
            password = it
            habilitarLogin =   enableLogin(email, password)
        }
        Spacer(modifier = Modifier.size(8.dp))
        recuperarSenha(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        loginButton(habilitarLogin)
        Spacer(modifier = Modifier.size(16.dp))
        loginDivisor()
        Spacer(modifier = Modifier.size(16.dp))
        socialLogin()

    }

}
@Composable
fun socialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.fb),
            contentDescription = "logo social fb",
            modifier = Modifier.size(16.dp))
        Text(
            text = "Continuar com Elanilson de Jesus",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0XFF4EA8E9),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun loginDivisor() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OU",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
            )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        
    }
}

@Composable
fun loginButton(habilitarLogin: Boolean) {
    Button(onClick = { },
        enabled = habilitarLogin,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color(0xFF4EA8E9),
           // disabledBackgroundColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Entrar")
        
    }

}

@Composable
fun recuperarSenha(modifier: Modifier) {
    Text(
        text = "Recuperar senha?",
    fontSize = 12.sp,
    fontWeight = FontWeight.Bold,
    color =  Color(0xFF4EA8E9),
        modifier = modifier
    )
}

@Composable
fun password(password: String , onTextChange : (String) -> Unit) {
    var passwordVisi by rememberSaveable { mutableStateOf(false)

    }
    TextField(
        value = password,
        onValueChange = {onTextChange(it)},
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        placeholder = { Text(text = "Password")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
                       var imagem = if(passwordVisi){
                           Icons.Filled.VisibilityOff
                       }else{
                           Icons.Filled.Visibility
                       }
            IconButton(onClick = { passwordVisi = !passwordVisi }) {
                Icon(imageVector = imagem , contentDescription = " show password")
                
            }
        },
        visualTransformation = if(passwordVisi){
            VisualTransformation.None
        }else{
            PasswordVisualTransformation()
        }

    )
}


@Composable
fun email(email : String , onTextChange : (String) -> Unit) {
    TextField(value = email,
        onValueChange = {onTextChange(it)} , 
        modifier = Modifier.fillMaxWidth(),
    placeholder = { Text(text = "Telefone, usuário ou E-mail")},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun imagemLogo(modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.insta), contentDescription = "logo" , modifier = modifier)
}

@Composable
fun header(modifier: Modifier){
    var activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "close app",
        modifier = modifier.clickable { activity.finish() })
}

fun enableLogin(email : String , password : String) =  Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

