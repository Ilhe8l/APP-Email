package souza.guilherme.email;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = findViewById(R.id.btnEnviar); //Obtém o objeto da classe Botão através de seu id

        //Define a ação que será executada quando houver o click do botão (O método OnClick será chamado)
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etEmail = findViewById(R.id.etEmail); //Obtém o objeto (E-Mail) da classe EditText atrvés de seu id
                String email = etEmail.getText().toString(); //Obtém o texto do objeto etEmail e o converte para um objeto da classe String

                EditText etAssunto = findViewById(R.id.etAssunto); //Obtém o objeto (Assunto) da classe EditText atrvés de seu id
                String assunto = etAssunto.getText().toString(); //Obtém o texto do objeto etAssunto e o converte para um objeto da classe String

                EditText etTexto = findViewById(R.id.etTexto); //Obtém o objeto (Texto) da classe EditText atrvés de seu id
                String texto = etTexto.getText().toString(); //Obtém o texto do objeto etTexto e o converte para um objeto da classe String

                Intent i = new Intent(Intent.ACTION_SENDTO); //Cria-se um Intent indicando a ação que se quer realizar (ACTION_SENDTO)
                i.setData(Uri.parse("mailto:")); // Definimos que estamos interessados somente em apps que resolvem o URI “mailto:” (recebimento de email)

                String[] emails = new String[]{email}; //Corresponde a uma lista de String
                i.putExtra(Intent.EXTRA_EMAIL, emails); //Adiciona o email a lista emails (referente ao email do destinatário)
                i.putExtra(Intent.EXTRA_SUBJECT, assunto); //Referente ao campo de assunto
                i.putExtra(Intent.EXTRA_TEXT, texto); //Referente ao campo de texto

                try{ //Tenta
                    startActivity(Intent.createChooser(i, "Escolha o APP")); //Faz com que apareça apps capazes de realizar a tarefa
                }

                catch (ActivityNotFoundException e){ //Exceção
                    Toast.makeText(MainActivity.this, "Não há nenhum app que possa realizar essa operação", Toast.LENGTH_LONG).show(); //Caso não possua nenhum app de E-Mail instalado, a mensagem de erro será exibida
                }


            }
        });

    }
}