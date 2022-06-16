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

//import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext; 				/* PARA USO NO TOMCAT */
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
//import javax.servlet.ServletRequest; 


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
@RequestMapping("/")
public class AvaliacaoAPI{	

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
	private static final String DIR_FOTOS = "fotoscv";
    private List<String> listExtOk = Arrays.asList(new String[]{"jpeg", "jpg", "png"});

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
	@RequestMapping(value="/usuarios", method = RequestMethod.POST)
	public void login(@RequestParam(name="cPF",  required=false)  String cPF,
	 				  @RequestParam(name="novo", 	  required=true)  int novo,
					  @RequestParam(name="senha",      required=false)  String senha, 
					  HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	//public Response login(@RequestParam("nome")  String nome, @RequestParam("email") String email){
		
		String strJSON = "";

		long usuarioId = 0;

		if (novo == 1){
			//if (nome.trim().length() > 0){
			usuarioId = usuarioService.addUsuario();
			//}
			strJSON = "{\"usuarioid\": " + usuarioId + "}";
		}
		else {
			/*
			Usuario usuario = usuarioService.findByCPF(cPF);

			if (usuario != null){
				strJSON = "{\"usuarioid\": " + usuario.getId() + "}";
			}
			else {
				strJSON = "{\"erro\": \"Usuário não cadastrado\"}";
			}
			*/
			strJSON = "{\"usuarioid\": 0}";

		}

		send2Browser("text", response, strJSON);
		
	}

	//@Produces("text/plain ; charset=UTF-8")
	/*
	@RequestMapping(value="/respostas/???????????????", method = RequestMethod.GET)
	public void bkGetRespostas(@RequestParam("usuarioId") int usuarioId, HttpServletResponse response) throws ServletException, IOException{

		List<Resposta> lista = respostaService.getRespostasUltimaPergunta(usuarioId);
	
		Gson gson = new Gson();

		send2Browser("text", response, gson.toJson(lista));

	}
	*/
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value="/perguntas", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String showProximaPergunta(@RequestParam("usuarioid")  int usuarioId, 
									@RequestParam("ordemid")  int ordemId,
									HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		//List<String> respostasProlog = respostaService.retrieveRespostasProlog(usuarioId);

		Pergunta proxPergunta = null;

		Gson gson = null;

		//boolean risco = false;
		boolean baixa = false;

		String ret = "";

		try {

			/*
			if (respostasProlog.size() > 0){

				avaliacao = new Avaliacao(respostasProlog.toArray(new String[respostasProlog.size()]), regraPrologService.retrieveAllRules());

				risco = avaliacao.assess();

			}		

			if (risco){

				usuario = usuarioService.findById(usuarioId);

				baixa = respostaService.darBaixa(usuarioId);

				ret = "{\"conclusao\": \"" + usuario.getNome() + ", se trata de um Ponto Critico de Controle !\"}";

			}
			else { //Sem risco
			*/

			proxPergunta = perguntaService.retrieveProximaPergunta(usuarioId, ordemId);

			if (proxPergunta != null){

				gson = new Gson();

				ret = gson.toJson(proxPergunta);

			}
			else {

				Usuario usuario = usuarioService.findById(usuarioId);

				baixa = respostaService.darBaixa(usuarioId);

				ret = "{\"conclusao\": \"" + usuario.getCPF() + ", cadastro realizado com sucesso !\"}";

			}

		}
		catch (Exception e){
			e.printStackTrace();
		}

		//send2Browser("json", response, ret);
		return ret;


	}

