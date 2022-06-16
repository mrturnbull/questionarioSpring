package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.dao.PerguntaManager;
import com.dunedin.sensorx.questionario.model.Pergunta;
import com.dunedin.sensorx.questionario.model.Alternativa;
import com.dunedin.sensorx.questionario.model.controls.Combobox;
import com.dunedin.sensorx.questionario.model.controls.Controle;
import com.dunedin.sensorx.questionario.model.controls.Opcao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryHint;
import org.springframework.stereotype.Repository;

@Repository
public class PerguntaManagerBean implements PerguntaManager {

    Pergunta pergunta = null;

    @PersistenceContext
    private EntityManager em;

    public PerguntaManagerBean(){};

    public int getMaxOrdem() {

            int ret = 0;
            try {

                    Object obj = em.createNamedQuery("Pergunta.findMaxOrdem").getSingleResult();
                    if (obj != null) 
                            ret = (int)(obj);
                    else
                            ret = 0;

            }	
            catch(Exception e){
                    e.printStackTrace();
            }
            return ret;

    }

    public int ordenar(String lista){

            String arrParams[] = lista.split(";");
            int ret = 0;
            String sql = "";

            try {

                    for (int i=0; i < arrParams.length; i++){

                            String params[] = arrParams[i].split(",");

                            sql  = " UPDATE PerguntaEnunciado";
                            sql += " SET Ordem = " + params[1];
                            sql += " WHERE PerguntaEnunciadoId = " + params[0];

                            em.createNativeQuery(sql).executeUpdate();

                    }

                    ret = 1;

            }
            catch(Exception e) {
                    e.printStackTrace();
            }

            return ret;


    }

    public int delPergunta(int perguntaId){

            int ret = 0; //Nao apagou

            try {
                    em.createNamedQuery("Pergunta.delPergunta").setParameter("perguntaId", perguntaId).executeUpdate();
                    ret = 1;
            }
            catch(Exception e){
                    e.printStackTrace();	
            }

            return ret;

    }

    public List<Pergunta> findAll(){

            List<Pergunta> u;

            try {
                    u = (List<Pergunta>) em.createNamedQuery("Pergunta.findAll").getResultList();
                    return u;
            }
            catch(NoResultException nre){
                    return null;	
            }
    }

    public Pergunta findPerId(int perguntaId){

            Pergunta u;

            try {
                    u = (Pergunta) em.createNamedQuery("Pergunta.findPerId").setParameter("id", perguntaId).getSingleResult();
                    return u;
            }
            catch(NoResultException nre){
                    nre.printStackTrace();
                    return null;	
            }
    }

    public int addPergunta(Pergunta perguntaJSON){

//{"id": 1, "enunciado": "Digite o nome completo:", "alternativas": [],
// "tipo": 0, "ordem": 1, "visivel": 1, "isRequired": 1}		

            Pergunta p = new Pergunta();

            p.setEnunciado(perguntaJSON.getEnunciado());
            p.setTipo(0);
            p.setOrdem(getMaxOrdem() + 1);
            p.setVisivel(1);
            p.setIsRequired(perguntaJSON.getIsRequired());

            em.persist(p);
            em.flush(); 				//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<????????

            return 1;

    }

