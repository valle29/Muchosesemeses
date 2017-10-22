package com.antonio.muchosesemeses;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.telephony.SmsManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Main extends AppCompatActivity {

    FloatingActionButton btnEnviar;
    MaterialEditText txtSMS,txtTelefono,txtNumSMS;
    LinearLayout btnMas, btnMenos;
    int numSMS=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtSMS = (MaterialEditText) findViewById(R.id.txtSMS);
        txtTelefono = (MaterialEditText) findViewById(R.id.txtTelefono);
        btnMenos = (LinearLayout) findViewById(R.id.btnMenos);
        btnMas = (LinearLayout) findViewById(R.id.btnMas);
        txtNumSMS = (MaterialEditText) findViewById(R.id.txtNumSMS);


        btnMenos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                numSMS = Integer.parseInt(txtNumSMS.getText().toString());
                if(numSMS>0)
                    numSMS--;
                txtNumSMS.setText(Integer.toString(numSMS));
            }
        });

        btnMas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                numSMS = Integer.parseInt(txtNumSMS.getText().toString());
                numSMS++;
                txtNumSMS.setText(Integer.toString(numSMS));
            }
        });

        btnEnviar= (FloatingActionButton) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtSMS.getText().toString().isEmpty())
                {
                    SweetAlertDialog dialog = new SweetAlertDialog(Main.this, SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("Ingrese un SMS").show();
                }
                else
                if(txtTelefono.getText().toString().isEmpty())
                {
                    SweetAlertDialog dialog = new SweetAlertDialog(Main.this, SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("Ingrese un numero de teléfono").show();
                }
                else
                if(txtNumSMS.getText().equals(""))
                {
                    SweetAlertDialog dialog = new SweetAlertDialog(Main.this, SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("Ingrese un numero de SMS").show();
                }
                else
                {
                    /*SweetAlertDialog dialog = new SweetAlertDialog(Main.this, SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("Falta el método :'v").show();*/

                    //TODO
                    //Método de chayo joto
                    SweetAlertDialog pDialog = new SweetAlertDialog(Main.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Enviando");
                    pDialog.setCancelable(false);
                    pDialog.show();
            /////////==========METODO MENSAJES===========//////////////
                    mensajes();
            //////=======================================//////////////
                    pDialog.dismiss();

                }
            }
        });
    }

    private void mensajes()
    {
        for(int i=0; i<=numSMS; i++)
        {
            sendSMS(txtTelefono.getText().toString(),txtSMS.getText().toString());
        }
    }

    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message,null, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
