package com.dunedin.sensorx.questionario.controller;

import com.dunedin.sensorx.questionario.model.*;
import com.dunedin.sensorx.questionario.model.controls.*;
import com.dunedin.sensorx.questionario.service.*;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.IllegalStateException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Collectors;

//import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext; 				/* PARA USO NO TOMCAT */
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


//@PropertySource("classpath:application.properties")
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/backoffice")
public class BackofficeController{	

	/*
	@Autowired
	ServletContext servletContext;
	*/

	@Autowired 
	AlternativaService alternativaService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	PerguntaService perguntaService;
	
	@Autowired
	RespostaService respostaService;
	
	@Autowired
	RegraPrologService regraPrologService;
	
	@Autowired
	ControleService controleService;

	@Autowired
	OpcaoService opcaoService;	

	@Autowired
	ScripteService scripteService;

	/*
	@Value("${upload.directory}")
    private String uploadDirectory;
	*/
	//private static final String DIR_FOTOS = "fotoscv";
    //private List<String> listExtOk = Arrays.asList(new String[]{"jpeg", "jpg", "png"});

	private void send2Browser(String contentType, HttpServletResponse response, String str) throws ServletException, IOException{
		
		ServletOutputStream out = null;

		if (contentType.equals("text")){
			response.setContentType("text/plain ; charset=UTF-8");
		}
		else if (contentType.equals("json")){
			response.setContentType("application/json ; charset=UTF-8");
		}
        response.setHeader("Access-Control-Allow-Origin", "*");
        out = response.getOutputStream();
        out.println(str);
        out.close();

	}
	
	//@Produces("text/plain ; charset=UTF-8")
	/*
	@RequestMapping(value="/respostas", method = RequestMethod.GET)
	public void bkRetrieveAllRespostas(HttpServletResponse response) throws ServletException, IOException{

		List<AuxResposta> lista = respostaService.bkRetrieveRespostasPerEmail("");
		
		Gson gson = new Gson();

		send2Browser("text", response, gson.toJson(lista));

	}
	*/
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value="/perguntas", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String bkRetrieveAllPerguntas(HttpServletResponse response) throws ServletException, IOException {

		List<Pergunta> lista = perguntaService.findAll();

		Gson gson = new Gson();
		
		return gson.toJson(lista);
		
	}
	
	//@Produces("text/plain ; charset=UTF-8")
	@RequestMapping(value="/perguntas/{perguntaid}", method = RequestMethod.GET)
	public void bkRetrievePerguntas(@PathVariable("perguntaid") int perguntaId,
		HttpServletResponse response) throws ServletException, IOException {

		String ret = "";
		Pergunta p = perguntaService.findPerId(perguntaId);

		Gson gson = new Gson();
		
		send2Browser("text", response, gson.toJson(p));
		
	}

	//@Produces("text/plain ; charset=UTF-8")
	@RequestMapping(value="/perguntas/{perguntaid}/alternativas", 
					method = RequestMethod.GET,
					produces = "application/json")
	public void bkRetrieveAlternativas(@PathVariable("perguntaid") int perguntaId,
		HttpServletResponse response) throws ServletException, IOException{

		List<Alternativa> la = alternativaService.findAllPerPerguntaId(perguntaId);

		Gson gson = new Gson();

		send2Browser("text", response, gson.toJson(la));
			
	}
	
	//@Produces("text/plain ; charset=UTF-8")
	@RequestMapping(value="/perguntas/{perguntaid}/alternativas/{alternativaId}", method = RequestMethod.GET)
	public void bkRetrieveAlternativa(@PathVariable("perguntaId") int perguntaId, 
		@PathVariable("alternativaId") int alternativaId,
		HttpServletResponse response) throws ServletException, IOException{

		Alternativa a = alternativaService.findAlternativaPerId(perguntaId, alternativaId);
	
		Gson gson = new Gson();

		send2Browser("text", response, gson.toJson(a));
		
	}
	
