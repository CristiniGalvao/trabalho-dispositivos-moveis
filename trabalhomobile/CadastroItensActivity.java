package com.example.trabalhomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CadastroItensActivity extends AppCompatActivity {

    private Button btCadastrarItem, btVoltar;
    private EditText edCodigoItem, edDescricaoItem, edValorUnitarioItem;
    private TextView tvListaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_itens);

        btCadastrarItem = findViewById(R.id.btCadastrarItem);
        btVoltar = findViewById(R.id.btCancelarMenu);
        edCodigoItem = findViewById(R.id.edCodigoItem);
        edDescricaoItem = findViewById(R.id.edDescreverItem);
        edValorUnitarioItem = findViewById(R.id.edValorUnitarioItem);
        tvListaItem = findViewById(R.id.tvListarItem);

        atualizarItem();
        btCadastrarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarItem(edCodigoItem,edDescricaoItem,edValorUnitarioItem);
                atualizarItem();
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(MainActivity.class);
            }
        });

    }
    private void salvarItem(EditText codigoItem, EditText descricao, EditText valorUnitario) {
        if (codigoItem.getText().toString().isEmpty()) {
            codigoItem.setError("Informe o campo corretamente!");
            return;
        }
        if (valorUnitario.getText().toString().isEmpty()) {
            valorUnitario.setError("Informe o campo corretamente!");
            return;
        }
        if (descricao.getText().toString().isEmpty()) {
            descricao.setError("Informe o campo corretamente!");
            return;
        }
        int codigo = Integer.parseInt(codigoItem.getText().toString());
        double valor = Double.parseDouble(valorUnitario.getText().toString());
        Item item = new Item();
        item.setCodigoItem(codigo);
        item.setValorUnitarioItem(valor);
        item.setDescricaoItem(descricao.getText().toString());
        Controller.getInstance().salvarItem(item);
        codigoItem.setText("");
        descricao.setText("");
        valorUnitario.setText("");
        Toast.makeText(this, "Sucesso!\n Item cadastrado com sucesso!", Toast.LENGTH_LONG).show();
    }

    public void atualizarItem(){
        String text = "";
        for(Item itens : Controller.getInstance().retornarItem()){
            text += "Código do item: " + itens.getCodigoItem() + "\nDescrição do item: " + itens.getDescricaoItem() + "\nValor Unitário: " + itens.getValorUnitarioItem() + "\n";
        }
        tvListaItem.setText(text);
    }

    private void abrirActivity(Class<?> activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}