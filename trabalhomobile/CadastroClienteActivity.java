package com.example.trabalhomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edNome, edCpf;
    private Button btCadastrar, btCancelar;
    private TextView tvClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edNome = findViewById(R.id.edNome);
        edCpf = findViewById(R.id.edCpf);
        btCadastrar = findViewById(R.id.btCadastrar);
        btCancelar = findViewById(R.id.btVoltar);
        tvClientes = findViewById(R.id.tvClientes);

        atualizarCliente();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente(edNome,edCpf);
                atualizarCliente();
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(MainActivity.class);
            }
        });
    }

    private void salvarCliente(TextView nomeCliente, TextView cpfCliente){
        Cliente cliente = new Cliente();
        if(nomeCliente.getText().toString().isEmpty()){
            nomeCliente.setError("Informe o campo corretamente!");
            return;
        }
        if(cpfCliente.getText().toString().isEmpty()){
            cpfCliente.setError("Informe o campo corretamente!");
            return;
        }
        cliente.setNome(nomeCliente.getText().toString());
        cliente.setCpf(cpfCliente.getText().toString());
        Controller.getInstance().salvarCliente(cliente);
        nomeCliente.setText("");
        cpfCliente.setText("");
        Toast.makeText(this, "\nCliente cadastrado com sucesso!", Toast.LENGTH_LONG).show();
    }

    public void atualizarCliente(){
        String texto = "";
        for(Cliente cliente : Controller.getInstance().retornarCliente()){
            texto += "Nome: " + cliente.getNome() + "\nCPF: " + cliente.getCpf() + "\n";
        }
        tvClientes.setText(texto);
    }

    private void abrirActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}