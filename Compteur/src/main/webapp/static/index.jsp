<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
    </head>
    <body>
        <meta charset="utf-8">
        <title>Your First WebSocket!</title>
        <script src="js/jquery-3.1.1.min.js"></script>
        
        <script language="javascript" type="text/javascript">
            
            var wsUri = "ws://localhost:8080/Compteur/echo";
            
            function init() {
            }
            function send_message() {
                websocket = new WebSocket(wsUri);
                websocket.onopen = function(evt) {
                    onOpen(evt)
                };
                websocket.onmessage = function(evt) {
                    onMessage(evt)
                };
                websocket.onerror = function(evt) {
                    onError(evt)
                };
            }
            function onOpen(evt) {
                writeToScreen("Connected to Endpoint!");
                doSend(textID.value);
            }
            function onMessage(evt) {
                writeToScreen("Message Received: " + evt.data);
            }
            function onError(evt) {
                writeToScreen('ERROR: ' + evt.data);
            }
            function doSend(message) {
                writeToScreen("Message Sent: " + message);
                websocket.send(message);
                //websocket.close();
            }
            function writeToScreen(message) {
                var output = document.getElementById("output");
                output.innerHTML = message;
            }
            window.addEventListener("load", init, false);
            
            //---------------------------------------------------------------------------
            function test() {
            	var uri = "http://localhost:8080/Compteur/test/24";
    	    	var hotelList;
    	
    			$.ajax({
    			  url: uri,
    			  dataType: 'json',
    			  async: false,		
    			  type: 'PUT',
    			  success: function(data) {
    				  hotelList = data;
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
        		var fin = $('#fin').val();
        		
        		var uri = "http://localhost:8080/Compteur/addCompteur/"+nom+"/"+locale+"/"+fin;
        		
    			$.ajax({
    			  url: uri,
    			  dataType: 'json',
    			  async: false,		
    			  type: 'PUT',
    			  success: function(data) {

    			  },
    			  error: function(xhr, status, error) {
    				  var err = "(" + xhr.responseText + ")";
    				  console.log(err.Message);
    			  }
    			});
            }
        </script>
        <h1 style="text-align: center;">Hello World WebSocket Client</h2>
        <br>
        <div style="text-align: center;">
            <form action="">
                <input onclick="send_message()" value="Send" type="button">
                <input onclick="test()" value="TEST" type="button">
                
                <input id="textID" name="message" value="Hello WebSocket!" type="text"><br>
            </form>          	
 		</div>
 		 <div style="text-align: center;">
            <form action="">
           		Nom du Compteur: <input onclick="" type="text" id="nom" name="nom">
           		Langue: <input onclick="" type="text" id="locale" name="locale">
                Fin: <input onclick="" type="text" id="fin" name="fin">
                <input onclick="nouveauCompteur()" value="OK" type="button">
        	</form>           	
 		</div>
 		
        <div id="output"></div>
        
        
</body>
</html>