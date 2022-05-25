package com.example.boletos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtNumeroDeBoleto;
    private TextView lblDatos;

    private EditText txtNombreDeCliente;
    private EditText txtEdad;
    private TextView lblDestino;
    private Spinner spnDestino;
    private TextView lblTipoDeViaje;
    private Spinner spnTipoDeViaje;
    private EditText txtFecha;
    private EditText txtPrecio;
    private Button btnIntroducirDatos;
    private Button btnMostrarDatos;
    private Button btnRegresar;
    private Button btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNumeroDeBoleto=(EditText) findViewById(R.id.txtNumeroDeBoleto);
        lblDatos = (TextView) findViewById(R.id.lblDatos);

        txtNombreDeCliente=(EditText) findViewById(R.id.txtNombreDeCliente);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        lblDestino = (TextView) findViewById(R.id.lblDestino);
        spnDestino=(Spinner) findViewById(R.id.spnDestino);
        lblTipoDeViaje=(TextView) findViewById(R.id.lblTipoDeViaje);
        spnTipoDeViaje=(Spinner) findViewById(R.id.spnTipoDeViaje);
        txtFecha=(EditText) findViewById(R.id.txtFecha);
        txtPrecio=(EditText) findViewById(R.id.txtPrecio);

        btnIntroducirDatos=(Button) findViewById(R.id.btnIntroducirDatos);
        btnMostrarDatos=(Button) findViewById(R.id.btnMostrarDatos);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        btnCerrar = (Button)  findViewById(R.id.btnCerrar);

        final String[] destino = {""};
        final int[] tipoDeViaje = {0};
        final double[] subTotal = new double[1];
        final double[] descuento = new double[1];
        final double[] impuesto = new double[1];
        final double[] total = new double[1];

        Boleto boletos = new Boleto();

        btnIntroducirDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNombreDeCliente.getText().toString().matches("")
                || txtNumeroDeBoleto.getText().toString().matches("")
                || txtEdad.getText().toString().matches("")
                || txtFecha.getText().toString().matches("")
                ||txtPrecio.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this,"Faltan datos",Toast.LENGTH_SHORT).show();
                } else {
                    boletos.setNombreDeCliente(txtNombreDeCliente.getText().toString());

                    String numeroDeBoleto = txtNumeroDeBoleto.getText().toString();
                    boletos.setNumeroDeBoleto(Integer.parseInt(numeroDeBoleto));

                    String edad = txtEdad.getText().toString();

                    boletos.setDestino(destino[0]);
                    boletos.setTipoDeViaje(tipoDeViaje[0]);
                    boletos.setFecha(txtFecha.getText().toString());
                    String precio = txtPrecio.getText().toString();
                    boletos.setPrecio(Double.parseDouble(precio));

                    //Funciones
                    subTotal[0] = boletos.calcularSubTotal();
                    descuento[0] = boletos.sacarDescuento(Integer.parseInt(edad));
                    impuesto[0] = boletos.sacarImpuesto();
                    total[0] = boletos.sacarTotal(descuento[0]);

                    Toast.makeText(MainActivity.this, "Datos ingresados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMostrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtNombreDeCliente.setVisibility(View.GONE);
                txtNumeroDeBoleto.setVisibility(View.GONE);
                txtEdad.setVisibility(View.GONE);
                lblDestino.setVisibility(View.GONE);
                spnDestino.setVisibility(View.GONE);
                lblTipoDeViaje.setVisibility(View.GONE);
                spnTipoDeViaje.setVisibility(View.GONE);
                txtFecha.setVisibility(View.GONE);
                txtPrecio.setVisibility(View.GONE);
                btnIntroducirDatos.setVisibility(View.GONE);
                btnMostrarDatos.setVisibility(View.GONE);
                btnRegresar.setVisibility(View.VISIBLE);

                lblDatos.setText("Nombre del cliente: " +boletos.getNombreDeCliente()
                +"\nNumero de Boleto: " +txtNumeroDeBoleto.getText().toString()
                +"\nEdad: " +txtEdad.getText().toString()
                +"\nDestino: " +boletos.getDestino()
                +"\nTipo de viaje: " +boletos.getTipoDeViaje()
                +"\nFecha: " +boletos.getFecha()
                +"\n\nPrecio: " +boletos.getPrecio()
                + "\n\nSubTotal: " +subTotal[0]
                + "\n\nDescuento: " +descuento[0]
                + "\n\nImpuesto: " +impuesto[0]
                + "\n\nTotal: " +total[0]
                );
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblDatos.setText("");

                txtNombreDeCliente.setText("");
                txtNumeroDeBoleto.setText("");
                txtEdad.setText("");;
                txtFecha.setText("");
                txtPrecio.setText("");

                txtNombreDeCliente.setVisibility(View.VISIBLE);
                txtNumeroDeBoleto.setVisibility(View.VISIBLE);
                txtEdad.setVisibility(View.VISIBLE);
                lblDestino.setVisibility(View.VISIBLE);
                spnDestino.setVisibility(View.VISIBLE);
                lblTipoDeViaje.setVisibility(View.VISIBLE);
                spnTipoDeViaje.setVisibility(View.VISIBLE);
                txtFecha.setVisibility(View.VISIBLE);
                txtPrecio.setVisibility(View.VISIBLE);
                btnIntroducirDatos.setVisibility(View.VISIBLE);
                btnMostrarDatos.setVisibility(View.VISIBLE);
                btnRegresar.setVisibility(View.GONE);
            }
        });

        //En esta sección se programa el spinner de destinos
        ArrayAdapter<String> Adaptador=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.destinos));
        spnDestino.setAdapter(Adaptador);
        spnDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                destino[0] = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }});

        //En esta sección se programa el spinner del tipo de viaje
        ArrayAdapter<String> Adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.tiposDeViajes));
        spnTipoDeViaje.setAdapter(Adapter);
        spnTipoDeViaje.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String kindOfJourney = adapterView.getItemAtPosition(i).toString();
                if (kindOfJourney.matches("1. Sencillo")) {
                    tipoDeViaje[0] = 1;
                } else {
                    tipoDeViaje[0] = 2;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }});

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder confirmar = new AlertDialog.Builder(MainActivity.this);
                confirmar.setTitle("¿Cerrar APP?");
                confirmar.setMessage("Se descartará toda la información ingresada");
                confirmar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                confirmar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                confirmar.show();
            }
        });

        //txtNumeroDeBoleto.setText("1");
    }
}