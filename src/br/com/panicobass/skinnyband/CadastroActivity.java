package br.com.panicobass.skinnyband;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by tairo on 28/11/14.
 */
public class CadastroActivity extends ActionBarActivity {
	
	ImageButton imgBtnFoto,imgBtnFoto_fragment,imgBtnGaleria_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.push_left_enter, R.anim.push_right_exit);
        setContentView(R.layout.activity_cadastro_novo_usuario);
        
        imgBtnFoto= (ImageButton)findViewById(R.id.imgBtnFoto);
        imgBtnFoto.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				//chama o fragmento parar escolha da foto do perfil
				chamaFragmentFoto();
				
			}
		});
    }
    
    public void chamaFragmentFoto() {
        overridePendingTransition(R.anim.rotate_in_enter, R.anim.rotate_in_enter);
        /*************************************************************************/
        /**  Mostrar um DialogFrafment para ser inserido o endereço so website  */
        /***********************************************************************/

        final Dialog dialog = new Dialog(CadastroActivity.this);

        /** inflando o layout pra criação do DialogFragment*/
        dialog.setContentView(R.layout.fragment_foto);

        /*************************************************************************/
        /**                Cria os componentes no DialogFrafment 				*/
        /***********************************************************************/

        imgBtnFoto_fragment = (ImageButton) dialog.findViewById(R.id.imgBtnFoto_fragment);
        imgBtnGaleria_fragment = (ImageButton) dialog.findViewById(R.id.imgBtnGaleria_fragment);

        /*************************************************************************/
        /**                    Mostra o DialogFrafment 						*/
        /***********************************************************************/
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        dialog.setTitle("Selecione uma foto");
        dialog.show();

        /*************************************************************************/
        /**                    Método para fazer login 						*/
        /***********************************************************************/
        imgBtnFoto_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(CadastroActivity.this, ShowsActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }

        });
    }
}
