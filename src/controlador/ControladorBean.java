package controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Carro;
import modeloDAO.CarroDAO;

@ManagedBean
@SessionScoped
public class ControladorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Carro> carrosEstacionados;	
	private Carro carroNovo = new Carro();
	private Carro carroSelecionado = new Carro();
	private CarroDAO carroDAO = new CarroDAO();
	
	@PostConstruct
	public void init(){	
		try{
			carrosEstacionados = carroDAO.listarTodos();
			
		}catch(Exception e){
			System.out.println(" Erro ao tentar listar os carros: "+ e);
		}
	}
	
	public void listarCarros (){
		carrosEstacionados = carroDAO.listarTodos();
	}
	
	public void salvarCarro (){
		carroDAO.salvar(carroNovo);
		listarCarros();
		
		carroNovo = new Carro(); //reseta o carro da tela e coloca um novo para preenchimento.
		System.out.println("controlador salvando com sucesso");
	}

	public void excluirCarro () throws IOException{
        carroDAO.excluir(carroSelecionado);
        listarCarros();
        
        //Redireciona para a pagina dos registros (carros estacionados)
        FacesContext.getCurrentInstance().getExternalContext()
		.redirect("RegistroDoEstacionamento.xhtml");
	}
	
	public void atualizarCarro () throws IOException{
		carroDAO.atualizar(carroSelecionado);
		listarCarros();
		
		//Redireciona para a pagina dos registros (carros estacionados)
        FacesContext.getCurrentInstance().getExternalContext()
		.redirect("RegistroDoEstacionamento.xhtml");
	}

	public Carro getCarroNovo() {
		return carroNovo;
	}

	public void setCarroNovo(Carro carroNovo) {
		this.carroNovo = carroNovo;
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	public ArrayList<Carro> getCarrosEstacionados() {
		return carrosEstacionados;
	}
	
	
	
}
