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
        StringBuilder allInformations = new StringBuilder();
        allInformations.append("Adres sieci: " +  "\n" + "Dziesiętnie: " + networkAddress + ", binarnie: " + addressIP.addressIpToBinary(networkAddress) + "\n \n"
        + "Maska sieci: " + "\n" + "Dziesiętnie: " + addressIP.subnetMaskDecimal() + ", binarnie: " + addressIP.subnetMaskBinary() + "\n \n"
        + "Adres broadcast: " + "\n" + "Dziesiętnie: " + broadcastAddress + ", binarnie: " + addressIP.addressIpToBinary(broadcastAddress) + "\n \n"
        + "Pierwszy adres hosta: " + "\n" + "Dziesiętnie: " + firstHostAddress + ", binarnie: " + addressIP.addressIpToBinary(firstHostAddress) + "\n \n"
        + "Ostatni adres hosta: " + "\n" + "Dziesiętnie: " + lastHostAddress + ", binarnie: " + addressIP.addressIpToBinary(lastHostAddress) + "\n \n"
        + "Maksymalna liczba hostów: " + addressIP.maxHostAmount() + "\n \n" + IsPrivate.isPrivate(myIpAddress) + "\n \n" + ClassOfAddress.whatClassOfAddress(myIpAddress));

        System.out.println(allInformations.toString());

        PingRequest.sendPingRequest();

        SaveToFile.saveToFileInformation(allInformations.toString());
    }
}

