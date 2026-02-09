import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AutomationCalculatorServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/calculate", new CalculateHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server started at http://localhost:8080/calculate");
    }

    static class CalculateHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            Map<String, String> params = parseFormData(body);

            double time = Double.parseDouble(params.getOrDefault("time", "0"));
            int tasks = Integer.parseInt(params.getOrDefault("tasks", "0"));
            double cost = Double.parseDouble(params.getOrDefault("cost", "0"));
            double dev = Double.parseDouble(params.getOrDefault("dev", "0"));
            double maintYearly = Double.parseDouble(params.getOrDefault("maintYearly", "0"));
        

            double manualCost = AutomationCalculator.ManualCostPerYear(time, tasks, cost);
            double automationCost = AutomationCalculator.AutomationCost(dev, maintYearly);
            double savings = AutomationCalculator.Savings(manualCost, automationCost);

            String decision = savings > 0 ? "Automation is WORTH IT" : "Automation is NOT worth it";

            String response =
                    "Manual Cost (Yearly): " + round(manualCost) + "\n" +
                    "Automation Cost (Year 1): " + round(automationCost) + "\n" +
                    "Savings (Year 1): " + round(savings) + "\n" +
                    "Decision: " + decision;

            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }

        private Map<String, String> parseFormData(String body) throws UnsupportedEncodingException {
            Map<String, String> map = new HashMap<>();
            String[] pairs = body.split("&");

            for (String pair : pairs) {
                String[] kv = pair.split("=");
                if (kv.length == 2) {
                    String key = URLDecoder.decode(kv[0], "UTF-8");
                    String value = URLDecoder.decode(kv[1], "UTF-8");
                    map.put(key, value);
                }
            }
            return map;
        }

        private double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }
    }
}