	//@Produces("text/plain")
	@RequestMapping(value="/perguntas/{perguntaid}/resposta", method = RequestMethod.POST)
	public void enviarResposta(@RequestParam("usuarioid") int usuarioId, 
							   @PathVariable("perguntaid") int perguntaId, 
							   @RequestParam("respostaid") String respostaIds,
							   @RequestParam("resposta") String respostas,
							   HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ret = 0;

		if (respostaIds.equals("1") && perguntaId > 1){

			Alternativa alternativa = alternativaService.findAlternativaPerId(perguntaId, 1);

			if (alternativa.getPerguntaIDdeComparacao() > 0 && alternativa.getPerguntaIDdeComparacao() < perguntaId){

				Scripte scripte = scripteService.find(alternativa.getScriptIDdeComparacao());

				Resposta respostaDeComparacao = respostaService.getResposta(usuarioId, alternativa.getPerguntaIDdeComparacao(), 1);

				Object objRet = respostaService.comparar2Respostas(usuarioId, perguntaId, respostas, scripte.getNome(), scripte.getBody(), respostaDeComparacao.getResposta());

				try {
					ret = (int) objRet;
					respostaService.addResposta(usuarioId, perguntaId, "1", respostas);
					send2Browser("text", response, "[{\"status\":" + ret + "}]");
				}
				catch(ClassCastException cce){
					String msgErro = (String) objRet;
					send2Browser("text", response, "[{\"status\":\"" + msgErro + "\"}]");
				}

			}
			else {

				ret = respostaService.addResposta(usuarioId, perguntaId, respostaIds, respostas);
				send2Browser("text", response, "[{\"status\":" + ret + "}]");

			}

		}
		else {

			ret = respostaService.addResposta(usuarioId, perguntaId, respostaIds, respostas);
			send2Browser("text", response, "[{\"status\":" + ret + "}]");

		}
		
	}


