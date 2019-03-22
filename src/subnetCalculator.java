import java.io.IOException;
import java.util.Scanner;

public class subnetCalculator {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter IP address (a.b.c.d/mask): ");
        String commandLine = in.nextLine();
        AddressIP addressIP = new AddressIP();
        addressIP.addressValidation(commandLine);
        String myIpAddress = addressIP.getAddress();
        String networkAddress = addressIP.calculateNetworkAddress(myIpAddress);
        String broadcastAddress = addressIP.calculateBroadcastAddress();
        String firstHostAddress = addressIP.firstHostAddress();
        String lastHostAddress = addressIP.lastHostAddress();
        String newLine = System.getProperty("line.separator");
        System.out.println("Adres sieci: ");
        System.out.println("Dziesiętnie: " + networkAddress + ", binarnie: " + addressIP.addressIpToBinary(networkAddress) + newLine);
        System.out.println("Maska sieci: ");
        System.out.println("Dziesiętnie: " + addressIP.subnetMaskDecimal() + ", binarnie: " + addressIP.subnetMaskBinary() + newLine);
        System.out.println("Adres broadcast: ");
        System.out.println("Dziesiętnie: " + broadcastAddress + ", binarnie: " + addressIP.addressIpToBinary(broadcastAddress) + newLine);
        System.out.println("Pierwszy adres hosta: ");
        System.out.println("Dziesiętnie: " + firstHostAddress + ", binarnie: " + addressIP.addressIpToBinary(firstHostAddress) + newLine);
        System.out.println("Ostatni adres hosta: ");
        System.out.println("Dziesiętnie: " + lastHostAddress + ", binarnie: " + addressIP.addressIpToBinary(lastHostAddress)+ newLine);
        System.out.println("Maksymalna liczba hostów: " + addressIP.maxHostAmount() + newLine);
        addressIP.isPrivate(myIpAddress);
        addressIP.whatClassOfAddress(myIpAddress);
        if(addressIP.getHostAddressToPing() != null){
            addressIP.sendPingRequest();
        }
    }
}