	//@Produces("text/plain ; charset=UTF-8")
	@RequestMapping(value="/perguntas", method=RequestMethod.POST, consumes = "application/json")
	public void addPergunta(@RequestBody Pergunta pergunta, HttpServletResponse response) throws ServletException, IOException{
		
		int ret = 0;
		ret = perguntaService.addPergunta(pergunta);

		send2Browser("text", response, "{\"status\":" + ret + "}");

	}
	
	//@Produces("text/plain ; charset=UTF-8")
	@DeleteMapping(value="/perguntas/{perguntaId}")
	public void delPergunta(@PathVariable("perguntaId") int perguntaId,
		HttpServletResponse response) throws ServletException, IOException{
		
		int ret = 0;
		//Pergunta p = perguntaService.findPerId(perguntaId);
		ret = perguntaService.delPergunta(perguntaId);

		send2Browser("text", response, "{\"status\":" + ret + "}");

	}
	
	//@Produces("text/plain ; charset=UTF-8")
	@RequestMapping(value = "/perguntas/{perguntaId}/alternativas", 
					method = RequestMethod.POST,
					consumes = "application/json",
					produces = "application/json")
	public void addAlternativa(@RequestBody Controle controle, HttpServletResponse response) throws ServletException, IOException{
		
		String ret = "";
		String[] aRet = new String[2];
		
		ret = alternativaService.addAlternativa(controle);
		
		aRet = ret.split(",");

		String obj = "";
		obj += "{";
		obj += "\"perguntaId\":" 	+ aRet[0] 	+ ",";
		obj += "\"alternativaId\":" + aRet[1] + ",";
		obj += "\"status\":" 	 	+ aRet[0];
		obj += "}";
		
		send2Browser("text", response, obj);

	}
	
	//@Produces("text/plain ; charset=UTF-8")
	@DeleteMapping(value="/perguntas/{perguntaId}/alternativas/{alternativaId}")
	public void delAlternativa(@PathVariable("perguntaId") int perguntaId, 
		@PathVariable("alternativaId") int alternativaId,
		HttpServletResponse response) throws ServletException, IOException {
		
		int ret = 0;
		
		/*
		ret = opcaoService.deleteAll(perguntaId, alternativaId);
		ret = controleService.delete(perguntaId, alternativaId);
		*/
		ret = alternativaService.delAlternativa(perguntaId, alternativaId);

		send2Browser("text", response, "{\"status\":" + ret + "}");

	}

	@RequestMapping(value="/perguntas/ordem", method = RequestMethod.GET)
	public void setOrdem(@RequestParam("lista") String lista,
		HttpServletResponse response) throws ServletException, IOException {

		int ret = 0;
		ret = perguntaService.ordenar(lista);

		send2Browser("text", response, "{\"status\":" + ret + "}");
		
	}

	//@PostMapping("/upload")
	@RequestMapping(value="/upload", 
					method = RequestMethod.POST,
					produces = "application/json")
    public void bkuploadFile( @RequestParam("perguntaId") int perguntaId,
                              @RequestParam("alternativaId") int alternativaId,
                              @RequestParam("descricao") String descricao,
                              @RequestParam("tipoHTML") String tipoHTML,
                              @RequestParam("file") MultipartFile file,
                              HttpServletRequest request, 
                              HttpServletResponse response) {

        List<Opcao> opcoes = new ArrayList<Opcao>();
        Opcao opcao = null;
        String rotulo = "";
        int i = 1;
        String ret = "";

        try {

	        BufferedReader buffR = new BufferedReader(new InputStreamReader((file.getInputStream())));

	        while ((rotulo = buffR.readLine()) != null){
	            opcao = new Opcao(perguntaId, alternativaId, i++, rotulo, rotulo);
	            opcoes.add(opcao);  
	        }        
	        
	        Controle controle = new Combobox();
	        controle.setPerguntaId(perguntaId);
	        controle.setAlternativaId(alternativaId);
	        controle.setDescricao(descricao);
	        controle.setTipoHTML(tipoHTML); 
	        controle.setOpcoes(opcoes);

	        ret = alternativaService.addAlternativa(controle);

	        send2Browser("text", response, "{\"status\":" + ret + "}");

       	}       	
        catch(IOException ioe){
       		System.out.println(ioe.getMessage());
       	}
        catch(ServletException se){
			System.out.println(se.getMessage());
       	}
       	catch(Exception e){
       		System.out.println(e.getMessage());
       	}   

    }

