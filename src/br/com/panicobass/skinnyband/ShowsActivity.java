package br.com.panicobass.skinnyband;

import java.util.Locale;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ShowsActivity extends ActionBarActivity {
	
	
	   private DrawerLayout mDrawerLayout;
	    private ListView mDrawerList;
	    private ActionBarDrawerToggle mDrawerToggle;

	    private CharSequence mDrawerTitle;
	    private CharSequence mTitle;
	    private String[] mGenerosTitles;   
	    
	    private SearchView searchView; 
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Coloca um efeito antes de mostrar a tela principal
		overridePendingTransition(R.anim.push_right_enter,R.anim.push_left_exit);
		setContentView(R.layout.activity_background_main);

		// mostra o logo do app na actionbar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
		
		
			mTitle = mDrawerTitle = getTitle();
			//Pega um array de String para colocar no drawer
			
	        mGenerosTitles = getResources().getStringArray(R.array.generos_array);
	        //Linka o DrawerLayout do java com o xml
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        
	        //Linka o ListView do java com o xml
	        mDrawerList = (ListView) findViewById(R.id.left_drawer);

	        //  Configura uma sombra personalizada quando o Drawer é aberto
	        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	        
	        // set up the drawer's list view with items and click listener
	        mDrawerList.setAdapter(new ArrayAdapter<String>(ShowsActivity.this, R.layout.drawer_lista_items, mGenerosTitles));
	        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	        
	        // ActionBarDrawerToggle ties together the the proper interactions
	        // between the sliding drawer and the action bar app icon
	        mDrawerToggle = new ActionBarDrawerToggle(
	                ShowsActivity.this,    /* Classe que chama a activity Activity */
	                mDrawerLayout,         /* Layout que será mostrado DrawerLayout  */
	                R.drawable.ic_drawer,  /* Icone que aparecera na ActionBar */
	                R.string.drawer_open  /* Descrição */
		) {

			// Método chamado quando o fragment é fechado
			public void onDrawerClosed(View view) {
				//Toast.makeText(ShowsActivity.this, "Teste Drawer close",Toast.LENGTH_SHORT).show();
			}

			// Método chamado quando o fragment é aberto
			public void onDrawerOpened(View drawerView) {
				//Toast.makeText(ShowsActivity.this, "Teste Drawer open",	Toast.LENGTH_SHORT).show();
			}
			
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			selectItem(0);
		}

	}

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.principal_menu, menu);
	        
	        //Implementa o SearchView	       
            searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setOnQueryTextListener(new SearchFiltro());
	        return super.onCreateOptionsMenu(menu);        

	    }
	    
	    //Classe de busca do SearchView 
	    private class SearchFiltro implements OnQueryTextListener{

			@Override
			public boolean onQueryTextChange(String query) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				
				//Cria uma intent para manipular o websearch para o item selecionado
	        	//Envia conteudo do searchView capturado para uma intent para fazer uma busca na internet
	            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
	            intent.putExtra(SearchManager.QUERY,query);
	            
	            // catch event that there's no activity to handle intent
	            if (intent.resolveActivity(getPackageManager()) != null) {
	                startActivity(intent);
	            } else {
	                Toast.makeText(ShowsActivity.this, R.string.app_not_available, Toast.LENGTH_LONG).show();
	            }
				return false;
			}
	    	
	    }

	    /* Called whenever we call invalidateOptionsMenu() */
	    @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // Se o drawer for aberto, esconde os items de ações  relatados para o cotainer 
	        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
	        return super.onPrepareOptionsMenu(menu);
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	         // The action bar home/up action should open or close the drawer.
	         // ActionBarDrawerToggle will take care of this.
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	        
	        // Manipula as ações dos botões
	        switch(item.getItemId()) {
	        case R.id.action_settings:            
	        	
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }

	    /* Captura o evento de click da lista de items do drawer */
	    private class DrawerItemClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            selectItem(position);
	        }
	    }

	    private void selectItem(int position) {
	        // Atualiza o contaienr principal trocando o fragment
	        Fragment fragment = new ShowFragment();
	        Bundle args = new Bundle();
	        args.putInt(ShowFragment.ARG_OPCAO_NUMBER, position);
	        fragment.setArguments(args);

	        FragmentManager fragmentManager = getFragmentManager();
	        String opcao = getResources().getStringArray(R.array.generos_array)[position];
	        

	        	fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();	
	     

	        //Atualiza o item selecionado e titulo, depois fecha o Drawer
	        mDrawerList.setItemChecked(position, true);
	        setTitle(mGenerosTitles[position]);
	        mDrawerLayout.closeDrawer(mDrawerList);
	    }

	    
	    //Muda o titulo da ActionBar
	    @Override
	    public void setTitle(CharSequence title) {
	        mTitle = title;	        
	        getSupportActionBar().setTitle(mTitle);
	    }

	    /**
	     * When using the ActionBarDrawerToggle, you must call it during
	     * onPostCreate() and onConfigurationChanged()...
	     */

	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        //Syncroniza o toggle state depois  que o onRestoreInstanceState tiver ocorrido.
	        mDrawerToggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        // Pass any configuration change to the drawer toggls
	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }
	    

		/** 
		 * Fragment que aparece no "content_frame", mostra a imagem de im planeta
		 */
	    public static class ShowFragment extends Fragment {
	        public static final String ARG_OPCAO_NUMBER = "opcao_number";

	        public ShowFragment() {
	        	//Contrutor vazio para a subclasse fragment
	        }

	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        	
	        	View rootView;
	        	int i = getArguments().getInt(ARG_OPCAO_NUMBER);
	            String opcao = getResources().getStringArray(R.array.generos_array)[i];
	        
	      
				rootView = inflater.inflate(R.layout.shows_principais,container, false);
				int imageId = getResources().getIdentifier(
						opcao.toLowerCase(Locale.getDefault()), "drawable",	getActivity().getPackageName());
				
				((ImageView) rootView.findViewById(R.id.img1)).setImageResource(imageId);
				((ImageView) rootView.findViewById(R.id.img2)).setImageResource(imageId);
				((ImageView) rootView.findViewById(R.id.img3)).setImageResource(imageId);
				((ImageView) rootView.findViewById(R.id.img4)).setImageResource(imageId);
				((ImageView) rootView.findViewById(R.id.img5)).setImageResource(imageId);
				((ImageView) rootView.findViewById(R.id.img6)).setImageResource(imageId);
				
			
				//Quando a imagem pe clicada mostra um efeito de ZOOM
				((ImageView) rootView.findViewById(R.id.img1)).setOnClickListener(new CliqueInimacao());
				((ImageView) rootView.findViewById(R.id.img2)).setOnClickListener(new CliqueInimacao());
				((ImageView) rootView.findViewById(R.id.img3)).setOnClickListener(new CliqueInimacao());
				((ImageView) rootView.findViewById(R.id.img4)).setOnClickListener(new CliqueInimacao());
				((ImageView) rootView.findViewById(R.id.img5)).setOnClickListener(new CliqueInimacao());
				((ImageView) rootView.findViewById(R.id.img6)).setOnClickListener(new CliqueInimacao());
				
				getActivity().setTitle(opcao);

			return rootView;

		}
	        //Classe para imlemantar o clique do imageView
	        private class CliqueInimacao implements OnClickListener{
	        	//Animaçãço da Imagem
	        	final  Animation in = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.zoom_in_enter);
				@Override
				public void onClick(View v) {
					v.startAnimation(in);
		             v.setVisibility(View.VISIBLE);					
				}	        	
	        }
	       
	}
	    
	    
}
