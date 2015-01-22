package agenda.cursoandroidavancado.com.br.agendaormlite.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agenda.cursoandroidavancado.com.br.agendaormlite.R;
import agenda.cursoandroidavancado.com.br.agendaormlite.activity.model.dao.ContatoDAO;
import agenda.cursoandroidavancado.com.br.agendaormlite.activity.modelo.bean.Contato;


public class ListagemActivity extends ActionBarActivity {

    private List<String> listaDeContatos = new ArrayList<>();

    private  final String TAG = ActionBarActivity.class.getSimpleName();

    private void carregarLista(){
        listaDeContatos.clear();
        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> lista = null;
        try {
            lista = dao.listar();
        }catch (SQLException e){
            Log.e(TAG, "falha ao carregar lista");
        }
        for (Contato contato : lista){
            listaDeContatos.add(contato.toString());
        }
        dao.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagemlayout);

        int adapterLayout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter = null;

        ListView lvListagem = (ListView) findViewById(R.id.lvListagem);
        carregarLista();
        adapter = new ArrayAdapter<String>(this,adapterLayout,listaDeContatos);
        lvListagem.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listagemmenu, menu);
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
