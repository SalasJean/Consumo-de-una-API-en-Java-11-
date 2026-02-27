package main;
import modelos.User;
import servicios.UserApiClient;
import util.JsonParser;
import util.FileManager;
import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;
public class Main {
	
	private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final JsonParser jsonParser = new JsonParser();
    private static final UserApiClient api = new UserApiClient(httpClient, jsonParser);
    private static final FileManager fileManager = new FileManager();
    private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		//esta clase representa el punto de entrada de la app es lo mas parecido a controller en el patron mvc
		 System.out.println("""
		            ╔══════════════════════════════════════╗
		            ║   CLIENTE API USUARIOS - JAVA PURO   ║
		            ║   (Con objetos anidados)             ║
		            ╚══════════════════════════════════════╝
		            """);
		 
		 while (true) {
	            mostrarMenu();
	            int opcion = leerOpcion();
	            
	            try {
	                procesarOpcion(opcion);
	            } catch (Exception e) {
	                System.out.println("❌ Error: " + e.getMessage());
	            }
	            
	            System.out.println("\nPresiona Enter para continuar...");
	            scanner.nextLine();
	        }
		 
	}
	//ahora si aqui definimos los metodos para la ejecucion
	public static void mostrarMenu() {
		//aqui ponemos static void porque no devuelve nada recuerda
		 System.out.println("\n📋 MENÚ PRINCIPAL");
	        System.out.println("1. Ver todos los usuarios");
	        System.out.println("2. Ver usuario por ID");
	        System.out.println("3. Crear nuevo usuario");
	        System.out.println("4. Actualizar usuario");
	        System.out.println("5. Eliminar usuario");
	        System.out.println("6. Guardar usuarios en archivo");
	        System.out.println("7. Ver archivos guardados");
	        System.out.println("8. Salir");
	        System.out.print("Elige una opción: ");
	}
	//investigar porque definimos private aqui? mm? interesante
	private static int leerOpcion() {
		try {
			return Integer.parseInt(scanner.nextLine());//cual es la diferencia de un nextLine y de un next nada mas? osea del string?
		}catch(NumberFormatException e) {//recuerda que este tipo de error es numberformatexception recuerdalo si?
			return 0;
		}
	}
	
	//ahora creamos otro metodo que nos permita procesar esta opcion
	private static void procesarOpcion(int opcion) throws Exception {
        switch (opcion) {
            case 1 -> verTodosUsuarios();
            case 2 -> verUsuarioPorId();
            case 3 -> crearUsuario();
            case 4 -> actualizarUsuario();
            case 5 -> eliminarUsuario();
            case 6 -> guardarUsuarios();
            case 7 -> verArchivos();
            case 8 -> {
                System.out.println("👋 ¡Hasta luego!");
                System.exit(0);
            }
            default -> System.out.println("❌ Opción no válida");
        }
	}
	//ahora creamos otro metodo que nos permita ver o listar todos los usuarios si?
	
	private static void verTodosUsuarios() throws Exception {
        System.out.println("\n📚 Obteniendo todos los usuarios...");
        List<User> usuarios = api.obtenerTodos();
        
        System.out.println("\n👥 Total de usuarios: " + usuarios.size());
        System.out.println("=".repeat(50));
        
        usuarios.forEach(user -> {
            System.out.println(user);
            System.out.println("-".repeat(40));
        });
    }
	//recuerda que java es un lenguaje funcional por lo tanto necesitamos trabajr en base a ese paradigma siempre si? recuerda muchachon
	private static void verUsuarioPorId() throws Exception {
        System.out.print("Ingresa el ID del usuario: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        User user = api.obtenerPorId(id);
        System.out.println("\n✅ Usuario encontrado:");
        System.out.println(user);
    }
    
	private static void crearUsuario() throws Exception {
	    System.out.println("\n📝 Ingresa los datos del nuevo usuario:");
	    
	    // Crear usuario con constructor vacío
	    User nuevoUser = new User();
	    
	    // Campos obligatorios
	    System.out.print("Nombre: ");
	    nuevoUser.setName(scanner.nextLine());
	    
	    System.out.print("Email: ");
	    nuevoUser.setEmail(scanner.nextLine());
	    
	    // Campos opcionales
	    System.out.print("Username (opcional): ");
	    String username = scanner.nextLine();
	    if (!username.isEmpty()) nuevoUser.setUsername(username);
	    
	    System.out.print("Teléfono (opcional): ");
	    String phone = scanner.nextLine();
	    if (!phone.isEmpty()) nuevoUser.setPhone(phone);
	    
	    System.out.print("Website (opcional): ");
	    String website = scanner.nextLine();
	    if (!website.isEmpty()) nuevoUser.setWebsite(website);
	    
	    // Enviar a la API (los objetos anidados vienen null)
	    User userCreado = api.crearUsuario(nuevoUser);
	    
	    System.out.println("\n✅ Usuario creado exitosamente (ID asignado: " + userCreado.getId() + ")");
	    System.out.println("Nombre: " + userCreado.getName());
	    System.out.println("Email: " + userCreado.getEmail());
	}
    
    private static void actualizarUsuario() throws Exception {
        System.out.print("ID del usuario a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        User userExistente = api.obtenerPorId(id);
        System.out.println("\n📌 Usuario actual:");
        System.out.println(userExistente);
        
        System.out.println("\n✏️ Ingresa los nuevos datos (Enter para mantener actual):");
        
        System.out.print("Nombre [" + userExistente.getName() + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) userExistente.setName(nombre);
        
        System.out.print("Email [" + userExistente.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) userExistente.setEmail(email);
        
        User resultado = api.actualizarUsuario(id, userExistente);
        System.out.println("\n✅ Usuario actualizado:");
        System.out.println(resultado);
    }
    
    private static void eliminarUsuario() throws Exception {
        System.out.print("ID del usuario a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        User userExistente = api.obtenerPorId(id);
        System.out.println("\n⚠️ Usuario a eliminar:");
        System.out.println(userExistente);
        
        System.out.print("¿Estás seguro? (s/n): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("s")) {
            boolean eliminado = api.eliminarUsuario(id);
            if (eliminado) {
                System.out.println("✅ Usuario eliminado correctamente");
            } else {
                System.out.println("❌ No se pudo eliminar el usuario");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
    
    private static void guardarUsuarios() throws Exception {
        System.out.println("\n📚 Obteniendo usuarios para guardar...");
        List<User> usuarios = api.obtenerTodos();
        String json = jsonParser.convertirAJson(usuarios);
        fileManager.guardarComoJson(json, "todos_usuarios");
        System.out.println("✅ Usuarios guardados exitosamente!");
    }
    
    private static void verArchivos() throws Exception {
        System.out.println("\n📁 Archivos guardados:");
        List<String> archivos = fileManager.listarArchivos();
        if (archivos.isEmpty()) {
            System.out.println("No hay archivos guardados aún");
        } else {
            archivos.forEach(archivo -> System.out.println("  • " + archivo));
        }
    }

}
