import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class subnetCalculator {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter IP address (a.b.c.d/mask): ");
        String commandLine = in.nextLine();
        AddressIP addressIP = new AddressIP();
        addressIP.addressValidation(commandLine);
        //System.out.println("Maska dziesiętnie: " + addressIP.subnetMaskDecimal() + ", maska binarnie: " + addressIP.subnetMaskBinary());
        System.out.println("Adres IP binarnie: " + addressIP.addressIpToBinary());
    }
}