    @RequestMapping(value="/regra-comparacao", method = RequestMethod.POST)
	public void bkSaveRegraComparacao(@RequestParam("perguntaId") int perguntaId,
							   @RequestParam("perguntaIDdeComparacao") int perguntaIDdeComparacao,
							   @RequestParam("scriptId") int scriptId,
							   HttpServletResponse response) throws ServletException, IOException {

	    int ret = 0;
		ret = alternativaService.saveRegraComparacao(perguntaId, scriptId, perguntaIDdeComparacao);

		send2Browser("text", response, "{\"status\":" + ret + "}");
		
	}

    //@PostMapping("/upload")
	@RequestMapping(value="/script", 
					method = RequestMethod.POST,
					produces = "application/json")
    public void bkNovoScript(@RequestParam("file") MultipartFile file,
                               HttpServletRequest request, 
                               HttpServletResponse response) throws ServletException, IOException{

        /***************************************************
		* Cada arquivo contém somente 1 funcao
		* O nome do arquivo DEVE ser igual ao nome da funcao
		* Se a regra da funcao for satisfeita, retornar "1"
		* senão retornar "0"
        ***************************************************/
		BufferedReader buffR = null;
        String body = "";
        String ret = "";

        try {        
	        buffR = new BufferedReader(new InputStreamReader((file.getInputStream())));
	        body = buffR.lines().collect(Collectors.joining());
	        ret = scripteService.add(file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf(".")), body); 
	        send2Browser("text", response, "{\"status\":" + ret + "}");
	    }       	
        catch(IOException ioe){
       		System.out.println(ioe.getMessage());
       		send2Browser("text", response, "{\"status\":" + 0 + "}");
       	}

    }

    @RequestMapping(value="/script/{scripteId}", 
					method = RequestMethod.POST,
					produces = "application/json")
    public void bkEditarScript(@PathVariable("scripteId") int scripteId,
    						   @RequestParam("file") MultipartFile file,
                               HttpServletRequest request, 
                               HttpServletResponse response) throws ServletException, IOException{

        /***************************************************
		* Cada arquivo contém somente 1 funcao
		* O nome do arquivo DEVE ser igual ao nome da funcao
		* Se a regra da funcao for satisfeita, retornar "1"
		* senão retornar "0"
        ***************************************************/
		BufferedReader buffR = null;
        String body = "";
        String ret = "";

        try {        
	        buffR = new BufferedReader(new InputStreamReader((file.getInputStream())));
	        body = buffR.lines().collect(Collectors.joining());
	        ret = scripteService.editar(scripteId, file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf(".")), body); 
	        send2Browser("text", response, "{\"status\":" + ret + "}");
	    }       	
        catch(IOException ioe){
       		System.out.println(ioe.getMessage());
       		send2Browser("text", response, "{\"status\":" + 0 + "}");
       	}

    }

	@RequestMapping(value="/script", method = RequestMethod.GET)
	public void bkListarScripts(HttpServletResponse response) throws ServletException, IOException {

	    boolean ret = false;
		List<Scripte> lista = scripteService.listar();

		Gson gson = new Gson();

		send2Browser("text", response, gson.toJson(lista));
		
	}

	@RequestMapping(value="/script/{scripteId}", method = RequestMethod.GET)
	public void bkGetScript(@PathVariable("scripteId") int scripteId, 
								HttpServletResponse response) throws ServletException, IOException {

	    boolean ret = false;
		Scripte scripte = scripteService.find(scripteId);

		Gson gson = new Gson();

		send2Browser("text", response, gson.toJson(scripte));
		
	}

	@DeleteMapping(value="/script/{scripteId}")
	public void bkDelScript(@PathVariable("scripteId") int scripteId,
		HttpServletResponse response) throws ServletException, IOException{
		
		int ret = 0;
		ret = scripteService.apagar(scripteId);

		Gson gson = new Gson();

		send2Browser("text", response, "{\"status\":" + 1 + "}");

	}

}