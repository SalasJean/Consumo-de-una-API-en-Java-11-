package util;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import modelos.User;

public class JsonParser {
	//en esta clase va toda la logicca para manejar las conversiones a formato Json recuerda
	//esta clase nos permitira crear metod0s que nos permitann serializar las entradas si? esto hara que java no tenga problemas para recibir  datos
	private final Gson gson;
    
    public JsonParser() {
        this.gson = new Gson();
    }
    
    //JSON -> user individual esto se hace para el manejo de objetos anidados de manera automatica
    public User parsearUser(String json) {
    	return gson.fromJson(json, User.class);
    }
    //ahora este parsea una lista de toods los usuarios
 // JSON -> Lista de Users
   //recuerda que como es una lista devemos envolverlo igual de esa forma si? recuerdalo mi bro
    public List<User> parsearListaUsers(String json){
    	Type userListType = new TypeToken<List<User>>() {}.getType();
    	return  gson.fromJson(json, userListType);
    }
    
 // User -> JSON
    //objetivo convertir un solo user  a json recuerda si?
    public String convertirAJson(User user) {
    	return gson.toJson(user);
    }
    //aqui el parametro es el propio modelo recuerda si?
	 //ahora programammos un nuevo metodo que nos devuelva una lista de usuarios pero ya parseado si?
 // Lista de Users -> JSON
    public String convertirAJson(List<User> users) {
    	return gson.toJson(users);
    }
	

}