	@RequestMapping(value="/upload", 
					method = RequestMethod.POST,
					produces = "application/json")
    public void uploadFile( @RequestParam("usuarioid")  int usuarioId,                            
                            @RequestParam("alternativa") MultipartFile file,
                              HttpServletRequest request, 
                              HttpServletResponse response) {

        
		String pathSep = System.getProperty("path.separator");		
        String ret = "";

        try {

        	String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);

        	//if (descricao.equals("photo") && listExtOk.indexOf(ext) > 0){
        	if (listExtOk.indexOf(ext) > 0){

        		/*********************************************
				ServletContext -> PARA USO NO TOMCAT 
        		**********************************************/
        		ServletContext servletContext = request.getServletContext();

        		String ctxDir   = servletContext.getRealPath("/");
        		//String fileDir  = ctxDir + pathSep + DIR_FOTOS + pathSep;
        		String fileNome = "foto_"+ usuarioId + "." + ext;

        		//Path path = FileSystems.getDefault().getPath(fileDir, fileNome);
	        	        	
	        	file.transferTo(new File(ctxDir + "/" + fileNome));

	        	//file.transferTo(new File(dirDestino + pathSep + DIR_FOTOS));

	        	send2Browser("text", response, "{\"status\": 1 }");

        	}
        	else send2Browser("text", response, "{\"status\": 0 }");


       	}       	
 		catch(IOException ioe){
       		System.out.println(ioe.getMessage());
       	}
        catch(ServletException se){
			System.out.println(se.getMessage());
       	}
       	catch(IllegalStateException ise){
			System.out.println(ise.getMessage());
       	}
       	catch(Exception e){
       		System.out.println(e.getMessage());
       	}   

    }

    public String getResposta(List<Resposta> lista, int i){
    	    	
    	String ret = "";
    	Resposta item = null;
    	
    	if (lista.size() > 0){

    		try {
    			item = lista.get(i - 1); 
    			ret = item.getResposta();   		
    		}
    		catch(IndexOutOfBoundsException iobe){
    			System.out.println("Resposta " + i + "nao existe no questionario!");
    		}

    	}

    	return ret;
	    
    }

    @RequestMapping(value="/curriculum/{usuarioId}", method = RequestMethod.GET)
	public ModelAndView showCurriculum(@PathVariable("usuarioId") int usuarioId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ModelAndView mav = null;
		
 		Curriculum c = new Curriculum();

		List<Resposta> lista = respostaService.getRespostas(usuarioId);

		String foto = request.getServletContext().getContextPath() + "/" + DIR_FOTOS + "/" + usuarioId + ".jpeg";
 		c.setFotoLinkJPEG(foto);
 		foto = request.getServletContext().getContextPath() + "/" + DIR_FOTOS + "/" + usuarioId + ".jpg";
 		c.setFotoLinkJPG(foto);
 		foto = request.getServletContext().getContextPath() + "/" + DIR_FOTOS + "/" + usuarioId + ".png";
 		c.setFotoLinkPNG(foto);

		c.setR1(getResposta(lista,   1));
		c.setR2(getResposta(lista,   2));
		c.setR3(getResposta(lista,   3));
		c.setR4(getResposta(lista,   4));
		c.setR5(getResposta(lista,   5));

		c.setR6(getResposta(lista,   6));
		c.setR7(getResposta(lista,   7));
		c.setR8(getResposta(lista,   8));
		c.setR9(getResposta(lista,   9));
		c.setR10(getResposta(lista, 10));
		
		c.setR11(getResposta(lista, 11));
		c.setR12(getResposta(lista, 12));
		c.setR13(getResposta(lista, 13));
		c.setR14(getResposta(lista, 14));
		c.setR15(getResposta(lista, 15));
		
		c.setR16(getResposta(lista, 16));
		c.setR17(getResposta(lista, 17));
		c.setR18(getResposta(lista, 18));
		c.setR19(getResposta(lista, 19));
		c.setR20(getResposta(lista, 20));
		
		c.setR21(getResposta(lista, 21));
		c.setR22(getResposta(lista, 22));
		c.setR23(getResposta(lista, 23));
		c.setR24(getResposta(lista, 24));
		c.setR25(getResposta(lista, 25));
		
		c.setR26(getResposta(lista, 26));
		c.setR27(getResposta(lista, 27));
		c.setR28(getResposta(lista, 28));
		c.setR29(getResposta(lista, 29));
		c.setR30(getResposta(lista, 30));
		
		c.setR31(getResposta(lista, 31));
		c.setR32(getResposta(lista, 32));
		c.setR33(getResposta(lista, 33));
		c.setR34(getResposta(lista, 34));
		c.setR35(getResposta(lista, 35));
		
		c.setR36(getResposta(lista, 36));
		c.setR37(getResposta(lista, 37));
		c.setR38(getResposta(lista, 38));
		c.setR39(getResposta(lista, 39));
		c.setR40(getResposta(lista, 40));
		
		c.setR41(getResposta(lista, 41));
		c.setR42(getResposta(lista, 42));
		c.setR43(getResposta(lista, 43));
		c.setR44(getResposta(lista, 44));
		c.setR45(getResposta(lista, 45));
		
		c.setR46(getResposta(lista, 46));
		c.setR47(getResposta(lista, 47));
		c.setR48(getResposta(lista, 48));
		c.setR49(getResposta(lista, 49));
		c.setR50(getResposta(lista, 50));
		
		c.setR51(getResposta(lista, 51));
		c.setR52(getResposta(lista, 52));
		c.setR53(getResposta(lista, 53));
		c.setR54(getResposta(lista, 54));
		c.setR55(getResposta(lista, 55));

		c.setR56(getResposta(lista, 56));
		c.setR57(getResposta(lista, 57));
		c.setR58(getResposta(lista, 58));
		c.setR59(getResposta(lista, 59));
		c.setR60(getResposta(lista, 60));
		
		c.setR61(getResposta(lista, 61));
		c.setR62(getResposta(lista, 62));
		c.setR63(getResposta(lista, 63));
		c.setR64(getResposta(lista, 64));
		c.setR65(getResposta(lista, 65));

		mav = new ModelAndView("curriculum");
		mav.addObject("cv", c);

        return mav;

	}
	
}