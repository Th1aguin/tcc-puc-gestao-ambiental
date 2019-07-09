package br.com.puc.tcc.binder;

import java.beans.PropertyEditorSupport;

import br.com.puc.tcc.client.BarragemClient;
import br.com.puc.tcc.model.Barragem;

public class BarragemPropertyEditor extends PropertyEditorSupport {
	
	BarragemClient cliente;
	
	public BarragemPropertyEditor(BarragemClient cliente) {
		this.cliente =cliente;
	}
	
    @Override
    public void setAsText(String text) {
        //transforma a String com o id em um long
        Long id = new Long(text);
        //recupera no db o perfil do id referido
		Barragem barragem =  cliente.buscar(id);
        //add o objeto perfil encontrado no objeto user no controller através do método setValue da super-classe.
        super.setValue(barragem);
    }
}