import java.lang.StringIndexOutOfBoundsException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Encontrar_Vagas {
         
    public static void main(String[] args) {
    	
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;

        HttpResponse<String> response = null;

        String link = "https://programathor.com.br/jobs/";
        
        System.out.println("Encontre sua vaga aqui!\n");

        request = HttpRequest.newBuilder().uri(URI.create(link)).GET().build();

        try {

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
        } catch (IOException | InterruptedException e) {
        	e.printStackTrace();
        }
        
        String html = response.body().toString();
        
        int posInicEmpresa = 0, posInicVaga = 0, posInicTipoVaga = 0, posInicSalario = 0, posInicLink = 0;
        
    	for (int i = 0 ; i < 15 ; i++) {
            posInicEmpresa = html.indexOf("fa-briefcase'></i>", posInicEmpresa + 1);
            int posFinalEmpresa = html.indexOf("</s",posInicEmpresa);
            String empresa = html.substring(posInicEmpresa,posFinalEmpresa);
            System.out.println("Empresa: "+empresa.replace("fa-briefcase'></i>", ""));
            
            posInicVaga = html.indexOf("text-24 line-height-30\">", posInicVaga + 1);
            int posFinalVaga = html.indexOf("<",posInicVaga);
            String vaga = html.substring(posInicVaga,posFinalVaga);
            System.out.println(vaga.replace("text-24 line-height-30\">", "Vaga: "));
            
            posInicTipoVaga = html.indexOf("fa-map-marker-alt'></i>", posInicTipoVaga + 1);
            int posFinalTipoVaga = html.indexOf("</span>",posInicTipoVaga);
            String tipovaga = html.substring(posInicTipoVaga,posFinalTipoVaga);
            System.out.println(tipovaga.replace("fa-map-marker-alt'></i>", "Local de Trabalho: "));
            
            try {
	            posInicSalario = html.indexOf("fa-money-bill-alt'></i>", posInicSalario + 1);
	            int posFinalSalario = html.indexOf("</span>",posInicSalario);
	            String salario = html.substring(posInicSalario,posFinalSalario);
	            System.out.println(salario.replace("fa-money-bill-alt'></i>", "Salário: "));
            } catch (StringIndexOutOfBoundsException err) {
            	System.out.println("Salário: Não Informado.");
            }
	        
            posInicLink = html.indexOf("<a href=\"/jobs/", posInicLink + 1);
            int posFinalLink = html.indexOf("\"",posInicLink + 10);
            String linkEmpresa = html.substring(posInicLink,posFinalLink);
            System.out.println("Link: "+linkEmpresa.replace("<a href=\"", "https://programathor.com.br"));
            System.out.println();
    	}
    }
}