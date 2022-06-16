<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<html lang="pt-br">
<style>

.fundoextcinza {
  width: 100%;
  height:100%;
  /* Firefox */
  display: -moz-box;
  -moz-box-pack: center;
  -moz-box-align: center;
  /* Safari and Chrome */
  display: -webkit-box;
  -webkit-box-pack: center;
  -webkit-box-align: center;
  /* W3C */
  display: box;
  box-pack: center;
  box-align: center;
  background-color:grey;
}

.fundoextbranco {
  width: 95%;
  height:95%;
  /* Firefox */
  display: -moz-box;
  -moz-box-pack: center;
  -moz-box-align: center;
  /* Safari and Chrome */
  display: -webkit-box;
  -webkit-box-pack: center;
  -webkit-box-align: center;
  /* W3C */
  display: box;
  box-pack: center;
  box-align: center;
  background-color:white;
}

.areadeescrita {
	
	width:98%;
	height:90%;
	background-color:white;
	/* Firefox */
	display: -moz-box;
	-moz-box-pack: center;
	-moz-box-align: center;
	/* Safari and Chrome */
	display: -webkit-box;
	-webkit-box-pack: center;
	-webkit-box-align: center;
	/* W3C */
	display: box;
	box-pack: center;
	box-align: center;
  	
}

.areadeescritareal {
	border: 0px solid black;
	width:90%;
	height:90%;
	background-color:white;
	/* Firefox */
	display: -moz-box;
	-moz-box-pack: center;
	-moz-box-align: center;
	/* Safari and Chrome */
	display: -webkit-box;
	-webkit-box-pack: center;
	-webkit-box-align: center;
	/* W3C */
	display: box;
	box-pack: center;
	box-align: center;
  	
}
</style>	
<head>
	<title>Curriculum Vitae</title>
</head>
<body style="font-family:Verdana;font-size:1.5em;width:100%;height:3000px">
<div id="container" class="fundoextcinza">
	<div class="fundoextbranco">
		<div class="areadeescrita">
			<div class="areadeescritareal">
				<div style="position:absolute;float:left;top:120px;width:1200px">
					<c:choose>
					<c:when test="${empty cv}">
						<span style="font-family: tahoma;font-size:1em;color: orange">
						CURRICULO NAO DISPONIVEL
						</span>					
					</c:when>
					<c:otherwise>${cv.r}
						<div style="position:absolute;float:left;width:180px;background-color:green;">
							<img src="http://www.dunedin.com.br/Picture.png" width="180" height="240"/>
						</div>
						<div style="position:absolute;float:left;margin-left:230px;background-color:white;width:calc(100%-360px)">
							<div style="float:left;font-size:2em;height:100px">${cv.r1}</div>
							<div style="float:left;width:100%">${cv.r3}, ${cv.r4}, ${cv.r5}</div>
							<div style="float:left;width:100%">Endereço - ${cv.r6}, ${cv.r7}</div>
							<div style="float:left;width:100%">Bairro ${cv.r8} - ${cv.r9} - ${cv.r10}</div>
							<div style="float:left;width:100%">Telefone: ${cv.r11} / Email: ${cv.r12}</div>
						</div>
					</div>				
					<div style="position:absolute;float:left;top:500px;width:1200px;background-color:white">
						<div style="float:left;width:100%">01 - OBJETIVO</div>
						<div style="float:left;margin:5px solid black;height:1px;width:1200px;background-color:black"></div>
						<div style="float:left;margin-top:15px;width:100%">${cv.r13}</div>
					</div>
					<div style="position:absolute;float:left;top:1000px;width:1200px;background-color:white">
						<div style="float:left;width:100%">02 - FORMAÇÃO</div>
						<div style="float:left;margin:5px solid black;height:1px;width:1200px;background-color:black"></div>
						<div style="float:left;margin-top:15px;width:100%">
							<ul>
								<li style="height:50px">${cv.r15} - ${cv.r16} - ${cv.r17} a ${cv.r18}</li>
								<li style="height:50px">${cv.r19} - ${cv.r20} - ${cv.r21} a ${cv.r22}</li>
							</ul>	
						</div>
					</div>
					<div style="position:absolute;float:left;top:1500px;width:1200px;background-color:white">
						<div style="float:left;width:100%">03 - EXPERIÊNCIAS PROFISSIONAIS</div>
						<div style="float:left;margin:5px solid black;height:1px;width:1200px;background-color:black"></div>
						<div style="float:left;margin-top:15px;width:100%">
						<div style="float:left;margin-top:15px;width:100%">
							<ul>
								<li style="height:125px">
									<b>${cv.r27} - (${cv.r30} a ${cv.r31})</b></br>
									Cargo: ${cv.r28}</br>
									Principais atribuições: ${cv.r29}
								</li>
								<li style="height:125px">
									<b>${cv.r32} - (${cv.r35} a ${cv.r36})</b>	</br>							
									Cargo: ${cv.r33}</br>
									Principais atribuições: ${cv.r34}
								</li>
								<li style="height:125px">
									<b>${cv.r37} - (${cv.r40} a ${cv.r41})</b>	</br>							
									Cargo: ${cv.r38}</br>
									Principais atribuições: ${cv.r39}
								</li>
							</ul>	
						</div>
						</div>
					</div>
					<div style="position:absolute;float:left;top:2000px;width:1200px;background-color:white">
						<div style="float:left;width:100%">04 - APERFEIÇOAMENTO E ATIVIDADES COMPLEMENTARES</div>
						<div style="float:left;margin:5px solid black;height:1px;width:1200px;background-color:black"></div>
						<div style="float:left;margin-top:15px;width:100%">
							<ul>
								<li style="height:50px">
									${cv.r42} - ${cv.r43} (${cv.r44} a ${cv.r45}) 							
								</li>
								<li style="height:50px">
									${cv.r46} - ${cv.r47} (${cv.r48} a ${cv.r49})
								</li>
								<li style="height:50px">
									${cv.r50} - ${cv.r51} (${cv.r52} a ${cv.r53})
								</li>
							</ul>
						</div>
					</div>
					<div style="position:absolute;float:left;top:2500px;width:1200px;background-color:white">
						<div style="float:left;width:100%">05 - IDIOMAS</div>
						<div style="float:left;margin:5px solid black;height:1px;width:1200px;background-color:black"></div>
						<div style="float:left;margin-top:15px;width:100%">
							<ul>
								<li style="height:50px">
									${cv.r54} = ${cv.r55}								
								</li>
								<li style="height:50px">
									${cv.r56} = ${cv.r57}
								</li>
							</ul>
						</div>
					</div>
					<!--
					<div style="position:absolute;float:left;top:3000px;width:1200px;background-color:white">
						<div style="float:left;width:100%">06 - OBJETIVO</div>
						<div style="float:left;margin:5px solid black;height:1px;width:1200px;background-color:black"></div>
						<div style="float:left;margin-top:15px;width:100%">Meus objetivos são desenvolver software na plataforma Oracle</div>
					</div>
					-->
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>
</body>
</html>