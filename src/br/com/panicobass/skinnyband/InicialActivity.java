package br.com.panicobass.skinnyband;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

/**
 * Created by tairo on 24/11/14.
 */
public class InicialActivity extends Activity {

    private EditText edtUsuarioLogin, edtSenhaLogin;
    private Button btnLoginFragment, btnLogin_inicial,btnCadastro_inicial;
    private ViewFlipper img1,img2,img3,img4,img5,img6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
        setContentView(R.layout.activity_inicial);

        //Linka o java com o Xml
        img1 = (ViewFlipper)findViewById(R.id.img1);
        img2 = (ViewFlipper)findViewById(R.id.img2);
        img3 = (ViewFlipper)findViewById(R.id.img3);
        img4 = (ViewFlipper)findViewById(R.id.img4);
        img5 = (ViewFlipper)findViewById(R.id.img5);
        img6 = (ViewFlipper)findViewById(R.id.img6);

      final  Animation in = AnimationUtils.loadAnimation(this, R.anim.zoom_in_enter);

     img1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             img1.startAnimation(in);
             img1.setVisibility(View.VISIBLE);
         }
     });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2.startAnimation(in);
                img2.setVisibility(View.VISIBLE);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img3.startAnimation(in);
                img3.setVisibility(View.VISIBLE);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img4.startAnimation(in);
                img4.setVisibility(View.VISIBLE);
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img5.startAnimation(in);
                img5.setVisibility(View.VISIBLE);
            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img6.startAnimation(in);
                img6.setVisibility(View.VISIBLE);
            }
        });





        /**
         * Métodos de chamada para as telas de Login e Cadastro
         */
        btnLogin_inicial = (Button) findViewById(R.id.btnLogin_inicial);
        btnLogin_inicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaLogin();
            }
        });
        
        btnCadastro_inicial = (Button) findViewById(R.id.btnCadastrar_inicial);
        btnCadastro_inicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialActivity.this,CadastroActivity.class);                
                startActivity(intent);
                finish();
            }
        });
    }
    

    public void chamaLogin() {
        overridePendingTransition(R.anim.rotate_in_enter, R.anim.rotate_in_enter);
        /*************************************************************************/
        /**  Mostrar um DialogFrafment para ser inserido o endereço so website  */
        /***********************************************************************/

        final Dialog dialog = new Dialog(InicialActivity.this);

        /** inflando o layout pra criação do DialogFragment*/
        dialog.setContentView(R.layout.fragment_login);

        /*************************************************************************/
        /**                Cria os componentes no DialogFrafment 				*/
        /***********************************************************************/

        edtUsuarioLogin = (EditText) dialog.findViewById(R.id.edtUsuarioLogin);
        edtSenhaLogin = (EditText) dialog.findViewById(R.id.edtSenhaLogin);
        btnLoginFragment = (Button) dialog.findViewById(R.id.btnLoginFragment);


        /*************************************************************************/
        /**                    Mostra o DialogFrafment 						*/
        /***********************************************************************/
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        dialog.setTitle(R.string.txtMensagemLogin);
        dialog.show();

        /*************************************************************************/
        /**                    Método para fazer login 						*/
        /***********************************************************************/
        btnLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(InicialActivity.this, PrincipalActivity.class);

                overridePendingTransition(R.anim.push_down_enter, R.anim.push_down_exit);
                startActivity(intent);
                dialog.dismiss();
                InicialActivity.this.finish();
            }

        });
    }


}
