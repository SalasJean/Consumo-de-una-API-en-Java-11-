package servicios;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import modelos.User;
import util.JsonParser;

public class UserApiClient {
	//aqui va la logica para consumir la api recuerda que es el service
	//ahora pasamops a la creacion de la api 
	//ahora aqui vamos a definir la logica de negocio para mostrar los datos creados si? recuerda
	private final HttpClient client;
	private final JsonParser jsonParser;
	private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";
	
	public UserApiClient(HttpClient client, JsonParser jsonParser) {
		super();
		this.client = client;
		this.jsonParser = jsonParser;
	}
	//una vez unicializado ahora si vamos a crear la logica prmeor para obtener todos los usuarios generalmente funciona de esta forma si? recuerdalo
	//como java es un lenguaje funcional osea orientado a funciones pues creamos aqui un funcion que nos permita realizar esa logica
	public List<User> obtenerTodos() throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL))
				.GET()
				.build();
		System.out.println("🔍 Consultando todos los usuarios...");
		//ahora una vez consultado osea una vez habiendo metido el request ahora respondemos al cliente
		//recuerda que esta es logica de respiesta mas no de entrada
		HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
		//ahora una vez creado su logica de respuesta ahora creamos otra logica que valide si esa respuesta que creamos nos devuelve una buena respuesta para continuar
		if(response.statusCode() == 200) {
			return jsonParser.parsearListaUsers((String) response.body());
			//recuerda que aqui estamos parseando osea estamos cambiando de tipo de dato de string a object recuerda si?
		}else {
            throw new RuntimeException("Error " + response.statusCode());
        }
	}
	//ahora vamos a crear la logia para obtener el usuario por su id recuerda si?
	//recuerda que caundo creamos un metodo con parametros generlamete el parametro dever ser ol objeto a buscar dentro del modelo o entidad si? recuerdalo siempre
	public User obtenerPorId(int id) throws Exception {
		//ahora como la busqueda ya no es general mas bien es especifica necesitamos declarar de otra forma la url
		String url = BASE_URL + "/" + id;
		//ahora chapamos lo que nos da el cliente 
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.GET()
				.build();
		
		System.out.println("🔍 Consultando usuario " + id + "...");
		//ahora vamos a crear la logica para responder al cliente una vez econtrado su id
		//recuerda que esta logica se esta envolviendo en una lista porque a veces existe muchos datos con el mismo nombre pero direfrnte id recuerdalo
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); //esto nos devuelve un bodyhandlers de estring
		//ahora creamos una logica para ver si cumple con estos datos 
		if (response.statusCode() == 200) {
            return jsonParser.parsearUser(response.body());
        } else {
            throw new RuntimeException("Error " + response.statusCode() + ": Usuario no encontrado");
        }
	}
	 // POST - Crear nuevo usuario
    public User crearUsuario(User nuevoUser) throws Exception {
        String json = jsonParser.convertirAJson(nuevoUser);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        
        System.out.println("📤 Creando nuevo usuario...");
        
        HttpResponse<String> response = client.send(request, 
            HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 201) {
            return jsonParser.parsearUser(response.body());
        } else {
            throw new RuntimeException("Error al crear: " + response.statusCode());
        }
    }
    
    // PUT - Actualizar usuario
    public User actualizarUsuario(int id, User userActualizado) throws Exception {
        String url = BASE_URL + "/" + id;
        String json = jsonParser.convertirAJson(userActualizado);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        
        System.out.println("📝 Actualizando usuario " + id + "...");
        
        HttpResponse<String> response = client.send(request, 
            HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200) {
            return jsonParser.parsearUser(response.body());
        } else {
            throw new RuntimeException("Error al actualizar: " + response.statusCode());
        }
    }
    
    // DELETE - Eliminar usuario
    public boolean eliminarUsuario(int id) throws Exception {
        String url = BASE_URL + "/" + id;
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        
        System.out.println("🗑️ Eliminando usuario " + id + "...");
        
        HttpResponse<String> response = client.send(request, 
            HttpResponse.BodyHandlers.ofString());
        
        return response.statusCode() == 200 || response.statusCode() == 204;
    }
	

}