    private Pergunta montaPergunta(List<Object[]> listaP) throws NumberFormatException{

            Pergunta pergunta = null;

            if (listaP.size() > 0) {

            Controle controle = null;

            List<Alternativa> listaAlternativas = new ArrayList<Alternativa>();

            Alternativa alternativa = null;

            List<Opcao> opcoes = new ArrayList<Opcao>();

            Opcao opcao = null;

            pergunta = new Pergunta();

            int optIdx = 1;
                for (Object[] p : listaP){	

                    pergunta.setId((int) p[0]);
                        pergunta.setEnunciado((String) p[1]);
                        pergunta.setTipo(0);

                        if (p[12] != null)
                            pergunta.setIsRequired(Integer.parseInt(String.valueOf(p[12])));
                    else
                            pergunta.setIsRequired(0);

                    if (!((String)p[4]).equals("SELECT")){ //NAO EH SELECT

                                    if (alternativa == null || ((String)p[4]).equals("CHECKBOX") || ((String)p[4]).equals("RADIO")){

                                            alternativa = new Alternativa();
                                    alternativa.setId((int) p[2]);
                                    alternativa.setDescricao((String) p[3]);

                                            controle = new Controle();

                                            controle.setTipoHTML((String) p[4]);

                                            if (p[5] != null) 
                                            controle.setValor((String) p[5]);
                                    else
                                            controle.setValor("");

                                    if (p[6] != null) 
                                            controle.setLimMinimo(Long.parseLong(String.valueOf(p[6])));
                                    else
                                            controle.setLimMinimo(0);

                                    if (p[7] != null)
                                            controle.setLimMaximo(Long.parseLong(String.valueOf(p[7])));
                                    else
                                            controle.setLimMaximo(0);

                                    if (p[8] != null)
                                            controle.setMaxLength(Integer.parseInt(String.valueOf(p[8])));
                                    else
                                            controle.setMaxLength(0);

                                    if (p[13] != null)
                                            controle.setIsRequired(Integer.parseInt(String.valueOf(p[13])));
                                    else
                                            controle.setIsRequired(0);

                                    if (p[14] != null){ //COM RESPOSTA
                                            if (((String)p[4]).equals("CHECKBOX")){
                                                    controle.setValor((String) p[5]);
                                                    if (((String)p[14]).equals((String)p[5])){
                                                            controle.setIsChecked(1);
                                                    }
                                                    else {
                                                            controle.setIsChecked(0);			
                                                    }
                                            }
                                            else if (((String)p[4]).equals("RADIO")){
                                                    controle.setValor((String) p[5]);
                                                    if (((String)p[14]).equals((String)p[5])){
                                                            controle.setIsChecked(1);
                                                    }
                                                    else {
                                                            controle.setIsChecked(0);			
                                                    }
                                                    }
                                                    else {
                                                            controle.setValor((String) p[14]);
                                                            controle.setIsChecked(0);
                                                    }	
                                    }
                                    else { //SEM RESPOSTA
                                            //controle.setValor("");
                                            controle.setIsChecked(0);
                                    }

                                    if (p[15] != null) 
                                            controle.setClasseMascara((String) p[15]);
                                    else
                                            controle.setClasseMascara("");

                                    if (p[16] != null) 
                                            controle.setFormatoMascara((String) p[16]);
                                    else
                                            controle.setFormatoMascara("");

                                    alternativa.setControle(controle);	

                                    listaAlternativas.add(alternativa);

                                    }							

                    }
                    else { //EH SELECT

                            if (alternativa == null) {

                                    alternativa = new Alternativa();
                                    alternativa.setId((int) p[2]);
                                    alternativa.setDescricao((String) p[3]);

                                    controle = new Combobox();

                                    controle.setTipoHTML((String) p[4]);
                                            controle.setValor("");
                                    controle.setLimMinimo(0);
                                            controle.setLimMaximo(0);
                                    controle.setMaxLength(0);

                                    if (p[13] != null)
                                            controle.setIsRequired(Integer.parseInt(String.valueOf(p[13])));
                                    else
                                            controle.setIsRequired(0);

                            }				        	

                            opcao = new Opcao((int)p[0], (int)p[2], (int)p[9], (String)p[10], (String)p[11]);

                            opcoes.add(opcao);

                    }

                }

                if (controle.getTipoHTML().equals("SELECT")){

                    controle.setOpcoes(opcoes);

                    alternativa.setControle(controle);	

                            listaAlternativas.add(alternativa);

                }

                pergunta.setAlternativas(listaAlternativas);

        }

        return pergunta;

    }

