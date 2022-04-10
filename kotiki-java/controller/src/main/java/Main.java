import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import dao.CatDao;
import dao.OwnerDao;
import model.Cat;
import model.Owner;
import net.bytebuddy.utility.RandomString;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Calendar;

public class Main {

    private static final OwnerService ownerService = new OwnerService(new OwnerDao());
    private static final CatService catService = new CatService(new CatDao());


    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/", new handler());

        server.createContext("/cowner", new createOwnerHandler());
        server.createContext("/cownercat", new createOwnerWithCatHandler());

        server.createContext("/owner", new ownerHandler());
        server.createContext("/cat", new catHandler());

        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class handler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "home" + ownerService.findAll() + catService.findAll();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class ownerHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            Long id = Long.valueOf(t.getRequestURI().getPath().split("/")[2]);
            String response = "owner page: " + ownerService.findById(id).toString();

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class catHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            Long id = Long.valueOf(t.getRequestURI().getPath().split("/")[2]);
            String response = "cat page: " + catService.findById(id).toString();

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class createOwnerHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            Owner owner = new Owner(RandomString.make(8));
            owner.setBirth(Calendar.getInstance().getTime());
            ownerService.create(owner);


            String response = "owner created: " + ownerService.findById(owner.getId()).toString();

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class createOwnerWithCatHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            Owner owner = new Owner(RandomString.make(8));
            ownerService.create(owner);
            Cat cat = new Cat(RandomString.make(8), owner.getId());
            catService.create(cat);


            String response = "owner and cat created: "
                    + ownerService.findById(owner.getId()).toString()
                    + catService.findById(cat.getId()).toString();

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}