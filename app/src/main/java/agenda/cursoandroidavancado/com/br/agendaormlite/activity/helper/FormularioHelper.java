package agenda.cursoandroidavancado.com.br.agendaormlite.activity.helper;

import android.widget.EditText;

import agenda.cursoandroidavancado.com.br.agendaormlite.R;
import agenda.cursoandroidavancado.com.br.agendaormlite.activity.ListagemActivity;
import agenda.cursoandroidavancado.com.br.agendaormlite.activity.modelo.bean.Contato;

/**
 * Created by leo on 21/01/2015.
 */
public class FormularioHelper {

    private EditText nome;
    private EditText telefone;
    private EditText email;

    private Contato contato;

    public  FormularioHelper(ListagemActivity activity){

        nome = (EditText) activity.findViewById(R.id.edNome);
        telefone = (EditText) activity.findViewById(R.id.edTelefone);
        email = (EditText) activity.findViewById(R.id.edEmail);

        contato = new Contato();
    }

     public Contato getContato(){
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        contato.setEmail(email.getText().toString());
         return contato;
    }

    public void setContato(Contato contato){
        nome.setText(contato.getNome());
        telefone.setText(contato.getTelefone());
        email.setText(contato.getEmail());
        this.contato = contato;
    }
}