    public Pergunta retrieveProximaPergunta(int usuarioId, int ordemId){

        Pergunta pergunta = null;
        //List<Object[]> listaP = null;
        String tipoHTML = "";
        String sql = "";

        try{

            /****************************************************************
            * CHECA SE A PERGUNTA JA FOI RESPONDIDA
            *****************************************************************/
            sql += " SELECT pe.PerguntaENUNCIADOID,"; 
            sql += "        pe.Enunciado,";
            sql += "        pa.AlternativaPerguntaID,"; 
            sql += "        pa.Descricao AS AlternativaDescricao,";
            sql += "        c.CTipoHTML,"; 
            sql += "        c.Valor AS ControleValor,";
            sql += "        c.LimiteMinimo,"; 
            sql += "        c.LimiteMaximo,";
            sql += "        c.MaxLength,";
            sql += "        cbx.OpcaoID,";
            sql += "        cbx.Rotulo,";
            sql += "        cbx.Valor AS ComboboxValor,";
            sql += "        pe.IsRequired AS IsGlobalRequired,";
            sql += "        c.IsRequired,";
            sql += "        ur.Resposta,";
            sql += "        c.ClasseMascara,";
            sql += "        c.FormatoMascara";
            sql += " FROM PerguntaEnunciado pe";
            sql += " INNER JOIN PerguntaAlternativa pa ON pe.PerguntaEnunciadoID = pa.PerguntaID";
            sql += " INNER JOIN CONTROLE c ON c.CPerguntaID = pa.PerguntaID AND c.CAlternativaID = pa.AlternativaPerguntaID";
            sql += " LEFT OUTER JOIN UsuarioResposta ur ON ur.UsuarioID = :usuarioId AND ur.PerguntaID = c.CPerguntaID";
            sql += " LEFT OUTER JOIN COMBOBOXOPCAO cbx ON cbx.OPPerguntaID = c.CPerguntaID AND cbx.OPAlternativaID = c.CAlternativaID";
            sql += " WHERE pe.Ordem = :ordemId";
            sql += " ORDER BY pe.Ordem";

            System.out.println(sql);

            @SuppressWarnings("unchecked")
            List<Object[]> listaP = (List<Object[]>) em.createNativeQuery(sql).setParameter("usuarioId", usuarioId).setParameter("ordemId", ordemId).getResultList();

            if (listaP.size() > 0){

                pergunta = montaPergunta(listaP);

            }
            else {

                /****************************************************************
                * PERGUNTA AINDA NAO RESPONDIDA
                *****************************************************************/

                sql  = " SELECT pe.PerguntaENUNCIADOID,"; 
                sql += " 	pe.Enunciado,";
                sql += " 	pa.AlternativaPerguntaID,"; 
                sql += "        pa.Descricao AS AlternativaDescricao,";
                sql += " 	c.CTipoHTML,"; 
                sql += " 	c.Valor AS ControleValor,";
                sql += " 	c.LimiteMinimo,"; 
                sql += " 	c.LimiteMaximo,";
                sql += " 	c.MaxLength,";
                sql += "        cbx.OpcaoID,";
                sql += "        cbx.Rotulo,";
                sql += "        cbx.Valor AS ComboboxValor,";
                sql += "        pe.IsRequired AS IsGlobalRequired,";
                sql += "        c.IsRequired,";
                sql += "        NULLIF(0, 0) AS Resposta,"; //WORKAROUND PRA USAR COLUNA NULL NO HQL
                sql += "        c.ClasseMascara,";
                sql += "        c.FormatoMascara";
                sql += " FROM PerguntaEnunciado pe";
                sql += " INNER JOIN PerguntaAlternativa pa ON pe.PerguntaEnunciadoID = pa.PerguntaID";
                sql += " INNER JOIN CONTROLE c ON c.CPerguntaID = pa.PerguntaID AND c.CAlternativaID = pa.AlternativaPerguntaID";
                sql += " LEFT OUTER JOIN COMBOBOXOPCAO cbx ON cbx.OPPerguntaID = c.CPerguntaID AND cbx.OPAlternativaID = c.CAlternativaID";
                sql += " WHERE pe.PerguntaEnunciadoID = (";
                sql += "    SELECT pe.PerguntaEnunciadoID";
                sql += " 	  FROM PerguntaEnunciado pe";
                sql += " 	  INNER JOIN PerguntaAlternativa pa ON pe.PerguntaEnunciadoID = pa.PerguntaID";
                sql += " 	  LEFT OUTER JOIN UsuarioResposta ur ON pa.PerguntaID = ur.PerguntaID AND ur.UsuarioId = :usuarioId";
                sql += " 	  WHERE ur.Resposta IS NULL AND ur.DataFim IS NULL";
                sql += "    	AND pe.Visivel = 1";
                sql += " 	  ORDER BY pe.Ordem";
                sql += " 	  LIMIT 1";
                sql += " )";
                sql += " ORDER BY pe.Ordem";

                //@SuppressWarnings("unchecked")
                listaP = (List<Object[]>) em.createNativeQuery(sql).setParameter("usuarioId", usuarioId).getResultList();

                pergunta = montaPergunta(listaP);

            }

        }
        catch(NumberFormatException nfe){
                System.out.println(nfe.getMessage());	
        }
        catch(NoResultException nre){
                System.out.println(nre.getMessage());	
        }

        return pergunta;

    }

}
