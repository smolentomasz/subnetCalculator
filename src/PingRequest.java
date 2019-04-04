import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PingRequest {
    public static void sendPingRequest() throws IOException {
        StringBuilder pingResult = new StringBuilder();
        String[] parts = String.valueOf(AddressIP.getHostAddressToPing()).split("\\/");
        String pingCmd = "ping " + parts[1];
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                pingResult.append(inputLine);
            }
            in.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
