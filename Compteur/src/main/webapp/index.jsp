<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Informatique Distribuée Objet Distribuée</title>
        
        <link rel="stylesheet" href="<c:url value="/static/css/foundation.css" />">
        <link rel="stylesheet" href="<c:url value="/static/css/foundation.min.css" />">
        
    	<link rel="stylesheet" href="<c:url value="/static/css/app.css" />">
    
    </head>
    <body>
        <script src="<c:url value="/static/js/jquery-3.1.1.min.js" />"></script>
        <script src="<c:url value="/static/js/vendor/foundation.js" />"></script>
        <script src="<c:url value="/static/js/app.js" />"></script>
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
   		<script type="text/javascript">
   		var COMPTEURS_INIT;
   		
        var LANGUAGE_BY_LOCALE = {
        		"sq,": "Albanais",
        		"sq,AL": "Albanais(Albanie)",
        		"de,": "Allemand",
        		"de,CH": "Allemand(Suisse)",
        		"de,AT": "Allemand(Autriche)",
        		"de,LU": "Allemand(Luxembourg)",
        		"de,DE": "Allemand(Allemagne)",
        		"de,GR": "Allemand(Grèce)",
        		"en,US": "Anglais(Etats-Unis)",
        		"en,SG": "Anglais(Singapour)",
        		"en,MT": "Anglais(Malte)",
        		"en,": "Anglais",
        		"en,PH": "Anglais(Philippines)",
        		"en,NZ": "Anglais(Nouvelle-Zélande)",
        		"en,ZA": "Anglais(Afrique du Sud)",
        		"en,AU": "Anglais(Australie)",
        		"en,IE": "Anglais(Irlande)",
        		"en,CA": "Anglais(Canada)",
        		"en,IN": "Anglais(Inde)",
        		"en,GB": "Anglais(Royaume-Uni)",
        		"ar,AE": "Arabe(Emirats Arabes Unis)",
        		"ar,JO": "Arabe(Jordanie)",
        		"ar,SY": "Arabe(Syrie)",
        		"ar,BH": "Arabe(Bahreïn)",
        		"ar,SA": "Arabe(Arabie Saoudite)",
        		"ar,YE": "Arabe(Yémen)",
        		"ar,EG": "Arabe(Egypte)",
        		"ar,SD": "Arabe(Soudan)",
        		"ar,TN": "Arabe(Tunisie)",
        		"ar,IQ": "Arabe(Irak)",
        		"ar,MA": "Arabe(Maroc)",
        		"ar,QA": "Arabe(Qatar)",
        		"ar,OM": "Arabe(Oman)",
        		"ar,": "Arabe",
        		"ar,KW": "Arabe(Koweit)",
        		"ar,LY": "Arabe(Libye)",
        		"ar,DZ": "Arabe(Algérie)",
        		"ar,LB": "Arabe(Liban)",
        		"be,": "Biélorusse",
        		"be,BY": "Bièlorusse(Biélo-Russie)",
        		"bg,": "Bulgare",
        		"bg,BG": "Bulgare(Bulgarie)",
        		"ca,ES": "Catalan(Espagne)",
        		"ca,": "Catalan",
        		"zh,TW": "Chinois(Taiwan)",
        		"zh,HK": "Chinois(Hong-Kong)",
        		"zh,SG": "Chinois(Singapour)",
        		"zh,CN": "Chinois(Chine)",
        		"zh,": "Chinois",
        		"ko,": "Coréen",
        		"ko,KR": "Coréen(Corée du Sud)",
        		"hr,HR": "Croate(Croatie)",
        		"hr,": "Croate",
        		"da,DK": "Danois(Danemark)",
        		"da,": "Danois",
        		"es,PA": "Espagnol(Panama)",
        		"es,VE": "Espagnol(Vénézuela)",
        		"es,PR": "Espagnol(Porto Rico)",
        		"es,BO": "Espagnol(Bolivie)",
        		"es,AR": "Espagnol(Argentine)",
        		"es,SV": "Espagnol(El Salvador)",
        		"es,": "Espagnol",
        		"es,ES": "Espagnol(Espagne)",
        		"es,CO": "Espagnol(Colombie)",
        		"es,PY": "Espagnol(Paraguay)",
        		"es,EC": "Espagnol(Equateur)",
        		"es,US": "Espagnol(Etats-Unis)",
        		"es,GT": "Espagnol(Guatemala)",
        		"es,MX": "Espagnol(Mexique)",
        		"es,HN": "Espagnol(Honduras)",
        		"es,CL": "Espagnol(Chili)",
        		"es,DO": "Espagnol(République Dominicaine)",
        		"es,CU": "Espagnol(Cuba)",
        		"es,UY": "Espagnol(Uruguay)",
        		"es,CR": "Espagnol(Costa Rica)",
        		"es,NI": "Espagnol(Nicaragua)",
        		"es,PE": "Espagnol(Pérou)",
        		"et,": "Estonien",
        		"et,EE": "Estonien(Estonie)",
        		"fi,FI": "Finnois(Finlande)",
        		"fi,": "Finnois",
        		"fr,BE": "Français(Belgique)",
        		"fr,CH": "Français(Suisse)",
        		"fr,": "Français",
        		"fr,LU": "Français(Luxembourg)",
        		"fr,FR": "Français(France)",
        		"fr,CA": "Français(Canada)",
        		"el,": "Grec",
        		"el,CY": "Grec(Chypre)",
        		"el,GR": "Grec(Grèce)",
        		"hi,IN": "Hindi(Inde)",
        		"hi,": "Hindi",
        		"hu,": "Hongrois",
        		"hu,HU": "Hongrois(Hongrie)",
        		"iw,IL": "Hébreu(IsraÃ«l)",
        		"iw,": "Hébreu",
        		"in,": "Indonésien",
        		"in,ID": "Indonésien(Indonésie)",
        		"ga,": "Irlandais",
        		"ga,IE": "Irlandais(Irlande)",
        		"is,IS": "Islandais(Islande)",
        		"is,": "Islandais",
        		"it,": "Italien",
        		"it,CH": "Italien(Suisse)",
        		"it,IT": "Italien(Italie)",
        		"ja,JP": "Japonais(Japon)",
        		"ja,": "Japonais",
        		"ja,JP": "Japonais(Japon)",
        		"lv,": "Letton",
        		"lv,LV": "Letton(Lettonie)",
        		"lt,": "Lithuanien",
        		"lt,LT": "Lithuanien(Lithuanie)",
        		"mk,": "Macédonien",
        		"mk,MK": "Macédonien(Macédoine)",
        		"ms,MY": "Malais(Malaisie)",
        		"ms,": "Malais",
        		"mt,MT": "Maltais(Malte)",
        		"mt,": "Maltais",
        		"no,NO": "Norvégien(Norvège)",
        		"no,NO": "Norvégien(Norvège)",
        		"no,": "Norvégien",
        		"nl,": "Néerlandais",
        		"nl,NL": "Néerlandais(Pays-Bas)",
        		"nl,BE": "Néerlandais(Belgique)",
        		"pl,PL": "Polonais(Pologne)",
        		"pl,": "Polonais",
        		"pt,": "Portugais",
        		"pt,BR": "Portugais(Brésil)",
        		"pt,PT": "Portugais(Portugal)",
        		"ro,RO": "Roumain(Roumanie)",
        		"ro,": "Roumain",
        		"ru,RU": "Russe(Russie)",
        		"ru,": "Russe",
        		"sr,ME": "Serbe(Monténégro)",
        		"sr,BA": "Serbe(Bosnie-Herzégovine)",
        		"sr,CS": "Serbe(Serbie et Monténégro)",
        		"sr,BA": "Serbe(Bosnie-Herzégovine)",
        		"sr,ME": "Serbe(Monténégro)",
        		"sr,": "Serbe",
        		"sr,RS": "Serbe(Serbie)",
        		"sr,": "Serbe",
        		"sr,RS": "Serbe(Serbie)",
        		"sk,": "Slovaque",
        		"sk,SK": "Slovaque(Slovaquie)",
        		"sl,": "Slovène",
        		"sl,SI": "Slovène(Slovénie)",
        		"sv,SE": "Suédois(Suède)",
        		"sv,": "Suédois",
        		"cs,": "Tchèque",
        		"cs,CZ": "Tchèque(République Tchèque)",
        		"th,TH": "Thaï(Thaïlande)",
        		"th,": "Thaï",
        		"th,TH": "Thaï(Thaïlande)",
        		"tr,": "Turc",
        		"tr,TR": "Turc(Turquie)",
        		"uk,": "Ukrainien",
        		"uk,UA": "Ukrainien(Ukraine)",
        		"vi,VN": "Vietnamien(Vietnam)",
        		"vi,": "Vietnamien"
        	}        
        </script>
        <script type="text/javascript">
            // -------------- 1er web service (heure courante)
            var wsUri = "ws://localhost:8080/Compteur/getTime";
            /*
            function init() {
            }*/
            function getTime() {
                websocket = new WebSocket(wsUri);
                
                websocket.onopen = function(evt)  {
                    writeToScreen("Connected to Endpoint!");
                    doSend("start");
                };
                websocket.onmessage = function(evt){
                    writeToScreen(evt.data);
                };
                websocket.onerror = function(evt) {
                    writeToScreen('ERROR: ' + evt.data);
                };
                
            }
            function doSend(message) {
                writeToScreen("Message Sent: " + message);
                websocket.send(message);
                //websocket.close();
            }
            function writeToScreen(message) {
                var output = document.getElementById("time");
                var time = message.split(" ") ;
               	output.innerHTML = time[2]+"/"+time[1]+"/"+time[0]+", "+time[3]+":"+time[4]+":"+time[5];
            }
           	//window.addEventListener("load", init, false);
           
           	//--------------- 2eme web Service (mise a jour des compteurs)
           	
           	var wsUri2 = "ws://localhost:8080/Compteur/majTime";
           	function majTime() {
                websocket2 = new WebSocket(wsUri2);
                websocket2.onopen = function(evt)  {
                    writeToScreen("Connected to Endpoint!");
                    console.log(COMPTEURS_INIT);
                    doSendMaj(COMPTEURS_INIT);
                };
                websocket2.onmessage = function(evt){
                    writeToScreenMaj(evt.data);
                };
                websocket2.onerror = function(evt) {
                	writeToScreenMaj('ERROR: ' + evt.data);
                };
            }
            function doSendMaj(message) {
            	//writeToScreenMaj("Message Sent: " + message);
                websocket2.send(message);
                //websocket.close();
            }
            function writeToScreenMaj(message) {
                var time = message.split(" ") ;
               	//output.innerHTML = time[2]+"/"+time[1]+"/"+time[0]+", "+time[3]+":"+time[4]+":"+time[5];
				var json = JSON.parse(message);

				for (var cpt in json) {
	                var output = document.getElementById("fin"+cpt);
	                if (output !== null) {						
						output.innerHTML = json[cpt].end;
	                }

	                //var time = compteurList[cpt].fin.split(" ") ;
	                //var fin = time[2]+"/"+time[1]+"/"+time[0]+", "+time[3]+":"+time[4]+":"+time[5];
				}
            }
            
            //---------------------------------------------------------------------------
            function getCompteurs() {
            	var uri = "http://localhost:8080/Compteur/getCompteurs";
    	    	var compteurList;
    			
    			$.ajax({
    			  url: uri,
    			  dataType: 'json',
    			  async: false,		
    			  type: 'GET',
    			  success: function(data) {
    				compteurList = data;
					var compteurListToString = "[";
					
    				var tableRef = document.getElementById("compteurs").getElementsByTagName('tbody')[0];
    				tableRef.innerHTML = "";
    				
    				for (var cpt in compteurList) {
        				var tr = tableRef.insertRow(tableRef.rows.length);
        				var tdNom = tr.insertCell(0);
        				var tdCompt = tr.insertCell(1);
        				var tdSupprBtn = tr.insertCell(2);
        				
    	                var time = compteurList[cpt].fin.split(" ") ;
    	                var fin = time[2]+"/"+time[1]+"/"+time[0]+", "+time[3]+":"+time[4]+":"+time[5];
    					//output.innerHTML += "<th>"+compteurList[cpt].nom+" ("+compteurList[cpt].locale+") </th><th id=\"fin"+cpt+"\">"+fin+"</th>" ;    			
    					
    					var newText = document.createTextNode(compteurList[cpt].nom+" ("+compteurList[cpt].locale+")");
    					tdNom.appendChild(newText);
    					
    					newText = document.createTextNode(fin);
    					tdCompt.appendChild(newText);
    					tdCompt.setAttribute("id", "fin"+cpt, 0);
    					
    					//newText = document.createElement("<input class=\"alert button\" onclick=\"supprimeCompteur(this)\" value=\""+compteurList[cpt].nom+"\" type=\"button\">Delete</input>");

    					button = document.createElement("button");
    					tdSupprBtn.appendChild(button);
    					button.setAttribute("class", "alert button", 0);
    					button.setAttribute("onclick", "supprimeCompteur(this)", 0);
    					button.setAttribute("value", compteurList[cpt].nom, 0);
    					button.setAttribute("type", "button", 0);
    					button.innerHTML = "Supprimer";

						
    					//button.html("Supprimer");
    					
    					compteurListToString+= "{\"nom\"=\""+compteurList[cpt].nom+"\", \"locale\"=\""+compteurList[cpt].locale+"\", \"endY\"=\""+time[0]+"\", \"endM\"=\""+time[1]+"\", \"endJ\"=\""+time[2]+"\", \"endH\"=\""+time[3]+"\", \"endMin\"=\""+time[4]+"\", \"endS\"=\""+time[5]+"\"}" ;

    					if(cpt < compteurList.length)
    						compteurListToString+=",";
    				}
    				compteurListToString+="]";
    				COMPTEURS_INIT = compteurListToString ;

    			  },
    			  error: function (jqXHR) {
    			  	throw new Error(jqXHR.status + ". " + jqXHR.responseText);
    			  }
    			});
    			
            }
            //---------------------------------------------------------------------------
            function nouveauCompteur() {
            	         	
            	var nom = $('#nom').val();
        		var locale = $('#locale').val();
        		var datefin = $('#datefin').val();
        		var finHeure = $('#finHeure').val();
        		var finMinute = $('#finMinute').val();
        		var finSeconde = $('#finSeconde').val();
        		
        		if(nom && locale && datefin && finHeure && finMinute && finSeconde)
        		{
        			var uri = "http://localhost:8080/Compteur/addCompteur/"+nom+"/"+locale+"/"+datefin+"/"+finHeure+"/"+finMinute+"/"+finSeconde;
            		
        			$.ajax({
        			  url: uri,
        			  dataType: 'json',
        			  async: false,		
        			  type: 'PUT',
        			  success: function(data) {
        			  },
        			  error: function(xhr, status, error) {
        				  var err = "(" + xhr.responseText + ")";
        			  }
        			});

    				getCompteurs(); // mise a jour des compteurs
    				
    				$("#error").css("display", "none");  
        		}
        		else
        		{
        			$("#error").css("display", "block");  
        		}
            }
            //---------------------------------------------------------------------------
			function supprimeCompteur(bouton) {
            	         	
            	var nom = bouton.value; 
        		
        		var uri = "http://localhost:8080/Compteur/removeCompteur/"+nom;
        		
    			$.ajax({
    			  url: uri,
    			  dataType: 'json',
    			  async: false,		
    			  type: 'DELETE',
    			  success: function(data) {
    			  },
    			  error: function(xhr, status, error) {
    				  var err = "(" + xhr.responseText + ")";
    			  }
    			});

				getCompteurs(); // mise a jour des compteurs
            }
            
        </script>
        <h1 style="text-align: center;">Web App Lab #1 - Brahimi Hicham</h1>
        <h2 id="time" style="text-align: center;">Bonjour</h2>
        <br>
        <form action="">
		<div id="error" data-abide-error role="alert" class="alert callout" style="display: none;">
			<p><i class="fi-alert"></i>Veuillez remplir tous les champs !</p>
		</div>
          <div class="row primary">
        
          <div class="large-3 medium-3 small-3 columns">
            <div class="">
              <label>Nom du Compteur</label>
              <input type="text" id="nom" name="nom" placeholder="Inscrivez un nom existant= Modifier">
            </div>
          </div>
          <div class="large-2 medium-2 small-2 columns">
            <div class="">
           		<label>Langue</label>
           		<select id="locale" name="locale">
         			<script>
         			for( var l in LANGUAGE_BY_LOCALE ) {
         				document.write("<option value=\""+l+"\">"+LANGUAGE_BY_LOCALE[l]+"</option>\n");
         			}
         			</script>
         		</select>
            </div>
          </div>
          <div class="large-6 medium-6 small-6 columns collapse">
          <label>Date de Fin</label>
           <div class="large-6 medium-6 small-6 columns">
		   	<div>
		   		<input type="text" id="datefin" name="datefin" />
		    </div>
		   </div>
		   <div class="large-2 medium-2 small-2 columns">
		   	<div>
		     	<select id="finHeure" name="finHeure">
                <script>
           		for(var i = 0; i < 24; i++)  {
           			if (i < 10)
           				document.write("<option value=\""+i+"\">0"+i+"</option>\n");
           			else
           				document.write("<option value=\""+i+"\">"+i+"</option>\n");
           		}
           		</script>
				</select>
		    </div>
		   </div>
		   <div class="large-2 medium-2 small-2 columns">
		    <div>
		        <select id="finMinute" name="finMinute">
                <script>
           		for(var i = 0; i < 60; i++)  {
           			if (i < 10)
           				document.write("<option value=\""+i+"\">0"+i+"</option>\n");
           			else
           				document.write("<option value=\""+i+"\">"+i+"</option>\n");
           		}
           		</script>
				</select>
		    </div>
		   </div>
		   <div class="large-2 medium-2 small-2 columns">
		   	<div>
		    	<select id="finSeconde" name="finSeconde">
                <script>
           		for(var i = 0; i < 60; i++)  {
           			if (i < 10)
           				document.write("<option value=\""+i+"\">0"+i+"</option>\n");
           			else
           				document.write("<option value=\""+i+"\">"+i+"</option>\n");

           		}
           		</script>
				</select>
		    </div>
		   </div>
		   </div>
		   <div class="large-1 medium-1 small-1 columns">
		   	<div class="">
		   	    <label>&nbsp;</label>
		    	<input class="success button" onclick="nouveauCompteur()" value="OK" type="button" />
		    </div>
		   </div>
		 </div>
       	 </form>
       	 
        <table id="compteurs">
    	<thead>
    		<tr><th>Compteur</th><th>Temps restant</th><th>Suppression</th></tr>
    	</thead>
    	<tbody>
    	
    	</tbody>
    	</table>	
    	
    	<br />
    	<br />
    	<hr />
    	<h3 style="text-align: center;">Technologies utilisées</h3>
    	
		<table class="hover">
    	<tbody>
    	   <tr>
    	   	<th><h4>CLIENT</h4></th>
    	   	<th>
    	   		<ul class="no-bullet">
					<li><h5>WebService pour la mise à jour</h5></li>
					<li><h5>Ajax pour la mise en page dynamique</h5></li>
					<li><h5>Foundation pour le style</h5></li>
				 </ul>
    	   	</th>
    	   </tr>
    	   <tr>
    	   	<th><h4>SERVEUR</h4></th>
    	   	<th>
    	   		<ul class="no-bullet">
    	   			<li><h5>Spring comme framework J2EE</h5></li>
					<li><h5>Joda pour les calculs de temps</h5></li>
					<li><h5>Websocket pour la mise à jour</h5></li>
					<li><h5>Json pour lire les communications</h5></li>
				 </ul>
    	   	</th>
    	   	</tr>
    		<tr>
    	   	<th><h4>TESTS</h4></th>
    	   	<th>
    	   		<ul class="no-bullet">
    	   			<li><h5>Junit la base</h5></li>
					<li><h5>Mockito pour les tests sur servlet</h5></li>
				 </ul>
    	   	</th>
    	   </tr>
    	</tbody>
    	</table>	
    	
    	
        <script>   
          $(function() {
               $( "#datefin" ).datepicker();   
          });
          
          $("#datefin").keypress(function(e) {
              e.preventDefault();
          });
        </script>
        <script>
        function start(){
        	getCompteurs();
        	getTime();
        	majTime();
        }
        $(document).ready(start());
        </script>
        
</body>
</html>