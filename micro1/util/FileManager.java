package util;
//ahora unza vez consultado todos los datos donde guardamos todos los datos?
	//pues todos los datos lo guardarremos de manera local en este archivo llamado fileManager

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileManager {
	//recuerd aque la implemetacion de java.io es el que nos permite manejarnos con los temas de manejo de archivos 
	
	private static final String DATA_DIR = "datos_usuarios";
	//investigar que es lo que se va ahacer con este metodo 
	public FileManager() {
		File directorio = new File(DATA_DIR);
		if(!directorio.exists()) {
			directorio.mkdirs();
		}
	}
	//ahora entiendo este metodo es el que nos permite guardar datos dentro de nuestro equipo recuerdalo si?
	
	public void guardarComoJson(String contenido, String nombreArchivo) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String nombreCompleto = DATA_DIR + "/" + nombreArchivo + "_" + timestamp + ".json";
        
        Files.write(Paths.get(nombreCompleto), contenido.getBytes());
        System.out.println("💾 Datos guardados en: " + nombreCompleto);
    }
    
    public List<String> listarArchivos() throws IOException {
        try (var stream = Files.list(Paths.get(DATA_DIR))) {
            return stream
                .map(path -> path.getFileName().toString())
                .filter(name -> name.endsWith(".json"))
                .toList();
        }
    }

}
