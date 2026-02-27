module microserviciosJavaPuro {
	requires java.net.http;
	requires com.google.gson;
    opens modelos to com.google.gson; // Esto permite que Gson lea tus clases privadas
    exports util;
}


//recuerda que podemos hacer consumo de apis con java puro si?
//para ello se trabaja con http request
