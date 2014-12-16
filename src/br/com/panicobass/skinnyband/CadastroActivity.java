package br.com.panicobass.skinnyband;





import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by tairo on 28/11/14.
 */
public class CadastroActivity extends ActionBarActivity {
	public static final int SELECT_CAMERA_IMG = 11;
	public static final int SELECT_INTERNAL_IMG = 12;
	
	ImageButton imgBtnFoto_fragment,imgBtnGaleria_fragment;
	ImageView imgBtnFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.push_left_enter, R.anim.push_right_exit);
        setContentView(R.layout.activity_cadastro_novo_usuario);
        
        imgBtnFoto= (ImageView)findViewById(R.id.imgBtnFoto);
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
        /**                    Método para fazer tirar foto						*/
        /***********************************************************************/
        imgBtnFoto_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        		startActivityForResult(intent,SELECT_CAMERA_IMG);
                dialog.dismiss();
            }

        });        
      
        /************************************************************************/
        /**				methodo para buscar foto de galeria						*/
        /***********************************************************************/
        imgBtnGaleria_fragment.setOnClickListener(new ImageView.OnClickListener(){
			@Override
			public void onClick(View v) {
				// Vai para a galeria para escolher uma foto
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(intent, SELECT_INTERNAL_IMG);
	             dialog.dismiss();				
			}        	
        });
    }
    
    /*****************************************************************************/
    /**	Pega o resultado da chamada da activity que chamou a camera	ou a foto	*/
    /***************************************************************************/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		
		//verifica se a intent retornada não é nula da damera
		if (requestCode == SELECT_CAMERA_IMG) {
			if (resultCode == RESULT_OK) { 
				
				//se não for nulo a gente pega a foto
				Bundle bundle = intent.getExtras();
				
				//verifica novamente se não é nullo
				if (bundle != null) {
					Bitmap img = (Bitmap) bundle.get("data");					
					//coloca a imagem no imageview
					ImageView image = (ImageView)findViewById(R.id.imgBtnFoto);
					image.setImageBitmap(img);
				}
			}
		}
		
		if (requestCode == SELECT_INTERNAL_IMG) {
			
			if (resultCode == RESULT_OK) { //verifica se o resultado esta OK
				Uri imagemSelecionada = intent.getData(); 
				String[] colunas = {MediaStore.Images.Media.DATA};//pega a coluna que referancia a imagem
				Cursor cursor = getContentResolver().query(imagemSelecionada, colunas, null, null, null);
				cursor.moveToFirst();
				
				int indexColuna = cursor.getColumnIndex(colunas[0]);
				String pathImage = cursor.getString(indexColuna);
				cursor.close();
				
				//passa o resultado da busca para um BITMAP
				Bitmap img = BitmapFactory.decodeFile(pathImage);
				ImageView image = (ImageView)findViewById(R.id.imgBtnFoto);
				image.setImageBitmap(img);
				image.setImageBitmap(img);
			}
		}
	}
}
