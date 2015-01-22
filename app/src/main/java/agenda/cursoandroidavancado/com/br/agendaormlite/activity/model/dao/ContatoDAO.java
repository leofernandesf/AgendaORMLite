package agenda.cursoandroidavancado.com.br.agendaormlite.activity.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import agenda.cursoandroidavancado.com.br.agendaormlite.activity.modelo.bean.Contato;

/**
 * Created by leo on 21/01/2015.
 */
public class ContatoDAO extends OrmLiteSqliteOpenHelper{

    private static final String DATABESE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Contato, Long> dao = null;

    public ContatoDAO(Context context){
        super(context,DATABESE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            Log.i(ContatoDAO.class.getSimpleName(), "onCret()");
            TableUtils.createTable(connectionSource, Contato.class);
            Contato contato = new Contato();
            contato.setNome("Administrador");
            contato.setEmail("teste@androidavancaod.com.br");
            contato.setTelefone("9900-8877");
            cadastrar(contato);
        } catch (SQLException ex){
            Log.e(ContatoDAO.class.getSimpleName(), "onCreat(): Falha ao criar tabelas", ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            Log.i(ContatoDAO.class.getSimpleName(), "onUpgrade");
            TableUtils.dropTable(connectionSource,Contato.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException ex){
            Log.e(ContatoDAO.class.getSimpleName(), "onUpgrade(): falha na atualizacao", ex);
        }

    }

    public Dao<Contato, Long> getDao(){
        if (dao == null){
            try {
                dao = getDao(Contato.class);
            } catch (SQLException e){
                Log.e(ContatoDAO.class.getSimpleName(), "getDao(): Falha ao criar DAO", e);
            }
        }
        return dao;
    }

    public void close(){
        super.close();
        dao = null;
    }

    public void cadastrar(Contato contato) throws SQLException{
        getDao().create(contato);
    }

    public  void  excluir(Contato contato) throws SQLException{
        getDao().delete(contato);
    }

    public void alterar(Contato contato) throws SQLException{
        getDao().update(contato);
    }

    public List<Contato> listar() throws SQLException{
        return getDao().queryForAll();
    }
}
